package com.DBMSproj.app.loan;

import java.math.BigDecimal;
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

    public Loan createLoan(
        BigDecimal Rate_of_Interest,
        BigDecimal Loan_Amount,
        Integer duration,
        String Loan_Type,
        Long customer_id
    ) {
        return LoanDAO.createLoan(Rate_of_Interest, Loan_Amount, Loan_Type, customer_id, duration);
    }
}