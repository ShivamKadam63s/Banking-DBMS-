package com.DBMSproj.app.loan;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanDAO LoanDAO;
    public LoanService(LoanDAO LoanDAO) {
        this.LoanDAO = LoanDAO;
    }

    public List<Loan> getLoan(Long aadhar_id){
        return LoanDAO.findById(aadhar_id);
    }

}