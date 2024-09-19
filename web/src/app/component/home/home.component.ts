import { Component } from '@angular/core';
import { Income } from '../../data/income';
import { HomeService } from '../../service/home.service';
import { Home } from '../../data/home';
import { Task } from '../../data/task';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  tasks!:Task[];
  incomes!:Income[];
  balance!:number;
  dueOn!:Date;
  
  constructor(private homeService:HomeService){
    this.tasks=[];
    this.incomes=[];
    this.balance=0;
    this.dueOn = new Date();
  }
  ngOnInit(): void {
    this.getHome();
  }

  public getHome(): void{
    this.homeService.getHome().subscribe(
      (response: Home)=>{
        this.balance=response.balance;
        this.dueOn=response.dueOn;
        this.incomes=response.incomes;
        this.tasks=response.tasks;
        console.log(this.incomes);
      },
      (error :HttpErrorResponse)=>{
        console.log(error.message);
      }
    )
  }
}
