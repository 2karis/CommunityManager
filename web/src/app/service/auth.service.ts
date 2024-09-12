import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { finalize, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { StorageService } from './storage.service';
import { environment } from '../../environments/environment.development';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private appServerUrl = environment.appBaseUrl;
  
  
  constructor( private http: HttpClient,private router :Router) { }

  public signUp(signUpRequest: any):Observable<any>{
    return this.http.post<any>(`${this.appServerUrl}/auth/signup`,signUpRequest);
  }

  login(loginRequest: any):Observable<any>{
    return this.http.post(`${this.appServerUrl}/auth/login`,loginRequest)
    .pipe(finalize(()=>{
      if(StorageService.isAdminUserLoggedIn()){
        this.router.navigate(['/admin/dashboard']);
      }else if(StorageService.isUserLoggedIn()){
        this.router.navigate(['/customer/dashboard']);
      }else{
        alert("BAD Credentials");
      }
    }));
  }
  logout(token: string){
    return this.http.get(`${this.appServerUrl}/api/auth/logout?token=${token}`);
  }
}