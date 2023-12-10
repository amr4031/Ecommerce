package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.cart.Cart;
import com.ecommerce.ecommerce.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts-apis")
@CrossOrigin
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/cart/id/{customer-id}")
    public ResponseEntity<List<Cart>> getCartByCustomerId(@PathVariable("customer-id") Long id) {
        List<Cart> cart = cartService.getCartById(id);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
//        throw new ApiRequestException("Oops m4 3aref ageeb el carts");
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }


    @PostMapping("add-cart")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.addCart(cart), HttpStatus.OK);
    }

    @DeleteMapping("remove/{customerId}/{productId}")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long customerId, @PathVariable Long productId) {

        return new ResponseEntity<>(cartService.removeCartItem(customerId, productId).get(), HttpStatus.OK);
    }
}
