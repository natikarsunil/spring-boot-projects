package com.sunil.bank.controllers;

import com.sunil.bank.exceptions.AccountNotFoundException;
import com.sunil.bank.exceptions.CustomerAccountNotMatchingException;
import com.sunil.bank.exceptions.CustomerNotFoundException;
import com.sunil.bank.models.Account;
import com.sunil.bank.models.Customer;
import com.sunil.bank.models.MessageResponse;
import com.sunil.bank.services.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable("customerId") Long customerId){
        List<Account> accounts = bankService.getAccountsByCustomerId(customerId);
        if(accounts==null || accounts.isEmpty()){
            throw new AccountNotFoundException("Accounts for customer id "+customerId+ " not found");
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/customers/accounts/{accountId}")
    public ResponseEntity<?> getCustomersByAccountId(@PathVariable("accountId") Long accountId){
        List<Customer> customers = bankService.getCustomersByAccountId(accountId);
        if(customers==null || customers.isEmpty()){
            throw new CustomerNotFoundException("Customers for account id "+accountId+ " not found");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{customerId}/accounts/{accountId}/validate")
    public ResponseEntity<?> matchCustomerAccount(@PathVariable("customerId") Long customerId,
                                                  @PathVariable("accountId") Long accountId){
        if(!bankService.matchCustomerAccount(customerId, accountId))
            throw new CustomerAccountNotMatchingException("Customer account details not matching");

        return ResponseEntity.ok(new MessageResponse("Customer account details are valid!"));
    }
}
