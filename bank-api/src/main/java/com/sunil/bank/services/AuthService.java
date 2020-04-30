package com.sunil.bank.services;

import com.sunil.bank.models.Customer;
import com.sunil.bank.models.JwtResponse;
import com.sunil.bank.models.SignupRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {

    JwtResponse generateJwt(Authentication authentication);

    Customer register(SignupRequest signupRequest);

    boolean existByUserName(String userName);
}
