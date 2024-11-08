package com.DBMSproj.app.bankaccount;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.stereotype.Service;


@Service
public class BankAccountService {
    private final BankAccountDAO bankAccountDAO;
    public BankAccountService(BankAccountDAO BankAccountDAO) {
        this.bankAccountDAO = BankAccountDAO;
    }

    public BankAccount getBankAccountWithId(Long acc_id) {
        return bankAccountDAO.findById(acc_id).get(0);
    }

    public List<BankAccount> getBankAccount(Long acc_id) {
        return bankAccountDAO.findById(acc_id);
    }

    public List<BankAccount> bankAccountsForUser(Long aadhar_id) {
        return bankAccountDAO.bankAccountsForUser(aadhar_id);
    }
    
    public BankAccount deposit(Long acc_id, BigDecimal amount) {
        var currentBalance = bankAccountDAO.getAccBalance(acc_id);
        bankAccountDAO.setAccBalance(acc_id, amount.add(currentBalance));
        return bankAccountDAO.findById(acc_id).get(0);
    }

    public BankAccount withdraw(Long acc_id, BigDecimal amount) {
        var currentBalance = bankAccountDAO.getAccBalance(acc_id);
        bankAccountDAO.setAccBalance(acc_id, (currentBalance.subtract(amount)));
        return bankAccountDAO.findById(acc_id).get(0);
    }

    
    
}
