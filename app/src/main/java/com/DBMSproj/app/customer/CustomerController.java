package com.DBMSproj.app.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
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

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody EmailPassword emailPassword) {
        try {
            var service = new CustomerService(new CustomerDAO());
            String email = emailPassword.email(),
            password = emailPassword.password();
            if (service
                .authenticateEmailPassword(
                    email,
                    password)) {
                return ResponseEntity.ok(service.findByEmailPassword(email, password));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

record EmailPassword(String email, String password) {}