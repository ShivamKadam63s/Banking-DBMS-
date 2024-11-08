package com.DBMSproj.app.loan;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService service;
    public final static HashMap<String, String> _SQL_loan_type_col_names_;
    private final static HashMap<String, Double> rates;
    private final static HashMap<String, String> interest_type;
    static {
        _SQL_loan_type_col_names_ = new HashMap<>();
        _SQL_loan_type_col_names_.put("auto", "Car Loan");
        _SQL_loan_type_col_names_.put("home", "House");
        _SQL_loan_type_col_names_.put("personal", "Personal");

        rates = new HashMap<>();
        rates.put(_SQL_loan_type_col_names_.get("auto"), 0.02);
        rates.put(_SQL_loan_type_col_names_.get("home"), 0.02);
        rates.put(_SQL_loan_type_col_names_.get("personal"), 0.01);
        
        interest_type = new HashMap<>();
        interest_type.put(_SQL_loan_type_col_names_.get("auto"), "simple");
        interest_type.put(_SQL_loan_type_col_names_.get("home"), "compound");
        interest_type.put(_SQL_loan_type_col_names_.get("personal"), "compound");
    }
    
    public LoanController(LoanService service) {
        this.service = service;
    }
    @GetMapping("/{loan_id}")
    public List<Loan> getLoan(@PathVariable Long loan_id) {
        List<Loan> resultSet = new ArrayList<>();
        try {
            LoanService service = new LoanService(new LoanDAO());
            resultSet = service.getLoan(loan_id);
        } catch (Exception e) {}
        return resultSet;
    }

    @PostMapping("/credit_forecast")
    public ResponseEntity<FinalAmountROI> credit_forecast(@RequestBody AmountDurationLoanType req) {
        BigDecimal amount = req.amount();
        Integer duration = req.duration();
        String loanType = req.loanType();
        if (interest_type.get(_SQL_loan_type_col_names_.get(loanType)).equals("simple")) {
            BigDecimal finalAmount = amount.multiply(BigDecimal.valueOf(duration))
                                            .multiply(BigDecimal.valueOf(rates.get(_SQL_loan_type_col_names_.get(loanType))).add(BigDecimal.valueOf(1)));
            BigDecimal ROI = (finalAmount.subtract(amount)).divide(amount);
            //Note: for compound it makes sense, but for simple should we just return the interest_type stored interest
            return ResponseEntity.ok(new FinalAmountROI(finalAmount, ROI));
        }
        else if (interest_type.get(_SQL_loan_type_col_names_.get(loanType)).equals("compound"))
        {
            BigDecimal finalAmount = amount.multiply((BigDecimal.valueOf(rates.get(_SQL_loan_type_col_names_.get(loanType)))
                                            .add(BigDecimal.valueOf(1)))
                                            .pow(duration.intValue()));
            BigDecimal ROI = (finalAmount.subtract(amount)).divide(amount);
            return ResponseEntity.ok(new FinalAmountROI(finalAmount, ROI));
        }
        else {
            Exception e = new Exception();
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/obtain_loan")
    public ResponseEntity<Loan> obtain_loan(
        @RequestBody AmountDurationLoanTypeCustomerId req
    ) {
        System.out.println(req);
        BigDecimal amount = req.amount();
        String loanType = req.loanType();
        Integer duration = req.duration();
        Long customerId = req.customerId();
        return ResponseEntity.ok(service.createLoan(BigDecimal.valueOf(rates.get(_SQL_loan_type_col_names_.get(loanType))), amount, duration, _SQL_loan_type_col_names_.get(loanType), customerId));
    }
}

record AmountDurationLoanType(
    BigDecimal amount,
    Integer duration,
    String loanType
) {}

record FinalAmountROI(
    BigDecimal finalAmount,
    BigDecimal ROI
) {
}

record AmountDurationLoanTypeCustomerId(
    BigDecimal amount,
    Integer duration,
    String loanType,
    Long customerId
) {}
