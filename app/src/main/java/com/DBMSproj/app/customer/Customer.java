package com.DBMSproj.app.customer;

import java.sql.*;
public record Customer(
    Long aadhar_id,
    String mobile_no,
    String fname,
    String mname,
    String lname,
    String address,
    Date DOB,
    String Email,
    String Gender
) {
}