package com.sunil.bank.services;

import com.sunil.bank.jwt.JwtUtils;
import com.sunil.bank.models.Account;
import com.sunil.bank.models.Customer;
import com.sunil.bank.models.JwtResponse;
import com.sunil.bank.models.SignupRequest;
import com.sunil.bank.repository.AccountRepository;
import com.sunil.bank.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder encoder;

    public AuthServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, JwtUtils jwtUtils, PasswordEncoder encoder){
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @Override
    public JwtResponse generateJwt(Authentication authentication) {
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse(
                token,
                userDetails.getId(),
                userDetails.getUsername());

        return jwtResponse;
    }

    @Override
    @Transactional
    public Customer register(SignupRequest signupRequest) {
        Customer customer = buildCustomer(signupRequest);
        List<Account> accounts = accountRepository.saveAll(signupRequest.getAccounts());
        customer.setAccounts(accounts);
        return customerRepository.save(customer);
    }

    @Override
    public boolean existByUserName(String userName) {
        return customerRepository.existsByUserName(userName);
    }

    private Customer buildCustomer(final SignupRequest signupRequest){
        Customer customer = new Customer();
        customer.setUserName(signupRequest.getUserName());
        customer.setPassword(encoder.encode(signupRequest.getPassword()));
        customer.setForeName(signupRequest.getForeName());
        customer.setSurName(signupRequest.getSurName());
        customer.setDateOfBirth(signupRequest.getDateOfBirth());
        return customer;
    }
}
