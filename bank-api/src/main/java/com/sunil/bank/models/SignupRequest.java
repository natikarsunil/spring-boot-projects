package com.sunil.bank.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class SignupRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String foreName;

    @NotBlank
    private String surName;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    private List<Account> accounts;
}
