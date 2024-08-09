import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Task } from '../../data/task';
import { TaskService } from '../../service/task.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css'
})
export class TaskComponent {
  public tasks : Task[];

  public task! : Task;

  constructor(private taskService : TaskService){
    this.tasks = [];
    this!.task = <Task>{};
  }
  ngOnInit(): void {
    this.getTasks();
  }

  public getTasks(): void{
    this.taskService.getTasks().subscribe(
      (response: Task[])=>{
        this.tasks=response;
        console.log(this.tasks);
      },
      (error :HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public onOpenModal(currTask? : Task): void{
    this.task = <Task>currTask;
  }

  public onCreateTask(createForm : NgForm): void{
    this.taskService.createTask(createForm.value).subscribe({
      error: (e) => {
        alert(e);
      },
      complete: () => {
        this.getTasks();
        createForm.reset();
        document.getElementById("createClose")?.click()
      } 
    })
  }

  public onUpdateTask(updateForm : NgForm): void{
    this.taskService.updateTask(<Task>updateForm.value).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getTasks();
        updateForm.reset();
        document.getElementById("updateClose")?.click()
      } 
    })
  }

  public onDeleteTask(id:number): void{
    this.taskService.deleteTask(id).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getTasks();
        document.getElementById("deleteClose")?.click()
      } 
    })
  }

  public searchTask(key : string){
    const results : Task[] = [];
    for(const task of this.tasks){
      if(task.description.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(task);
      }
    }
    this.tasks = results;
    if(results.length===0 || !key){
      this.getTasks();
    }
  }
}
