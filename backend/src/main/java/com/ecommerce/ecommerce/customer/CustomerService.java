package com.ecommerce.ecommerce.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer login(LoginDto loginDto) {
        List<Customer> customers = getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getUserName().equals(loginDto.getUserName()) && customer.getPassword().equals(loginDto.getUserPassword())) {
                // Logic for successful login
                return customer;
            }
        }
        throw new RuntimeException("User name or password is incorrect");
    }
}
