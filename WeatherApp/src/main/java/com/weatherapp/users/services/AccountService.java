package com.weatherapp.users.services;

import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.common.dtos.UserDto;
import com.weatherapp.users.models.Account;

public interface AccountService {

  UserDto loginUser(LoginRequestDto loginRequestDto);

  void validateAndRegister(RegisterRequestDto registerRequestDto);

  Account findUserById(Long id);

  boolean existsAccountByUsername(String username);
  boolean validateEmail(String email);
}
