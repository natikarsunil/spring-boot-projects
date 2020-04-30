package com.sunil.bank.services;

import com.sunil.bank.models.Account;
import com.sunil.bank.models.Customer;

import java.util.List;

public interface BankService {

    List<Customer> getCustomersByAccountId(Long accountId);

    List<Account> getAccountsByCustomerId(Long customerId);

    boolean matchCustomerAccount(Long customerId, Long accountId);
}
