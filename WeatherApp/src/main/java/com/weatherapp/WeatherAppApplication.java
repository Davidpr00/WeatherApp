package com.weatherapp;

import com.weatherapp.users.models.Account;
import com.weatherapp.users.models.RoleEnum;
import com.weatherapp.users.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherAppApplication implements CommandLineRunner {
  private final AccountRepository accountRepository;

  public WeatherAppApplication(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(WeatherAppApplication.class, args);
  }

  @Override
  public void run(String... args) {
    if (accountRepository.findAll().size() < 1) {
      Account account = new Account("david", "123456789", "david.praslicka@gmail.com");
      account.setRole(RoleEnum.USER);
      accountRepository.save(account);
    }
  }
}
