package com.weatherapp.users.serviceImplementations;

import static org.junit.jupiter.api.Assertions.*;

import com.weatherapp.common.dtos.AccountDto;
import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.common.exceptions.EmailIsMissingException;
import com.weatherapp.common.exceptions.InvalidEmailException;
import com.weatherapp.common.exceptions.InvalidLoginCredentialsException;
import com.weatherapp.common.exceptions.InvalidTokenException;
import com.weatherapp.common.exceptions.PasswordIsMissingException;
import com.weatherapp.common.exceptions.ShortPasswordException;
import com.weatherapp.common.exceptions.ShortUsernameException;
import com.weatherapp.common.exceptions.UsernameMissingException;
import com.weatherapp.common.exceptions.UsernameTakenException;
import com.weatherapp.security.JwtUtil;
import com.weatherapp.users.models.Account;
import com.weatherapp.users.repositories.AccountRepository;
import com.weatherapp.users.repositories.CityRepository;
import com.weatherapp.users.services.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

class AccountServiceImplementationTest {

  private final AccountRepository mockedAccountRepository = Mockito.mock(AccountRepository.class);
  private final CityRepository mockedCityRepository  = Mockito.mock(CityRepository.class);
  private final JavaMailSender mockedJavaMailSender = Mockito.mock(JavaMailSender.class);
  private final JwtUtil mockedJwtUtil = Mockito.mock(JwtUtil.class);

  private final AccountService mockedService = new AccountServiceImplementation(mockedAccountRepository,mockedCityRepository,mockedJavaMailSender,mockedJwtUtil);

  private final Account testAccount = new Account("David", "1234456655433", "email@email.com");
  private final LoginRequestDto loginRequestDto = new LoginRequestDto("email@email.com","1234456655433");
  private final RegisterRequestDto registerRequestDto = new RegisterRequestDto("david","1234456655433","email@email.com");

  @Test
  void can_loginUser() {
    Mockito.when(mockedAccountRepository
        .findAccountByEmail(loginRequestDto.getEmail())).thenReturn(testAccount);
    Mockito.when(mockedAccountRepository.existsAccountByEmail(loginRequestDto.getEmail())).thenReturn(true);

    assertEquals("David", mockedService.loginUser(loginRequestDto).getUsername());
  }
  @Test
  void can_respond_with_EmailMissingException_when_loginUser() {
    loginRequestDto.setEmail(null);
    assertThrows(EmailIsMissingException.class, () -> mockedService.loginUser(loginRequestDto));
  }
  @Test
  void can_respond_with_PasswordIsMissingException_when_loginUser() {
    loginRequestDto.setPassword(null);
    assertThrows(PasswordIsMissingException.class, () -> mockedService.loginUser(loginRequestDto));
  }
  @Test
  void can_respond_with_InvalidLoginCredentialsException_when_loginUser() {
    assertThrows(InvalidLoginCredentialsException.class, () -> mockedService.loginUser(loginRequestDto));
  }

  @Test
  void can_respond_with_UsernameMissingException_when_validateAndRegister() {
    registerRequestDto.setUsername("");
    assertThrows(UsernameMissingException.class, () -> mockedService.validateAndRegister(registerRequestDto));
  }
  @Test
  void can_respond_with_PasswordIsMissingException_when_validateAndRegister() {
    registerRequestDto.setPassword("");
    assertThrows(PasswordIsMissingException.class, () -> mockedService.validateAndRegister(registerRequestDto));

  }
  @Test
  void can_respond_with_EmailIsMissingException_when_validateAndRegister() {
    registerRequestDto.setEmail("");
    assertThrows(EmailIsMissingException.class, () -> mockedService.validateAndRegister(registerRequestDto));

  }
  @Test
  void can_respond_with_UsernameTakenException_when_validateAndRegister() {
    Mockito.when(mockedService.existsAccountByUsername(registerRequestDto.getUsername())).thenReturn(true);
    assertThrows(UsernameTakenException.class, () -> mockedService.validateAndRegister(registerRequestDto));

  }
  @Test
  void can_respond_with_ShortUsernameException_when_validateAndRegister() {
    registerRequestDto.setUsername("d");
    assertThrows(ShortUsernameException.class, () -> mockedService.validateAndRegister(registerRequestDto));

  }
  @Test
  void can_respond_with_ShortPasswordException_when_validateAndRegister() {
    registerRequestDto.setPassword("d");
    assertThrows(ShortPasswordException.class, () -> mockedService.validateAndRegister(registerRequestDto));

  }
  @Test
  void can_respond_with_InvalidEmailException_when_validateAndRegister() {
    registerRequestDto.setEmail("da");
    assertThrows(InvalidEmailException.class, () -> mockedService.validateAndRegister(registerRequestDto));

  }
  @Test
  void can_validateAndRegister() {
    assertEquals(1,mockedAccountRepository.findAll().size());

  }
}