package com.weatherapp.users.controllers;

import com.weatherapp.common.dtos.CityCoordinatesDto;
import com.weatherapp.common.dtos.OpenWeatherResponseDto;
import com.weatherapp.users.services.AccountService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/city")
public class CityController {

  private final AccountService accountService;
  @Value("${API_KEY}")
  private String apikey;

  public CityController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/{cityName}")
  ResponseEntity<OpenWeatherResponseDto> getTemperaturebyLocationCoordinates(
      @RequestHeader String token, @PathVariable String cityName) {
    return ResponseEntity.ok().body(accountService.showOpenWeatherResponseDto(token, cityName));
  }

  @GetMapping("/save/{cityName}")
  ResponseEntity<CityCoordinatesDto> getCoordinatesFromCityName(
      @RequestHeader String token, @PathVariable String cityName) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<CityCoordinatesDto[]> response =
        restTemplate.getForEntity(
            "http://api.openweathermap.org/geo/1.0/direct?q="
                + cityName
                + "&limit=1&appid="
                + apikey,
            CityCoordinatesDto[].class);
    accountService.saveCityForUser(token, response);
    return ResponseEntity.ok().body(Objects.requireNonNull(response.getBody())[0]);
  }
}
