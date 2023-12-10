package com.ecommerce.ecommerce.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product", schema = "ecommerce")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;
    @Column(name = "product_price", nullable = false)
    private String productPrice;
    @Column(name = "image_url")
    private String imageUrl;
}