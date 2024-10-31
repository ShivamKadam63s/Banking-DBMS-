package com.DBMSproj.app.banktransaction;
import java.util.*;

import org.springframework.stereotype.Service;


@Service
public class BankTransactionService {
    private final BankTransactionDAO BankTransactionDAO;
    public BankTransactionService(BankTransactionDAO BankTransactionDAO) {
        this.BankTransactionDAO = BankTransactionDAO;
    }

    public List<BankTransaction> getBankTransaction(Long aadhar_id){
        return BankTransactionDAO.findById(aadhar_id);
    }

    public List<BankTransaction> newBankTransaction(Long aadhar_id){
        return BankTransactionDAO.findById(aadhar_id);
    }

}
