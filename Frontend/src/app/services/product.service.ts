import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  cartUpdated: Subject<boolean> = new Subject<boolean>();
  showLogin: Subject<boolean> = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8080/products-apis/products");
  }
  getAllProductsByCateogry(id: number): Observable<any[]> {
    return this.http.get<any[]>("https://freeapi.miniprojectideas.com/api/amazon/GetAllProductsByCategoryId?id="+ id);
  }

  getAllCategory(): Observable<any[]> {
    return this.http.get<any[]>("https://freeapi.miniprojectideas.com/api/amazon/GetAllCategory");
  }

  register(obj: any) : Observable<any> {
    return this.http.post<any>("http://localhost:8080/customers-apis/add-customer", obj);
  }

  login(obj: any) : Observable<any> {
    return this.http.post<any>("http://localhost:8080/customers-apis/login", obj);
    
  }

  addtoCart(obj: any) : Observable<any> {
    return this.http.post<any>("http://localhost:8080/carts-apis/add-cart", obj);
  }

  createProduct(obj: any) : Observable<any> {
    return this.http.post<any>("http://localhost:8080/products-apis/add-product", obj);
  }

  getAddtocartdataByCust(id: number): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8080/carts-apis/cart/id/"+ id);

  }

  removeProductFromCart(customerId: number,productId: number): Observable<any[]> {
    return this.http.delete<any[]>("http://localhost:8080/carts-apis/remove/"+ customerId +"/" + productId);
  }

  removeAllProductFromCart(customerId: number): Observable<any[]> {
    return this.http.delete<any[]>("http://localhost:8080/carts-apis/remove-all/"+ customerId);
  }
  PlaceOrder(obj: any) : Observable<any> {
    return this.http.post<any>("http://localhost:8080/bills-apis/add-bill", obj);
  }
}