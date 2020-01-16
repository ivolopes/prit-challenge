import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Array<Product>;

  constructor(
    private productService: ProductService,
    private router: Router
  ) { }

  ngOnInit() {
    this.productService.getAll().subscribe(products => {
        this.products = products;
    })
  }

  delete(id:number){
    if( confirm("Deseja excluir o produto?") ){
      this.productService.delete(id).subscribe( () => {
        alert("Produto excluído");
        this.router.navigate(['product']);
      }, error => {
        console.error(error);
      });
    }
  }

  edit(product:Product){

  }

}
