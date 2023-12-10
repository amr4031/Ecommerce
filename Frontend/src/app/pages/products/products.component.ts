import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{

  productsArray: any[] = [];
  categories: any[]= [];
  selectedCategory: number = 0;
  loggedObj: any = {};

  constructor(private productSrv: ProductService) {
    const localData = localStorage.getItem('amazon_user');
    if(localData != null) {
      const parseObj =  JSON.parse(localData);
      this.loggedObj = parseObj;
    }
  }

  ngOnInit(): void {
    this.loadProducts();
    this.loadCategory();
  }
  loadProducts() {
    this.productSrv.getAllProducts().subscribe((Res: any) =>{
      this.productsArray = Res;
    })
  }

  getAllProductsByCateogry(categoryId: number) {
    this.selectedCategory = categoryId;
    this.productSrv.getAllProductsByCateogry(categoryId).subscribe((Res: any) =>{
      this.productsArray = Res.data;
    })
  }
  

  loadCategory() {
    this.productSrv.getAllCategory().subscribe((Res: any) =>{
      this.categories = Res.data;
    })
  }

  addtocart(product: any) {
    if(this.loggedObj.id == undefined) {
      this.productSrv.showLogin.next(true);
    } else {
      const obj = {
        "id": 0,
        "customerId": this.loggedObj.id,
        "productId": product.id,
        "quantity": 1,
        "productShortName": product.productName,
        "addedDate": new Date(),
        "productImageUrl": product.imageUrl,
        "productPrice":product.productPrice
      }
      this.productSrv.addtoCart(obj).subscribe((res: any)=> {
        if(res) {
          alert("Product Added to Cart"); 
          this.productSrv.cartUpdated.next(true);
        } else {
          alert(res.message)
        }
      }) 
    }
  }




}