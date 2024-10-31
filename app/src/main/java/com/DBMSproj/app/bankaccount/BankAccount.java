package com.DBMSproj.app.bankaccount;

import java.math.BigDecimal;
public record BankAccount(
    Long acc_id,
    String pass,
    String username,
    BigDecimal acc_balance,
    BigDecimal transaction_limit,
    String acc_type,
    Long aadhar_id 
) {
    
}
