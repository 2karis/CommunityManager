import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Income } from '../data/income';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private appServerUrl = environment.appBaseUrl;
  
  constructor( private http: HttpClient) { }
  
  public getIncomes():Observable<Income[]>{
    return this.http.get<Income[]>(`${this.appServerUrl}/income/readall`)
  }
  public getIncome(id : number):Observable<Income>{
    return this.http.get<Income>(`${this.appServerUrl}/income/read/${id}`)
  }
  public createIncome(empolyee : Income):Observable<Income>{
    return this.http.post<Income>(`${this.appServerUrl}/income/create`, empolyee)
  }
  public updateIncome(empolyee : Income):Observable<Income>{
    return this.http.put<Income>(`${this.appServerUrl}/income/update`, empolyee)
  }
  public deleteIncome(id : number):Observable<void>{
    return this.http.delete<void>(`${this.appServerUrl}/income/delete/${id}`)
  }
  
}
