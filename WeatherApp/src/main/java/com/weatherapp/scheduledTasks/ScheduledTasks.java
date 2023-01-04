package com.weatherapp.scheduledTasks;

import com.weatherapp.users.models.City;
import com.weatherapp.users.services.AccountService;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  private final AccountService accountService;

  public ScheduledTasks(AccountService accountService) {
    this.accountService = accountService;
  }


  @Scheduled(fixedRate = 300000)
  public void reportCurrentWeather() {
    accountService.findAllCities().stream()
        .filter(city -> accountService.isTemperatureUnderZero(city.getCityName()))
        .forEach(city -> accountService.reportTemperatureUnderZero(city.getCityName()));
  }

}
