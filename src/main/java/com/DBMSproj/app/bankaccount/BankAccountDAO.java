package com.DBMSproj.app.bankaccount;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;
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
                "aadhar_id"}
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
            rs.getLong("aadhar_id")
        );
        return BankAccount;
    }
    public static BankAccount staticMapResultSetToEntity(ResultSet rs) throws SQLException {
        BankAccount BankAccount = new BankAccount(
            rs.getLong("acc_id"),
            rs.getString("pass"),
            rs.getString("username"),
            rs.getBigDecimal("acc_balance"),
            rs.getBigDecimal("transaction_limit"),
            rs.getString("acc_type"),
            rs.getLong("aadhar_id")
        );
        return BankAccount;
    }
    public List<BankAccount> findById(Long aadhar_id){
        return super.findById(aadhar_id);
    }

    public List<BankAccount> bankAccountsForUser(Long aadhar_id) {
        String sql = "Select * from BankAccount where aadhar_id = ?";
        List<BankAccount> accs = new ArrayList<>();
        try {
            PreparedStatement pS = connection.prepareStatement(sql);
            pS.setLong(1, aadhar_id);
            ResultSet rS = pS.executeQuery();
            while (rS.next()) {
                accs.add(mapResultSetToEntity(rS));
            }
            return accs;
        } catch (SQLException e) {return accs; }//im not handling this rn
    }

    public BigDecimal getAccBalance(Long acc_id) {
        String sql = "Select * from BankAccount where acc_id = ?";
        List<BankAccount> accs = new ArrayList<>();
        try {
            PreparedStatement pS = connection.prepareStatement(sql);
            pS.setLong(1, acc_id);
            ResultSet rS = pS.executeQuery();
            while (rS.next()) {
                accs.add(mapResultSetToEntity(rS));
            }
            return accs.get(0).acc_balance();
        } catch (SQLException e) {return accs.get(0).acc_balance(); }//im not handling this rn
    }

    public BankAccount setAccBalance(Long acc_id, BigDecimal acc_balance) {
        String sql = "Update BankAccount set acc_balance = ? where acc_id = ?";
        try {
            PreparedStatement pS = connection.prepareStatement(sql);
            pS.setBigDecimal(1, acc_balance);
            pS.setLong(2, acc_id);
            pS.executeUpdate();
            return findById(acc_id).get(0);
        } catch (SQLException e) {return findById(acc_id).get(0); }//im not handling this rn
    }
}

