package com.DBMSproj.app.banktransaction;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.math.BigDecimal;
import java.sql.*;
@Repository
public class BankTransactionDAO extends TableDAO<BankTransaction> {
    public BankTransactionDAO() throws SQLException{
        super(
            DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem?user=user&password=314159"),
            "BankTransaction",
            "transaction_id", 
            new String[]{"acc_id"},
            new String[] {
                "transaction_id",
                "Reciever_name",
                "Reciever_acc",
                "transaction_date",
                "transaction_type",
                "Amount",
                "acc_id"}
            );
    }
    public BankTransaction mapResultSetToEntity(ResultSet rs) throws SQLException {
        BankTransaction BankTransaction = new BankTransaction(
            rs.getLong("transaction_id"),
            rs.getString("Reciever_name"),
            rs.getLong("Reciever_acc"),
            rs.getDate("transaction_date"),
            rs.getString("transaction_type"),
            rs.getBigDecimal("Amount"),
            rs.getLong("acc_id")
        );
        return BankTransaction;
    }
    public List<BankTransaction> findById(Long aadhar_id){
        return super.findById(aadhar_id);
    }
}