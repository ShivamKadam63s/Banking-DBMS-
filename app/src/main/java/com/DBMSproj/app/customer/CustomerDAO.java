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
}
