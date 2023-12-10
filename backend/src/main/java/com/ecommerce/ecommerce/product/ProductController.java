package com.ecommerce.ecommerce.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products-apis")
@CrossOrigin(origins = "http://localhost:4202")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/product/id/{product-id}")
    public ResponseEntity<Product> getProductById(@PathVariable("product-id") Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
//        throw new ApiRequestException("Oops m4 3aref ageeb el products");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


    @PostMapping("add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }
}
