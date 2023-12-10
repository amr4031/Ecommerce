package com.ecommerce.ecommerce.checkout;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills-apis")
@CrossOrigin(origins = "http://localhost:4202")
public class BillController {
    @Autowired
    BillService billService;
    @GetMapping("/bill/id/{bill-id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("bill-id") Long id) {
        Bill bill = billService.getBillById(id);
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getAllBills() {
//        throw new ApiRequestException("Oops m4 3aref ageeb el bills");
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }


    @PostMapping("add-bill")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        return new ResponseEntity<>(billService.addBill(bill), HttpStatus.OK);
    }
}
