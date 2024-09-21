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

  public register(signUpRequest: any):Observable<any>{
    return this.http.post<any>(`${this.appServerUrl}/auth/signup`,signUpRequest);
  }

  login(loginRequest: any):Observable<any>{
    return this.http.post(`${this.appServerUrl}/auth/login`,loginRequest)
    .pipe(finalize(()=>{
      if(StorageService.isAdminUserLoggedIn()){
        this.router.navigate(['/admin/home']);
      }else if(StorageService.isUserLoggedIn()){
        this.router.navigate(['/home']);
      }else{
        console.log("BAD Credentials");
      }
    }));
  }
  logout(token: string){
    this.http.get(`${this.appServerUrl}/api/auth/logout?token=${token}`);
    StorageService.logout();
    this.router.navigate(['/login']);
  }
  isUserAuthenticated():boolean{
    return StorageService.isUserLoggedIn();
  }
  verifyAuthentication(){
    if(StorageService.isUserLoggedIn()){
      this.router.navigate(['/home']);
    }
  }
}