package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.customer.Customer;
import com.ecommerce.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "cart", schema = "ecommerce")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "product_name")
    private String productShortName;
    @Column(name = "added_date")
    private Date addedDate;
    @Column(name = "image_url")
    private String productImageUrl;
    @Column(name = "product_price")
    private String productPrice;

//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
}
