import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Home } from '../data/home';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private appServerUrl = environment.appBaseUrl;
  
  constructor( private http: HttpClient) { }
  
  public getHome():Observable<Home>{
    return this.http.get<Home>(`${this.appServerUrl}/home`)
  }

  
}