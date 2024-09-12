import { Component } from '@angular/core';
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
    this.authService.login(this.signInForm.value).subscribe({
      next(response){ 
        if(response.email!=null){
          const user = {
            email:response.email,
            role : response.role
          }
          StorageService.saveUser(user);
          StorageService.saveToken(response.token)
        }
      },
      error(error){console.log(error)},
   });

  //  if(StorageService.isAdminUserLoggedIn()){
  //     this.router.navigate(['/admin/dashboard']);
  //   }else if(StorageService.isUserLoggedIn()){
  //     this.router.navigate(['/customer/dashboard']);
  //   }else{
  //     alert("BAD Credentials");
  //   }

  }
}
