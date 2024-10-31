package com.DBMSproj.app.bankaccount;
import java.util.*;

import org.springframework.stereotype.Service;


@Service
public class BankAccountService {
    private final BankAccountDAO BankAccountDAO;
    public BankAccountService(BankAccountDAO BankAccountDAO) {
        this.BankAccountDAO = BankAccountDAO;
    }

    public List<BankAccount> getBankAccount(Long aadhar_id){
        return BankAccountDAO.findById(aadhar_id);
    }

    public List<BankAccount> newBankAccount(Long aadhar_id){
        return BankAccountDAO.findById(aadhar_id);
    }

}
