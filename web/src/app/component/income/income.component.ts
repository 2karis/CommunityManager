import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Income } from '../../data/income';
import { IncomeService } from '../../service/income.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-income',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './income.component.html',
  styleUrl: './income.component.css'
})
export class IncomeComponent {
  public incomes : Income[];

  public income : Income;

  constructor(private incomeService : IncomeService){
    this.incomes = [];
    this.income = <Income>{};
  }
  ngOnInit(): void {
    this.getIncomes();
  }

  public getIncomes(): void{
    this.incomeService.getIncomes().subscribe(
      (response: Income[])=>{
        this.incomes=response;
        console.log(this.incomes);
      },
      (error :HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public onOpenModal(currIncome? : Income): void{
    this.income = <Income>currIncome;
  }

  public onCreateIncome(createForm : NgForm): void{
    this.incomeService.createIncome(createForm.value).subscribe({
      error: (e) => {
        alert(e);
      },
      complete: () => {
        this.getIncomes();
        createForm.reset();
        document.getElementById("createClose")?.click()
      } 
    })
  }

  public onUpdateIncome(updateForm : NgForm): void{
    this.incomeService.updateIncome(<Income>updateForm.value).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getIncomes();
        updateForm.reset();
        document.getElementById("updateClose")?.click()
      } 
    })
  }

  public onDeleteIncome(id:number): void{
    this.incomeService.deleteIncome(id).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getIncomes();
        document.getElementById("deleteClose")?.click()
      } 
    })
  }

  public searchIncome(key : string){
    const results : Income[] = [];
    for(const income of this.incomes){
      if(income.status.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(income);
      }
    }
    this.incomes = results;
    if(results.length===0 || !key){
      this.getIncomes();
    }
  }
}
