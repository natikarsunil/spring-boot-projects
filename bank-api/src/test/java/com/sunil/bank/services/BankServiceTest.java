package com.sunil.bank.services;

import com.sunil.bank.models.Account;
import com.sunil.bank.models.Customer;
import com.sunil.bank.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BankServiceTest {

    private BankService bankService;

    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        bankService = new BankServiceImpl(customerRepository);
    }

    @Test
    public void testGetCustomersByAccountId_returnNull(){
        Mockito.when(customerRepository.findAllByAccountsId(ArgumentMatchers.anyLong())).thenReturn(null);
        List<Customer> customers = bankService.getCustomersByAccountId(1L);
        Assertions.assertNull(customers);
    }

    @Test
    public void testGetCustomersByAccountId_returnList(){
        Customer customer = buildCustomer();
        Mockito.when(customerRepository.findAllByAccountsId(ArgumentMatchers.anyLong())).thenReturn(Arrays.asList(customer));
        List<Customer> customers = bankService.getCustomersByAccountId(1L);
        Assertions.assertNotNull(customers);
    }

    @Test
    public void testGetAccountsByCustomerId_returnNull(){
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        List<Account> accounts = bankService.getAccountsByCustomerId(1L);
        Assertions.assertNull(accounts);
    }

    @Test
    public void testGetAccountsByCustomerId_returnList(){
        Customer customer = buildCustomer();
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));
        List<Account> accounts = bankService.getAccountsByCustomerId(1L);
        Assertions.assertNotNull(accounts);
    }

    @Test
    public void testMatchCustomerAccount_customerNotFound(){
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Assertions.assertFalse(bankService.matchCustomerAccount(1L, 2L));
    }

    @Test
    public void testMatchCustomerAccount_accountNotFound(){
        Customer customer = buildCustomer();
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));
        Assertions.assertFalse(bankService.matchCustomerAccount(1L, 2L));
    }

    @Test
    public void testMatchCustomerAccount_customerAccountNotMatch(){
        Customer customer = buildCustomer();
        Account account = new Account();
        account.setId(3L);
        customer.setAccounts(Arrays.asList(account));
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));
        Assertions.assertFalse(bankService.matchCustomerAccount(1L, 2L));
    }

    @Test
    public void testMatchCustomerAccount_customerAccountMatch(){
        Customer customer = buildCustomer();
        Account account = new Account();
        account.setId(2L);
        customer.setAccounts(Arrays.asList(account));
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));
        Assertions.assertTrue(bankService.matchCustomerAccount(1L, 2L));
    }

    private Customer buildCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUserName("abc");
        customer.setForeName("abc");
        customer.setSurName("xyz");
        return customer;
    }
}
