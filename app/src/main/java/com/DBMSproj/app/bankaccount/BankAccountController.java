package com.DBMSproj.app.bankaccount;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.DBMSproj.app.shared.*;


import com.DBMSproj.app.banktransaction.*;

import java.math.BigDecimal;
import java.util.*;
@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

    private final BankAccountService service;
    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping("/{acc_id}")
    public BankAccount getBankAccountWithId(@PathVariable Long acc_id) {
        return service.getBankAccountWithId(acc_id);
    }
    @GetMapping("/balance/{acc_id}")
    public ResponseEntity<BankAccount> balance(@PathVariable Long acc_id) {
        return ResponseEntity.ok(service.getBankAccountWithId(acc_id));
    }
    @GetMapping("/customer/{aadhar_id}")
    public ResponseEntity<List<BankAccount>> bankAccountsForUser(@PathVariable Long aadhar_id) {
        return ResponseEntity.ok(service.bankAccountsForUser(aadhar_id));
    }
    @GetMapping("/customer/data/{aadhar_id}")
    public List<BankAccount> databankAccountsForUser(@PathVariable Long aadhar_id) {
        return (service.bankAccountsForUser(aadhar_id));
    }
    @PostMapping("/deposit")
    public ResponseEntity<BankAccount> deposit(@RequestBody AccIdAmount accIdAmount) {
        Long acc_id = accIdAmount.acc_id();
        BigDecimal amount = accIdAmount.amount();
        return ResponseEntity.ok(service.deposit(acc_id, amount));
    }
    @PostMapping("/withdraw")
    public ResponseEntity<BankAccount> withdraw(@RequestBody AccIdAmount accIdAmount) {
        Long acc_id = accIdAmount.acc_id();
        BigDecimal amount = accIdAmount.amount();
        return ResponseEntity.ok(service.withdraw(acc_id, amount));
    }
    @PostMapping("/transaction")
    public ResponseEntity<BankTransaction> transaction(@RequestBody AccId_RecieverAcc_Amount_TransactionType req) {
        Long acc_id = req.acc_id();
        Long Reciever_acc = req.Reciever_acc();
        BigDecimal Amount = req.Amount();
        String transaction_type = req.transaction_type();
        try {
            return (new BankTransactionController(new BankTransactionService(new BankTransactionDAO())).createBankTransaction(new Reciever_acctransaction_typeAmountacc_id(Reciever_acc, transaction_type, Amount, acc_id)));
        } catch (Exception e) {
            return ResponseEntity.ok(new BankTransaction(null, null, null, null, null, null, null));
        }

    }
}


record AccIdAmount(Long acc_id, BigDecimal amount) {}
record AccId_RecieverAcc_Amount_TransactionType(
    Long acc_id,
    Long Reciever_acc,
    BigDecimal Amount,
    String transaction_type
) { }

