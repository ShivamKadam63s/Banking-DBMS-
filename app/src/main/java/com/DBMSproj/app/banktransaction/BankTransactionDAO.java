package com.DBMSproj.app.banktransaction;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
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
    
    public BankTransaction createBankTransaction( //this fucking sucks
    Long Reciever_acc,
    Date transaction_date,
    String transaction_type,
    BigDecimal Amount,
    Long acc_id) {
        Long transactionId = 0l;
        String username = "deez";
        System.out.println("My name is " + username);
        
        String sql1 = "Select username from BankAccount where acc_id = ?";
        try {
            PreparedStatement stmnt = connection.prepareStatement(sql1);
            stmnt.setLong(1, Reciever_acc);
            ResultSet rS = stmnt.executeQuery();
            rS.next();
            username = rS.getString("username");
            System.out.println("My name is " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int attempts = 0;
        while (attempts < 100) {
            try {
                transactionId = new Random().nextLong(10000l);
                String sql2 = "Insert into BankTransaction value(?, ?, ?, ?, ?, ?, ?);";
                
                PreparedStatement stmnt = connection.prepareStatement(sql2);
                stmnt.setLong(1, transactionId);
                stmnt.setString(2, username);
                stmnt.setLong(3, Reciever_acc);
                stmnt.setDate(4, transaction_date);
                stmnt.setString(5, transaction_type);
                stmnt.setBigDecimal(6, Amount);
                stmnt.setLong(7, acc_id);
                stmnt.executeUpdate();
                break;
            } catch (Exception e) {
                attempts++;
            }
        }
        return findById(Long.valueOf(transactionId)).get(0);
    }
    
    Connection getConnection() {return connection;}

    public List<BankTransaction> findByAccId(long acc_id) {
        ArrayList<BankTransaction> resultSet = new ArrayList<>();
        try {
            String sql = "Select * from "+tableName+" where acc_id = ? order by transaction_date;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, acc_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BankTransaction entity = mapResultSetToEntity(rs);
                resultSet.add(entity);
            }
        } catch(SQLException e) {}
        return resultSet;
    }
}