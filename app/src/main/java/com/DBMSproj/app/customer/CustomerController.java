package com.DBMSproj.app.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @GetMapping("/{aadhar_id}")
    public List<Customer> getCustomer(@PathVariable Long aadhar_id) {
        List<Customer> resultSet = new ArrayList<>();
        try {
            CustomerService service = new CustomerService(new CustomerDAO());
            resultSet = service.getCustomer(aadhar_id);
        } catch (Exception e) {}
        return resultSet;
    }
    @GetMapping("/shivamh")
    public String shivam() {return "Ruchir";}
}
