import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/product';
import { Router } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ProductEditDialog } from 'src/app/dialog/product/product-edit-dialog/product-edit-dialog.component';
import { ProductAddDialog } from 'src/app/dialog/product/product-add-dialog/product-add-dialog.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Array<Product>;

  constructor(
    private productService: ProductService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.productService.getAll().subscribe(products => {
        this.products = products;
    })
  }

  home(){
    this.router.navigate(['dashboard']);
  }

  delete(id:number){
    if( confirm("Deseja excluir o produto?") ){
      this.productService.delete(id).subscribe( () => {
        alert("Produto excluído");
        window.location.reload();
      }, error => {
        console.error(error);
      });
    }
  }

  edit(product:Product){

    var data = {
      id: product.id,
      name: product.name,
      description: product.description,
      price: product.price
    };

    const dialogRef = this.dialog.open(ProductEditDialog, {
      width: '500px',
      data: data
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  openDialogAdd(): void {
    const dialogRef = this.dialog.open(ProductAddDialog, {
      width: '400px',
      height: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  getFormattedPrice(price: number) {
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(price);
  }

}
