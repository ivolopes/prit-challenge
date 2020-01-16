import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // API path
  basePath = 'http://localhost:8080/api/v1/products/';

  constructor(
    private router: Router,
    private http: HttpClient
    ){ }

    httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem('access_token')
      })
    };

    getAll():Observable<Array<Product>>{
        return this.http.get<Array<Product>>(this.basePath, this.httpOptions);
    }

    delete(id:number):Observable<any>{
      return this.http.delete<any>(this.basePath+id, this.httpOptions);
    }

    update(product:Product):Observable<Product>{
      return this.http.put<Product>(this.basePath+product.id, product, this.httpOptions);
    }

    save(product:Product):Observable<Product>{
      return this.http.post<Product>(this.basePath,product, this.httpOptions);
    }
    
}
