package com.ecommerce.ecommerce.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(Long id) {
        return billRepository.getById(id);
    }

    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }


    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }


    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }
}
