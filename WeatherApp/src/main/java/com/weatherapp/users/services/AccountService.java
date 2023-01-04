package com.weatherapp.users.services;

import com.weatherapp.common.dtos.AccountDto;
import com.weatherapp.common.dtos.AccountResponseDto;
import com.weatherapp.common.dtos.json.CityCoordinatesDto;
import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.json.OpenWeatherResponseDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.users.models.Account;
import com.weatherapp.users.models.City;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.http.ResponseEntity;

public interface AccountService {

  AccountDto loginUser(LoginRequestDto loginRequestDto);

  void validateAndRegister(RegisterRequestDto registerRequestDto);

  Account findUserById(Long id);

  boolean existsAccountByUsername(String username);

  boolean validateEmail(String email);

  void deleteAccountById(long id) throws AccountNotFoundException;

  List<AccountResponseDto> findAll();

  AccountResponseDto updateAccountById(long id, RegisterRequestDto registerRequestDto);

  void sendVerificationEmail(Account account);

  void assignVerificationToken(Account account);

  void verifyAccount(String verificationToken) throws AccountNotFoundException;

  void saveCityForUser(String token, ResponseEntity<CityCoordinatesDto[]> response);

  OpenWeatherResponseDto showOpenWeatherResponseDto(String token, String cityName);
  OpenWeatherResponseDto showOpenWeatherResponseDtoScheduled(String cityName);
  List<City> findAllCities();
  boolean isTemperatureUnderZero(String cityName);
  void reportTemperatureUnderZero(String CityName);
  void sendNotificationEmail(String subject, String body, String recieverEmail);
  City returnCityByName(List<City> cities, String cityName);
}
