package com.weatherapp.users.services;

import com.weatherapp.common.dtos.AccountResponseDto;
import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.common.dtos.AccountDto;
import com.weatherapp.users.models.Account;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;

public interface AccountService {

  AccountDto loginUser(LoginRequestDto loginRequestDto);

  void validateAndRegister(RegisterRequestDto registerRequestDto);

  Account findUserById(Long id);

  boolean existsAccountByUsername(String username);
  boolean validateEmail(String email);

  void deleteAccountById(long id) throws AccountNotFoundException;

  List<AccountResponseDto> findAll();

  AccountResponseDto updateAccountById(long id, RegisterRequestDto registerRequestDto);
}
