package com.ecommerce.ecommerce.checkout;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "bill", schema = "ecommerce")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "sale_date")
    private Date saleDate;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "address1")
    private String deliveryAddress1;
    @Column(name = "address2")
    private String deliveryAddress2;
    @Column(name = "city")
    private String deliveryCity;
    @Column(name = "zip_code")
    private String deliveryPinCode;
}
