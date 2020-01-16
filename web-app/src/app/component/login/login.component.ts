import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { HttpResponse } from '@angular/common/http';
import { LoginResponse } from 'src/app/models/user';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  model: any = {};
 
  constructor(
    private authService: AuthService
  ) {}
 
  ngOnInit() {
    this.authService.logout();
  }
 
  login() {
    this.model.action = 'login';
    this.authService.loginForm(this.model).subscribe((res: LoginResponse) => {
      this.authService.setUser(res);
    }, error => {
      console.error(error);
    });
  }
 
}