package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.cart.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.customerId = :customerId")
    List<Cart> findByCustomerId(@Param("customerId") Long customerId);
    Optional<Cart> findByCustomerIdAndProductId(Long customerId, Long productId);


    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.customerId = :customerId")
    void deleteAllByCustomerId(Long customerId);

}
