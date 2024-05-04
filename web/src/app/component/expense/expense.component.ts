import { Component } from '@angular/core';
import { Expense } from '../../data/expense';
import { ExpenseService } from '../../service/expense.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-expense',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './expense.component.html',
  styleUrl: './expense.component.css'
})
export class ExpenseComponent {
  public expenses : Expense[];

  public expense : Expense;

  constructor(private expenseService : ExpenseService){
    this.expenses = [];
    this.expense = <Expense>{};
  }
  ngOnInit(): void {
    this.getExpenses();
  }

  public getExpenses(): void{
    this.expenseService.getExpenses().subscribe(
      (response: Expense[])=>{
        this.expenses=response;
        console.log(this.expenses);
      },
      (error :HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public onOpenModal(currExpense? : Expense): void{
    this.expense = <Expense>currExpense;
  }

  public onCreateExpense(createForm : NgForm): void{
    this.expenseService.createExpense(createForm.value).subscribe({
      error: (e) => {
        alert(e);
      },
      complete: () => {
        this.getExpenses();
        createForm.reset();
        document.getElementById("createClose")?.click()
      } 
    })
  }

  public onUpdateExpense(updateForm : NgForm): void{
    this.expenseService.updateExpense(<Expense>updateForm.value).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getExpenses();
        updateForm.reset();
        document.getElementById("updateClose")?.click()
      } 
    })
  }

  public onDeleteExpense(id:number): void{
    this.expenseService.deleteExpense(id).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getExpenses();
        document.getElementById("deleteClose")?.click()
      } 
    })
  }

  public searchExpense(key : string){
    const results : Expense[] = [];
    for(const expense of this.expenses){
      if(expense.description.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(expense);
      }
    }
    this.expenses = results;
    if(results.length===0 || !key){
      this.getExpenses();
    }
  }
}
