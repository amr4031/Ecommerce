package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public List<Cart> getCartById(Long id) {

        return cartRepository.findByCustomerId(id);
    }

    public Cart addCart(Cart cart) {
        // Check if the cart item exists
        Optional<Cart> existingCartItem = cartRepository.findByCustomerIdAndProductId(cart.getCustomerId(), cart.getProductId());
        if (existingCartItem.isPresent()) {
            existingCartItem.get().setQuantity(cart.getQuantity() + 1); // Update the quantity
            return cartRepository.save(existingCartItem.get());
        }
        return cartRepository.save(cart);
    }


    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }


    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    public Optional<Cart> removeCartItem(Long customerId, Long productId) {
        // Find the cart item
        Optional<Cart> cartItem = cartRepository.findByCustomerIdAndProductId(customerId, productId);

        if (cartItem.isPresent()) {
            Cart existingCartItem = cartItem.get();

            if (existingCartItem.getQuantity() > 1) {
                // If quantity is greater than 1, decrement and update
                existingCartItem.setQuantity(existingCartItem.getQuantity() - 1);
                cartRepository.save(existingCartItem);
            } else {
                // If quantity is 1, delete the cart item
                cartRepository.deleteById(existingCartItem.getId());
            }
        } else {
            // Optional: throw an exception or handle the case where the cart item is not found
            // For example: throw new CartItemNotFoundException("Cart item not found");
        }
        return cartItem;
    }
}
