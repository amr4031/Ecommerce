package com.ecommerce.ecommerce.product;

import com.ecommerce.ecommerce.product.Product;
import com.ecommerce.ecommerce.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }


    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
