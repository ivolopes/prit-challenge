import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @ViewChild('form', {
    static: false
  }) form: NgForm;

  model: any = {
    name:null,
    email:null,
    password:null
  };
  erroMessage: string;
  success: boolean;

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
  }

  salvar(){
      this.erroMessage = undefined;
      this.success = false;
      this.userService.save(this.model)
      .subscribe(
        data => {
          this.success = true;
          this.form.reset();
        },
        error => {
          this.erroMessage = error.error.message;
      })
  }

  voltar(){
    this.router.navigate(['login']);
  }

}
