import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { LoginComponent } from './component/login/login.component';
import { ProductComponent } from './component/product/product.component';
import { UserComponent } from './component/user/user.component';
 
const routes: Routes = [
  {path: 'dashboard', canActivate: [AuthGuard], component: DashboardComponent},
  {path: 'product', canActivate: [AuthGuard], component: ProductComponent},
  {path: 'login', component: LoginComponent},
  {path: 'user', component: UserComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' },
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }