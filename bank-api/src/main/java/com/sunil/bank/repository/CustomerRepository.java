package com.sunil.bank.repository;

import com.sunil.bank.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findById(Long customerId);
    List<Customer> findAllByAccountsId(Long accountId);
    Boolean existsByUserName(String userName);
}
