import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Task } from '../data/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private appServerUrl = environment.appBaseUrl;
  
  constructor( private http: HttpClient) { }
  
  public getTasks():Observable<Task[]>{
    return this.http.get<Task[]>(`${this.appServerUrl}/task/readall`)
  }
  public getTask(id : number):Observable<Task>{
    return this.http.get<Task>(`${this.appServerUrl}/task/read/${id}`)
  }
  public createTask(task : Task):Observable<Task>{
    return this.http.post<Task>(`${this.appServerUrl}/task/create`, task)
  }
  public updateTask(task : Task):Observable<Task>{
    console.log(task);
    return this.http.put<Task>(`${this.appServerUrl}/task/update`, task)
  }
  public deleteTask(id : number):Observable<void>{
    return this.http.delete<void>(`${this.appServerUrl}/task/delete/${id}`)
  }
}
