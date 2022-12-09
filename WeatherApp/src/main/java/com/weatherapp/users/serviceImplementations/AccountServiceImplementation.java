package com.weatherapp.users.serviceImplementations;

import com.weatherapp.common.dtos.AccountDto;
import com.weatherapp.common.dtos.AccountResponseDto;
import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.common.exceptions.EmailIsMissingException;
import com.weatherapp.common.exceptions.InvalidEmailException;
import com.weatherapp.common.exceptions.InvalidLoginCredentialsException;
import com.weatherapp.common.exceptions.PasswordIsMissingException;
import com.weatherapp.common.exceptions.ShortPasswordException;
import com.weatherapp.common.exceptions.ShortUsernameException;
import com.weatherapp.common.exceptions.UsernameMissingException;
import com.weatherapp.common.exceptions.UsernameTakenException;
import com.weatherapp.users.models.Account;
import com.weatherapp.users.repositories.AccountRepository;
import com.weatherapp.users.services.AccountService;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplementation implements AccountService {

  private final AccountRepository accountRepository;
  private final JavaMailSender javaMailSender;

  public AccountServiceImplementation(
      AccountRepository accountRepository, JavaMailSender javaMailSender) {
    this.accountRepository = accountRepository;
    this.javaMailSender = javaMailSender;
  }

  @Override
  public AccountDto loginUser(LoginRequestDto loginRequestDto) {
    if (loginRequestDto.getEmail() == null) {
      throw new EmailIsMissingException();
    } else if (loginRequestDto.getPassword() == null) {
      throw new PasswordIsMissingException();
    } else if (!accountRepository.existsAccountByEmail(loginRequestDto.getEmail()) || !accountRepository.findAccountByEmail(
        loginRequestDto.getEmail()).getPassword().equals(loginRequestDto.getPassword())) {
      throw new InvalidLoginCredentialsException();
    }
    AccountDto accountDto = new AccountDto(accountRepository.findAccountByEmail(loginRequestDto.getEmail()).getId(),accountRepository.findAccountByEmail(loginRequestDto.getEmail()).getUsername());
    return accountDto;
  }

  @Override
  public void validateAndRegister(RegisterRequestDto registerRequestDto) {
    if (registerRequestDto.getUsername().isEmpty()) {
      throw new UsernameMissingException();
    } else if (registerRequestDto.getPassword().isEmpty()) {
      throw new PasswordIsMissingException();
    } else if (registerRequestDto.getEmail().isEmpty()) {
      throw new EmailIsMissingException();
    }
    if (existsAccountByUsername(registerRequestDto.getUsername())) {
      throw new UsernameTakenException();
    } else if (registerRequestDto.getUsername().length() < 4) {
      throw new ShortUsernameException();
    } else if (registerRequestDto.getPassword().length() < 8) {
      throw new ShortPasswordException();
    } else if (!validateEmail(registerRequestDto.getEmail())) {
      throw new InvalidEmailException();
    }

      Account account = new Account(registerRequestDto.getUsername(), registerRequestDto.getEmail(), registerRequestDto.getPassword());
      assignVerificationToken(account);
      accountRepository.save(account);
      sendVerificationEmail(account);



    }

  public void sendVerificationEmail(Account account) {
    String subject = "Verify your myEbay account! ";
    String body = "Please, click the link below to verify your account: <br> "
        + "http://localhost:8080/users/verify/"
        + account.getVerificationToken();

    javaMailSender.send( mimeMessage -> {
      MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      mimeMsgHelperObj.setTo(account.getEmail());
      mimeMsgHelperObj.setText(body, true);
      mimeMsgHelperObj.setSubject(subject);
    });
  }

  @Override
  public void assignVerificationToken(Account account) {
    String verificationCode = UUID.randomUUID().toString();
    account.setVerificationToken(verificationCode);
    accountRepository.save(account);
  }

  @Override
  public void verifyAccount(String verificationToken) throws AccountNotFoundException {
    Account tobeVerified = accountRepository.findAccountByVerificationToken(verificationToken);
    if(tobeVerified == null){
      throw new AccountNotFoundException();
    } else {
      tobeVerified.setVerifiedAt(LocalDateTime.now().toString());
      accountRepository.save(tobeVerified);
    }
  }

  @Override
  public Account findUserById(Long id) {
    return accountRepository.findAccountById(id);
  }

  @Override
  public boolean existsAccountByUsername(String username) {
    return accountRepository.existsAccountByUsername(username);
  }

  @Override
  public boolean validateEmail(String email) {
    boolean isValid = false;
    try {
      InternetAddress internetAddress = new InternetAddress(email);
      internetAddress.validate();
      isValid = true;
    } catch (AddressException addressException) {
      System.out.println("Email is not valid" + addressException.getMessage());
    }
    return isValid;
  }

  @Override
  public void deleteAccountById(long id) throws AccountNotFoundException {
    if (accountRepository.existsById(id)) {
      accountRepository.deleteById(id);
    } else {
      throw new AccountNotFoundException();
      }
    }

  @Override
  public List<AccountResponseDto> findAll() {
    return accountRepository.findAll().stream()
        .map(
            account ->
                new AccountResponseDto(
                    account.getId(),
                    account.getUsername(),
                    account.getEmail(),
                    account.getCreatedAt(),
                    account.getRole()))
        .collect(Collectors.toList());
  }

  @Override
  public AccountResponseDto updateAccountById(long id, RegisterRequestDto registerRequestDto) {
    Account account = accountRepository.findAccountById(id);
    account.setUsername(registerRequestDto.getUsername());
    account.setEmail(registerRequestDto.getEmail());
    account.setPassword(registerRequestDto.getPassword());
    accountRepository.save(account);
    return new AccountResponseDto(
        account.getId(), account.getUsername(), account.getEmail(), account.getCreatedAt(), account.getRole());
  }


}
