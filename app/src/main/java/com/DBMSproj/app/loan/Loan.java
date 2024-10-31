package com.DBMSproj.app.loan;

import java.math.BigDecimal;

public record Loan(
    Long Loan_id,
    BigDecimal Rate_of_Interest,
    BigDecimal Loan_Amount,
    String Loan_Type,
    Long customer_id
) {
    

}
