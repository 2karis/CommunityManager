import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Expense } from '../data/expense';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  private appServerUrl = environment.appBaseUrl;
  
  constructor( private http: HttpClient) { }
  
  public getExpenses():Observable<Expense[]>{
    return this.http.get<Expense[]>(`${this.appServerUrl}/expense/readall`)
  }
  public getExpense(id : number):Observable<Expense>{
    return this.http.get<Expense>(`${this.appServerUrl}/expense/read/${id}`)
  }
  public createExpense(empolyee : Expense):Observable<Expense>{
    return this.http.post<Expense>(`${this.appServerUrl}/expense/create`, empolyee)
  }
  public updateExpense(empolyee : Expense):Observable<Expense>{
    return this.http.put<Expense>(`${this.appServerUrl}/expense/update`, empolyee)
  }
  public deleteExpense(id : number):Observable<void>{
    return this.http.delete<void>(`${this.appServerUrl}/expense/delete/${id}`)
  }
}
