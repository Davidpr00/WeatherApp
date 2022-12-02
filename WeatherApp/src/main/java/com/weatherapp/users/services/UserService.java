package com.weatherapp.users.services;

import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.common.dtos.UserDto;
import com.weatherapp.users.models.User;

public interface UserService {

  UserDto loginUser(LoginRequestDto loginRequestDto);

  void validateAndRegister(RegisterRequestDto registerRequestDto);

  User findUserById(Long id);
}
