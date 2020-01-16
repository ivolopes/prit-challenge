import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  model: any = {
    name:null,
    email:null,
    password:null
  };

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
  }

  salvar(){
      this.userService.save(this.model)
      .subscribe(
        data => {
          alert("Usuário cadastrado com sucesso");
          this.voltar();
        },
        error => {
          alert("Ocorreu um problema ao incluir o usuário");
      })
  }

  voltar(){
    this.router.navigate(['login']);
  }

}
