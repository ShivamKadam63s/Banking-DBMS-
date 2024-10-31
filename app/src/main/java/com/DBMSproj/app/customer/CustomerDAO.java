package com.DBMSproj.app.customer;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
// import java.util.*;
import java.sql.*;
@Repository
public class CustomerDAO extends TableDAO<Customer> {
    public CustomerDAO() throws SQLException{
        super(
            DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem?user=user&password=314159"),
            "Customer",
            "aadhar_id", 
            new String[]{},
            new String[] {
                "aadhar_id",
                "mobile_no",
                "fname",
                "mname",
                "lname",
                "address",
                "DOB",
                "Email",
                "Gender"}
            );
    }
    public Customer mapResultSetToEntity(ResultSet rs) throws SQLException {
        Customer customer = new Customer(
            rs.getLong("aadhar_id"),
            rs.getString("mobile_no"),
            rs.getString("fname"),
            rs.getString("mname"),
            rs.getString("lname"),
            rs.getString("address"),
            rs.getDate("DOB"),
            rs.getString("Email"),
            rs.getString("Gender")
        );
        return customer;
    }
    // public List<Customer> findById(Long aadhar_id){
    //     return super.findById(aadhar_id);
    // }

}