import { Component, EventEmitter, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { StorageService } from '../../service/storage.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  isSpinning : boolean = false;
  signInForm!: FormGroup;
  @Output() loginEvent = new EventEmitter<boolean>();
  constructor(private fb:FormBuilder,
     private authService:AuthService,
     private route: ActivatedRoute,
     private router: Router
    ){}
  ngOnInit(){
    this.signInForm = this.fb.group({
      email:[null,[Validators.required,Validators.email]],
      password:[null,[Validators.required]],
    })

  }
  login(){
    var isLoggedIn= false;
    this.authService.login(this.signInForm.value).subscribe({
      next(response){ 
        if(response.email!=null){
          const user = {
            email:response.email,
            role : response.role
          }
          StorageService.saveUser(user);
          StorageService.saveToken(response.token);
          isLoggedIn = true;
        }
      },
      error(error){console.log(error)},
   });
    //send isloggedin to app component

   this.loginEvent.emit(isLoggedIn);


  }
}
