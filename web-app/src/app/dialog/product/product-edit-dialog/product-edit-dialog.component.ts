import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-edit-dialog',
  templateUrl: './product-edit-dialog.component.html',
  styleUrls: ['./product-edit-dialog.component.css']
})
export class ProductEditDialog implements OnInit {

  constructor(
    private productService:ProductService,
    public dialogRef: MatDialogRef<ProductEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Product) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  salvar(){
    this.productService.update(this.data).subscribe(product => {
      alert("Produto alterado com sucesso!");
      this.onNoClick();
    },
    error =>{
      alert("Ocorreu um erro ao editar o produto");
    })
  }

  ngOnInit() {
  }

}
