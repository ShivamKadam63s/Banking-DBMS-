package com.DBMSproj.app.loan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/loan")
public class LoanController {
    
    @GetMapping("/{loan_id}")
    public List<Loan> getLoan(@PathVariable Long loan_id) {
        List<Loan> resultSet = new ArrayList<>();
        try {
            LoanService service = new LoanService(new LoanDAO());
            resultSet = service.getLoan(loan_id);
        } catch (Exception e) {}
        return resultSet;
    }
}