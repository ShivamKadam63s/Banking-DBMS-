package com.DBMSproj.app.banktransaction;
import java.math.BigDecimal;
import java.util.*;
import java.sql.Date;
import java.sql.*;
import org.springframework.stereotype.Service;


@Service
public class BankTransactionService {
    private final BankTransactionDAO bankTransactionDAO;
    public BankTransactionService(BankTransactionDAO bankTransactionDAO) {
        this.bankTransactionDAO = bankTransactionDAO;
    }

    public List<BankTransaction> getBankTransaction(Long aadhar_id){
        return bankTransactionDAO.findById(aadhar_id);
    }

    public BankTransaction createBankTransactions(
        
    Long Reciever_acc,
    Date transaction_date,
    String transaction_type,
    BigDecimal Amount,
    Long acc_id){
        Connection connection = bankTransactionDAO.getConnection();
        String sql1 = "Update BankAccount set acc_balance = acc_balance - ? where acc_id = ?";
        String sql2 = "Update BankAccount set acc_balance = acc_balance + ? where acc_id = ?";
        try {
            PreparedStatement stmnt = connection.prepareStatement(sql1);
            stmnt.setBigDecimal(1, Amount);
            stmnt.setLong(2, acc_id);
            stmnt.executeUpdate();
            PreparedStatement stmnt2 = connection.prepareStatement(sql2);
            stmnt2.setBigDecimal(1, Amount);
            stmnt2.setLong(2, Reciever_acc);
            stmnt2.executeUpdate();
        } catch (SQLException e) {
        } catch (Exception e) {
            ;
        }
        
        return bankTransactionDAO.createBankTransaction(
        Reciever_acc,
        transaction_date,
        transaction_type,
        Amount,
        acc_id);
    }

    public List<BankTransaction> getBankTransactionforBankAccount(Long acc_id) {
        return bankTransactionDAO.findByAccId(acc_id);
    }
}
