import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  totalPrice: number = 0;
  loggedObj: any = {};
  cartItems: any[]= [];
  checkoutObj: any = {
    "Id": 0,
    "customerId": 0,
    "saleDate": new Date(),
    "totalPrice": 0,
    "discount": 0,
    "paymentType": "",
    "deliveryAddress1": "",
    "deliveryAddress2": "",
    "deliveryCity": "",
    "deliveryPinCode": "",
    "cardNumber": "",
    "cardHolderName": "",
    "expiryDate": "",
    "CVV": ""
  }

  paymentMethod: string ='creditCard';
  cardDetails = {
      cardNumber: '',
      cardHolderName: '',
      expiryDate: '',
      cvv: ''
  };

  constructor(private productSrv: ProductService, private router: Router) {
    const localData = localStorage.getItem('amazon_user');
    if(localData != null) {
      const parseObj =  JSON.parse(localData);
      this.loggedObj = parseObj;
      this.getCartData(this.loggedObj.id)

    }
  }

  ngOnInit(): void {
    
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

  placeOrder() {
    this.checkoutObj.customerId =  this.loggedObj.id;
    this.productSrv.PlaceOrder(this.checkoutObj).subscribe((res: any)=> {
      if(res) { 
        
        alert("Order Has Been Succefully Placed")
        this.productSrv.removeAllProductFromCart(this.checkoutObj.customerId).subscribe((res: any)=> {
        })
        this.productSrv.cartUpdated.next(true);
        this.router.navigate(['/products']);
      } else {
        alert(res.message)
      }
    })
  } 
}