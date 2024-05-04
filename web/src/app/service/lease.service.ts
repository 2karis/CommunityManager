import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Lease } from '../data/lease';

@Injectable({
  providedIn: 'root'
})
export class LeaseService {

  private appServerUrl = environment.appBaseUrl;
  
  constructor( private http: HttpClient) { }
  
  public getLeases():Observable<Lease[]>{
    return this.http.get<Lease[]>(`${this.appServerUrl}/lease/readall`)
  }
  public getLease(id : number):Observable<Lease>{
    return this.http.get<Lease>(`${this.appServerUrl}/lease/read/${id}`)
  }
  public createLease(empolyee : Lease):Observable<Lease>{
    return this.http.post<Lease>(`${this.appServerUrl}/lease/create`, empolyee)
  }
  public updateLease(empolyee : Lease):Observable<Lease>{
    return this.http.put<Lease>(`${this.appServerUrl}/lease/update`, empolyee)
  }
  public deleteLease(id : number):Observable<void>{
    return this.http.delete<void>(`${this.appServerUrl}/lease/delete/${id}`)
  }
}
