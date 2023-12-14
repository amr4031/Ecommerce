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

  product = {
    productName: '',
    productPrice: '',
    imageUrl: ''
};
  constructor(private productSrv: ProductService) {
    const localData = localStorage.getItem('amazon_user');
    debugger
    if(localData != null) {
      const parseObj =  JSON.parse(localData);
      this.loggedObj = parseObj;
    }
  }

  confirmDelete(productId: number): void {
    if (window.confirm('Are you sure you want to remove this item?')) {
      // Logic to remove the item
      this.removeProduct(productId);
    }
  }

  // Method to remove product
  removeProduct(productId: number): void {

    this.productSrv.removeProduct(productId).subscribe((res: any)=> {
        alert("Product Removed");     
        window.location.reload();    
    })

    console.log("remove item ffffffffffffffffffffffffffffffffffffffffffffffff")
    // Your logic to remove the product
    // Update your productsArray accordingly
  }


  ngOnInit(): void {
    debugger
    this.loadProducts();
    this.loadCategory();
  }
  submitProduct() {

    this.productSrv.createProduct(this.product).subscribe((res: any)=> {
      if(res) {
        alert("Product Added");     
        window.location.reload();    
      } else {
        alert(res.message)
      }
    })
    console.log(this.product);
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