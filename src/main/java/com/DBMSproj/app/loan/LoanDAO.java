package com.DBMSproj.app.loan;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
// import java.util.*;
import java.sql.*;
import java.util.Random;
@Repository
public class LoanDAO extends TableDAO<Loan> {
    public LoanDAO() throws SQLException{
        super(
            DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem?user=user&password=314159"),
            "Loan",
            "Loan_id", 
            new String[]{},
            new String[] {
                "Loan_id",
                "Rate_of_Interest",
                "Loan_Amount",
                "Loan_Type",
                "customer_id",
                "address"}
            );
    }
    public Loan mapResultSetToEntity(ResultSet rs) throws SQLException {
        Loan Loan = new Loan(
            rs.getLong("Loan_id"),
            rs.getBigDecimal("Rate_of_Interest"),
            rs.getBigDecimal("Loan_Amount"),
            rs.getString("Loan_Type"),
            rs.getLong("customer_id"),
            rs.getInt("duration")
        );
        return Loan;
    }
    // public List<Loan> findById(Long aadhar_id){
    //     return super.findById(aadhar_id);
    // }
    public Loan createLoan(
        BigDecimal Rate_of_Interest,
        BigDecimal Loan_Amount,
        String Loan_Type,
        Long customer_id,
        Integer duration
    ) {
        System.out.println(Loan_Type);
        String sql = "Insert into Loan value (?, ?, ?, ?, ?, ?)";
        int attempts = 0;
        while (attempts < 10) {
            try (PreparedStatement stmnt = connection.prepareStatement(sql)) {
                Long Loan_id = new Random().nextLong(10000l);
                stmnt.setLong(1, Loan_id);
                stmnt.setBigDecimal(2, Rate_of_Interest);
                stmnt.setBigDecimal(3, Loan_Amount);    
                stmnt.setString(4, Loan_Type);
                stmnt.setLong(5, customer_id);
                stmnt.setLong(6, duration);
                stmnt.executeUpdate();
                return findById(Loan_id).get(0);
            } catch (Exception e) {
                return new Loan(null, null, null, null, null, null);
            }
        }
        return new Loan(null, null, null, null, null, null);
    }

}