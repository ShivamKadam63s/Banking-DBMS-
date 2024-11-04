package com.DBMSproj.app.bankaccount;
import java.util.*;
import org.springframework.stereotype.Service;


@Service
public class BankAccountService {
    private final BankAccountDAO bankAccountDAO;
    public BankAccountService(BankAccountDAO BankAccountDAO) {
        this.bankAccountDAO = BankAccountDAO;
    }

    public List<BankAccount> getBankAccount(Long aadhar_id) {
        return bankAccountDAO.findById(aadhar_id);
    }

    public List<BankAccount> newBankAccount(Long aadhar_id) {
        return bankAccountDAO.findById(aadhar_id);
    }
}
