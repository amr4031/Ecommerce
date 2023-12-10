package com.ecommerce.ecommerce.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers-apis")
@CrossOrigin(origins = "http://localhost:4202")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/customer/id/{customer-id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customer-id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
//        throw new ApiRequestException("Oops m4 3aref ageeb el customers");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }


    @PostMapping("add-customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<Customer> login(@RequestBody  LoginDto loginDto){
        return new ResponseEntity<>(customerService.login(loginDto),HttpStatus.OK);
    }
}
