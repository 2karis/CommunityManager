import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../../data/user';
import { UserService } from '../../service/user.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  public users : User[];

  public user : User;

  constructor(private userService : UserService){
    this.users = [];
    this.user = <User>{};
  }
  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers(): void{
    this.userService.getUsers().subscribe(
      (response: User[])=>{
        this.users=response;
        console.log(this.users);
      },
      (error :HttpErrorResponse)=>{
        console.log(error.message);
      }
    )
  }

  public onOpenModal(currUser? : User): void{
    this.user = <User>currUser;
  }

  public onCreateUser(createForm : NgForm): void{
    this.userService.createUser(createForm.value).subscribe({
      error: (e) => {
        console.log(e);
      },
      complete: () => {
        this.getUsers();
        createForm.reset();
        document.getElementById("createClose")?.click()
      } 
    })
  }

  public onUpdateUser(updateForm : NgForm): void{
    this.userService.updateUser(<User>updateForm.value).subscribe({
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.getUsers();
        updateForm.reset();
        document.getElementById("updateClose")?.click()
      } 
    })
  }

  public onDeleteUser(id:number): void{
    this.userService.deleteUser(id).subscribe({
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.getUsers();
        document.getElementById("deleteClose")?.click()
      } 
    })
  }

  public searchUser(key : string){
    const results : User[] = [];
    for(const user of this.users){
      if(user.firstName.toLowerCase().indexOf(key.toLowerCase()) !== -1||
         user.lastName.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(user);
      }
    }
    this.users = results;
    if(results.length===0 || !key){
      this.getUsers();
    }
  }
}
