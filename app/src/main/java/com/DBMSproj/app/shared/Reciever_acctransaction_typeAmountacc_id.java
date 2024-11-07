package com.DBMSproj.app.shared;
import java.math.BigDecimal;
public record Reciever_acctransaction_typeAmountacc_id(
    Long Reciever_acc,
    String transaction_type,
    BigDecimal Amount,
    Long acc_id
) {}
