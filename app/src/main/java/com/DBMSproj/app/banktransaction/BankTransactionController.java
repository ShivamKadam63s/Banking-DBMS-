package com.DBMSproj.app.banktransaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/banktransaction")
public class BankTransactionController {
    
    @GetMapping("/{transaction_id}")
    public List<BankTransaction> getBankTransaction(@PathVariable Long transaction_id) {
        List<BankTransaction> resultSet = new ArrayList<>();
        try {
            BankTransactionService service = new BankTransactionService(new BankTransactionDAO());
            resultSet = service.getBankTransaction(transaction_id);
        } catch (Exception e) {}
        return resultSet;
    }
}
