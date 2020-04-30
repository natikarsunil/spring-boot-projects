package com.sunil.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CustomerAccountNotMatchingException extends RuntimeException{

    public CustomerAccountNotMatchingException(String exception){
        super(exception);
    }
}
