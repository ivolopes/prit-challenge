import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpEvent, HttpHandler, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable()
export class UnauthorizedInterceptor implements HttpInterceptor {
    
    constructor(
        private router: Router
    ) {}

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler,
       ): Observable<HttpEvent<any>> {
        
       return next.handle(req).pipe(
            tap(evt => {}),
            catchError( (error:any) => {
                if(error instanceof HttpErrorResponse) {
                    if( error.status == 403 ){
                        this.router.navigate(['login']);
                    }                    
                }

                return throwError(error);
            })
        );
    }
}