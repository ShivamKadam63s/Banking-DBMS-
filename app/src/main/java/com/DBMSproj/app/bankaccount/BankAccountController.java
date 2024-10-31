package com.DBMSproj.app.bankaccount;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
    
    @GetMapping("/{acc_id}")
    public List<BankAccount> getBankAccount(@PathVariable Long acc_id) {
        List<BankAccount> resultSet = new ArrayList<>();
        try {
            BankAccountService service = new BankAccountService(new BankAccountDAO());
            resultSet = service.getBankAccount(acc_id);
        } catch (Exception e) {}
        return resultSet;
    }
}
