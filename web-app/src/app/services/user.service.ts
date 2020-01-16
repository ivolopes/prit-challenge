import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // API path
  basePath = 'http://localhost:8080/api/v1/users/';

  constructor(
    private router: Router,
    private http: HttpClient) { }

    httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    save(user:User):Observable<User>{
        return this.http.post<User>(this.basePath, user);
    }
}
