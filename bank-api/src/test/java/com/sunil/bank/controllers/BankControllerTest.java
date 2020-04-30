package com.sunil.bank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunil.bank.models.Account;
import com.sunil.bank.models.Customer;
import com.sunil.bank.services.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class BankControllerTest {

    BankService bankService;

    MockMvc mockMvc;

    BankController bankController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        bankService = Mockito.mock(BankService.class);
        bankController = new BankController(bankService);
        mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();
    }

    @Test
    public void testGetCustomersByAccountId_returnNull() throws Exception {
        String uri = "/v1/api/customers/accounts/2";
        Mockito.when(bankService.getCustomersByAccountId(ArgumentMatchers.anyLong())).thenReturn(null);
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("getCustomersByAccountId"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testGetCustomersByAccountId_returnList() throws Exception {
        Customer customer = buildCustomer();
        Mockito.when(bankService.getCustomersByAccountId(ArgumentMatchers.anyLong())).thenReturn(Arrays.asList(customer));
        String uri = "/v1/api/customers/accounts/2";
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("getCustomersByAccountId"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].userName").value("abc"))
                .andReturn();
    }

    @Test
    public void testGetAccountsByCustomerId_returnNull() throws Exception {
        String uri = "/v1/api/customers/2/accounts";
        Mockito.when(bankService.getAccountsByCustomerId(ArgumentMatchers.anyLong())).thenReturn(null);
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("getAccountsByCustomerId"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testGetAccountsByCustomerId_returnEmptyList() throws Exception {
        String uri = "/v1/api/customers/2/accounts";
        Mockito.when(bankService.getAccountsByCustomerId(ArgumentMatchers.anyLong())).thenReturn(new ArrayList<>());
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("getAccountsByCustomerId"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testGetAccountsByCustomerId_returnList() throws Exception {
        String uri = "/v1/api/customers/2/accounts";
        Account account = new Account();
        account.setId(1L);
        Mockito.when(bankService.getAccountsByCustomerId(ArgumentMatchers.anyLong())).thenReturn(Arrays.asList(account));
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("getAccountsByCustomerId"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andReturn();
    }

    @Test
    public void testMatchCustomerAccount_customerAccountNotMatch() throws Exception {
        String uri = "/v1/api/customers/2/accounts/2/validate";
        Mockito.when(bankService.matchCustomerAccount(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(false);
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("matchCustomerAccount"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(status().isExpectationFailed())
                .andReturn();
    }

    @Test
    public void testMatchCustomerAccount_customerAccountMatch() throws Exception {
        String uri = "/v1/api/customers/2/accounts/2/validate";
        Mockito.when(bankService.matchCustomerAccount(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(true);
        mockMvc.perform(get(uri))
                .andExpect(handler().methodName("matchCustomerAccount"))
                .andExpect(handler().handlerType(BankController.class))
                .andExpect(jsonPath("$.message").value("Customer account details are valid!"))
                .andExpect(status().isOk())
                .andReturn();
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
