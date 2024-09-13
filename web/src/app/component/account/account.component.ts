import { Component } from '@angular/core';
import { UserService } from '../../service/user.service';
import { User } from '../../data/user';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent {
  public user : User;
  constructor(private userService : UserService,
              private route : ActivatedRoute
  ){
    this.user = <User>{};
  }
  ngOnInit(): void {
    const selectedId : number = Number(this.route.snapshot.paramMap.get('id'));
    this.getUser(selectedId);
  }

  public getUser(id:number): void{
    this.userService.getUser(id).subscribe(
      (response: User)=>{
        this.user=response;
        console.log(this.user);
      },
      (error :HttpErrorResponse)=>{
        console.log(error.message);
      }
    )
  }
}
