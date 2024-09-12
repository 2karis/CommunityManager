import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  isSpinning : boolean = false;
  signUpForm!: FormGroup;
 
  constructor(private authService: AuthService,
     private fb:FormBuilder,
    private router: Router
  ){}
  ngOnInit(){
    this.signUpForm = this.fb.group({
      email:[null,[Validators.required,Validators.email]],
      firstname:[null,[Validators.required]],
      lastname:[null,[Validators.required]],
      password:[null,[Validators.required]],
      confirmPassword:[null,[Validators.required, this.confirmPasswordValidate]],
      isAgreed:[null,[Validators.required]],
    })
  }
  confirmPasswordValidate = (control:FormControl):{[s: string]:boolean}=>{
    if(!control.value){
      return {required: true};
    }else if(control.value !== this.signUpForm.controls['password'].value){
      return {control:true, error:true};
    }else{
      return{};
    }
  }
  signUp():void{
    this.authService.signUp(this.signUpForm.value).subscribe({
       next(response){ 
          //do something with response?
       },
       error(error){console.log(error)},
    });
    this.router.navigate(['login']);

  }
}
