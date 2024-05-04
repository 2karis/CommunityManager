import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { User } from '../data/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private appServerUrl = environment.appBaseUrl;
  constructor( private http: HttpClient) { }
  public getUsers():Observable<User[]>{
    return this.http.get<User[]>(`${this.appServerUrl}/user/readall`)
  }
  public getUser(id : number):Observable<User>{
    return this.http.get<User>(`${this.appServerUrl}/user/read/${id}`)
  }
  public createUser(empolyee : User):Observable<User>{
    return this.http.post<User>(`${this.appServerUrl}/user/create`, empolyee)
  }
  public updateUser(empolyee : User):Observable<User>{
    return this.http.put<User>(`${this.appServerUrl}/user/update`, empolyee)
  }
  public deleteUser(id : number):Observable<void>{
    return this.http.delete<void>(`${this.appServerUrl}/user/delete/${id}`)
  }
}
