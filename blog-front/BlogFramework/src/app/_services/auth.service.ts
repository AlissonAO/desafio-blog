import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8084/autentica';
const REG_API = 'http://localhost:8084/usuarios';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API, {
      email: credentials.email,
      senha: credentials.senha
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(REG_API, {
      nome: user.nome,
      email: user.email,
      senha: user.senha
    }, httpOptions);
  }
}
