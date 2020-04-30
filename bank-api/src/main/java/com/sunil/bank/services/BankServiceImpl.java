package com.sunil.bank.services;

import com.sunil.bank.models.Account;
import com.sunil.bank.models.Customer;
import com.sunil.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{

    private final CustomerRepository customerRepository;

    public  BankServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<Customer> getCustomersByAccountId(Long accountId) {
        return customerRepository.findAllByAccountsId(accountId);
    }

    @Override
    @Transactional
    public List<Account> getAccountsByCustomerId(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.isPresent() ? customer.get().getAccounts() : null;
    }

    @Override
    @Transactional
    public boolean matchCustomerAccount(Long customerId, Long accountId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            List<Account> accounts = customer.get().getAccounts();
            Optional<Account> account = accounts.stream().filter(acc -> acc.getId().equals(accountId)).findAny();
            return account.isPresent();
        }
        return false;
    }
}
