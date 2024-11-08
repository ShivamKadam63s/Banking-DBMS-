package com.DBMSproj.app.banktransaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.DBMSproj.app.shared.*;


import java.math.BigDecimal;
import java.util.*;
import java.sql.Date;

@RestController
@RequestMapping("/banktransaction")
public class BankTransactionController {
    
    private final BankTransactionService service;
    public BankTransactionController(BankTransactionService service) {
        this.service = service;
    }

    @GetMapping("/{transaction_id}")
    public List<BankTransaction> getBankTransaction(@PathVariable Long transaction_id) {
        List<BankTransaction> resultSet = new ArrayList<>();
        try {
            BankTransactionService service = new BankTransactionService(new BankTransactionDAO());
            resultSet = service.getBankTransaction(transaction_id);
        } catch (Exception e) {}
        return resultSet;
    }

    @GetMapping("/bankaccount/{acc_id}")
    public List<BankTransaction> getBankTransactionforBankAccount(@PathVariable Long acc_id) {
        List<BankTransaction> resultSet = new ArrayList<>();
        try {
            BankTransactionService service = new BankTransactionService(new BankTransactionDAO());
            resultSet = service.getBankTransactionforBankAccount(acc_id);
        } catch (Exception e) {}
        return resultSet;
    }

    @PostMapping("/add")
    public ResponseEntity<BankTransaction> createBankTransaction(@RequestBody Reciever_acctransaction_typeAmountacc_id req) {
        
        Long Reciever_acc = req.Reciever_acc();
        String transaction_type = req.transaction_type();
        BigDecimal Amount = req.Amount();
        Long acc_id =  req.acc_id();
        System.out.println(req.toString());
        return ResponseEntity.ok(service.createBankTransactions(Reciever_acc, new Date(System.currentTimeMillis()), transaction_type, Amount, acc_id));
    }
}
