import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Property } from '../data/property';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  private appServerUrl = environment.appBaseUrl;
  
  constructor( private http: HttpClient) { }
  
  public getPropertys():Observable<Property[]>{
    return this.http.get<Property[]>(`${this.appServerUrl}/property/readall`)
  }
  public getProperty(id : number):Observable<Property>{
    return this.http.get<Property>(`${this.appServerUrl}/property/read/${id}`)
  }
  public createProperty(data : Property):Observable<Property>{
    console.log(data);
    return this.http.post<Property>(`${this.appServerUrl}/property/create`, data)
  }
  public updateProperty(data : Property):Observable<Property>{
    return this.http.put<Property>(`${this.appServerUrl}/property/update`, data)
  }
  public deleteProperty(id : number):Observable<void>{
    return this.http.delete<void>(`${this.appServerUrl}/property/delete/${id}`)
  }
}
