package com.weatherapp.common.exceptions.exceptionHandler;

import com.weatherapp.common.dtos.ErrorResponseDto;
import com.weatherapp.common.exceptions.CityNotFoundException;
import com.weatherapp.common.exceptions.EmailIsMissingException;
import com.weatherapp.common.exceptions.InvalidEmailException;
import com.weatherapp.common.exceptions.InvalidLoginCredentialsException;
import com.weatherapp.common.exceptions.InvalidTokenException;
import com.weatherapp.common.exceptions.PasswordIsMissingException;
import com.weatherapp.common.exceptions.ShortPasswordException;
import com.weatherapp.common.exceptions.ShortUsernameException;
import com.weatherapp.common.exceptions.UsernameMissingException;
import com.weatherapp.common.exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandler {
  private final Environment environment;

  @Autowired
  protected ExceptionHandler(Environment environment) {
    this.environment = environment;
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = {EmailIsMissingException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleEmailIsMissingException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.email_is_missing"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidEmailException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleInvalidEmailException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.invalid_email"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
      value = InvalidLoginCredentialsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleInvalidLoginCredentialsException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.invalid_login_credentials"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidTokenException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleInvalidTokenException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.invalid_token"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
      value = PasswordIsMissingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handlePasswordIsMissingException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.password_is_missing"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = ShortPasswordException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleShortPasswordException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.password_is_short"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = ShortUsernameException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleShortUsernameException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.username_is_short"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = UsernameMissingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleUsernameIsMissingException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.username_is_missing"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = UsernameTakenException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleUsernameTakenException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.username_is_taken"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(value = CityNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleCityNotFoundException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.city_not_found"));
  }
}
