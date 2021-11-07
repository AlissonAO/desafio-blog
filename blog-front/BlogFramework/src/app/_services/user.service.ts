import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8084';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + '/', { responseType: 'text' }); //all
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + '/topicos', { responseType: 'text' }); //user
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + '/topicos', { responseType: 'text' }); //mod
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + '/topicos', { responseType: 'text' }); //admin chamr um form
  }
}
