import { Component, OnInit, Inject } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-product-add-dialog',
  templateUrl: './product-add-dialog.component.html',
  styleUrls: ['./product-add-dialog.component.css']
})
export class ProductAddDialog implements OnInit {

  model: any = {
    name:null,
    description:null,
    price:null
  };

  constructor(
    private productService:ProductService,
    public dialogRef: MatDialogRef<ProductAddDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Product) {}

    ngOnInit() {
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  salvar(){
    this.productService.save(this.model).subscribe(product => {
      alert("Produto cadastrado com sucesso!");
      this.onNoClick();
    },
    error =>{
      alert("Ocorreu um erro ao cadastrar o produto");
    })
  }

}
