import { Component } from '@angular/core';
import { ProductService } from './services/product.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'amazom_eCommerce';
  totalPrice: number = 0;
  registerObj: any = {
    "userName": "",
    "mobile": "",
    "password": ""
  }
  loginObj: any = {
    "UserName": "",
    "UserPassword": ""
  }
  loggedObj: any = {};
  cartItems: any[]= [];
  loginModelClass: string = '';
  constructor(private productSrv: ProductService) {
    const localData = localStorage.getItem('amazon_user');
    if(localData != null) {
      const parseObj =  JSON.parse(localData);
      this.loggedObj = parseObj;
      this.getCartData(this.loggedObj.id)
    }
    this.productSrv.cartUpdated.subscribe((res: boolean)=>{
      if(res) {
        this.getCartData(this.loggedObj.id)
      }
    })
    this.productSrv.showLogin.subscribe((res: boolean)=>{
      if(res) {
         this.loginModelClass = 'show';
      }
    })
  }
  calculateTotalPrice() {
    this.totalPrice = this.cartItems.reduce((sum, item) => sum + item.productPrice*item.quantity, 0);
}
  getCartData(id: number) {
    this.productSrv.getAddtocartdataByCust(id).subscribe((res: any)=>{
     
      this.cartItems = res;
      this.calculateTotalPrice();
    })
  }

  onRegister() {
    this.productSrv.register(this.registerObj).subscribe((res: any)=> {
      if(res) {
        this.loggedObj = res;
        localStorage.setItem('amazon_user', JSON.stringify(res));
        this.getCartData(this.loggedObj.id)
        alert("User Creation Done")
        window.location.reload();
      } else {
        alert(res.message)
      }
    })
  }
  onLogin() {
    this.productSrv.login(this.loginObj).subscribe((res: any)=> {
      debugger
      if(res) {
        alert("User Login Success");
        this.loggedObj = res;
        this.loginModelClass = '';
        localStorage.setItem('amazon_user', JSON.stringify(res));
         this.getCartData(this.loggedObj.id)
      } else {
        alert(res.message)
      }
    })
  }

  
  removeItem(customerId: number,productId: number) {
    this.productSrv.removeProductFromCart(customerId,productId).subscribe((res: any)=> {
      if(res) {
        alert("Item Removed"); 
        this.getCartData(this.loggedObj.id)
        this.calculateTotalPrice();
      } else {
        alert(res.message)
      }
    })
  }

  logout() {
    this.loggedObj = {};

    localStorage.removeItem('amazon_user');

    this.cartItems = [];

    window.location.reload();
  }
  
}