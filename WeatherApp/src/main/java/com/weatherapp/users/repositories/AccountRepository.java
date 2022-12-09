package com.weatherapp.users.repositories;

import com.weatherapp.users.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
boolean existsAccountByEmail(String email);
Account findAccountByEmail(String email);
boolean existsAccountByUsername(String username);
Account findAccountById(long id);
Account findAccountByVerificationToken(String verificationToken);
}
