import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './pages/products/products.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { AboutUsComponent } from './pages/about-us/about-us.component';


const routes: Routes = [
  {
    path:'products',
    component: ProductsComponent
  },
  {
    path:'checkout',
    component: CheckoutComponent
  },
  {
    path:'aboutUs',
    component: AboutUsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }