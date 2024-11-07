package com.DBMSproj.app.customer;
import com.DBMSproj.app.daotemplates.*;
import org.springframework.stereotype.Repository;
// import java.util.*;
import java.sql.*;
import java.util.ArrayList;
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
                "Gender",
                "password"}
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
            rs.getString("Gender"),
            rs.getString("password")
        );
        return customer;
    }
    public Customer findByEmailPassword(String email, String password) {
        ArrayList<Customer> resultSet = new ArrayList<>();
        try {
            String sql = "Select * from "+tableName+" where Email = ? and password = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer entity = mapResultSetToEntity(rs);
                resultSet.add(entity);
            }
        } catch(SQLException e) {}
        return resultSet.get(0);
    } 
    
    // public List<Customer> findById(Long aadhar_id){
    //     return super.findById(aadhar_id);
    // }

    public Customer createCustomer(
        Long aadhar_id,
        String mobile_no,
        String fname,
        String mname,
        String lname,
        String address,
        Date DOB,
        String Email,
        String Gender,
        String password) {
            
            String sql = "Insert into Customer value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmnt = connection.prepareStatement(sql)) {
                stmnt.setLong(1, aadhar_id);
                stmnt.setString(2, mobile_no);
                stmnt.setString(3, fname);
                stmnt.setString(4, mname);
                stmnt.setString(5, lname);
                stmnt.setString(6, address);
                stmnt.setDate(7, DOB);
                stmnt.setString(8, Email);
                stmnt.setString(9, Gender);
                stmnt.setString(10, password);

                stmnt.executeUpdate();
                return findById(aadhar_id).get(0);
            } catch (Exception e) {
                return new Customer(null, null, null, null, null, null, null, null, null, null);
            }
    }
}
