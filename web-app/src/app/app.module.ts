import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { JwtModule } from '@auth0/angular-jwt';
import { LoginComponent } from './component/login/login.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { ProductComponent } from './component/product/product.component';
import { UserComponent } from './component/user/user.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ProductAddDialog } from './dialog/product/product-add-dialog/product-add-dialog.component';
import { ProductEditDialog } from './dialog/product/product-edit-dialog/product-edit-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UnauthorizedInterceptor } from './component/interceptor/interceptor.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ProductComponent,
    UserComponent,
    ProductAddDialog,
    ProductEditDialog
  ],
  entryComponents: [
    ProductAddDialog,
    ProductEditDialog
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatDialogModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('access_token');
        },
        whitelistedDomains: ['localhost'],
        blacklistedRoutes: ['localhost/auth/login']
      }
    }),
    NoopAnimationsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: UnauthorizedInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }