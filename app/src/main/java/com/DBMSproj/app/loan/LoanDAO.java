package com.DBMSproj.app.loan;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
// import java.util.*;
import java.sql.*;
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
            rs.getLong("customer_id")
        );
        return Loan;
    }
    // public List<Loan> findById(Long aadhar_id){
    //     return super.findById(aadhar_id);
    // }

}