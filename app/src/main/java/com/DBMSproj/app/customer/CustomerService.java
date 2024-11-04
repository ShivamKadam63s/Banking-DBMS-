package com.DBMSproj.app.customer;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getCustomer(Long aadhar_id){
        return customerDAO.findById(aadhar_id);
    }

    public Customer findByEmailPassword(String email, String password) {
        return customerDAO.findByEmailPassword(email, password);
    }

    public boolean authenticateEmailPassword(String email, String password) {
        return customerDAO.findByEmailPassword(email, password) != null;
    }

}
