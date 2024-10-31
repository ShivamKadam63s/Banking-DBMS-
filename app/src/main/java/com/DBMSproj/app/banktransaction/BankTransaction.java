package com.DBMSproj.app.banktransaction;
import java.sql.Date;
import java.math.BigDecimal;

public record BankTransaction(
    Long transaction_id,
    String Reciever_name,
    Long Reciever_acc,
    Date transaction_date,
    String transaction_type,
    BigDecimal Amount,
    Long acc_id
) {
    
}
