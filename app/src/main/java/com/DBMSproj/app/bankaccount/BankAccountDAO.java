package com.DBMSproj.app.bankaccount;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.sql.*;
@Repository
public class BankAccountDAO extends TableDAO<BankAccount> {
    public BankAccountDAO() throws SQLException{
        super(
            DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem?user=user&password=314159"),
            "BankAccount",
            "acc_id", 
            new String[]{"aadhar_id"},
            new String[] {
                "acc_id",
                "pass",
                "username",
                "acc_balance",
                "transaction_limit",
                "acc_type",
                "aadhar_id "}
            );
    }
    public BankAccount mapResultSetToEntity(ResultSet rs) throws SQLException {
        BankAccount BankAccount = new BankAccount(
            rs.getLong("acc_id"),
            rs.getString("pass"),
            rs.getString("username"),
            rs.getBigDecimal("acc_balance"),
            rs.getBigDecimal("transaction_limit"),
            rs.getString("acc_type"),
            rs.getLong("aadhar_id ")
        );
        return BankAccount;
    }
    public List<BankAccount> findById(Long aadhar_id){
        return super.findById(aadhar_id);
    }
    
}