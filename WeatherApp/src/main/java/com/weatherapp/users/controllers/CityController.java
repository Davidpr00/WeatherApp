package com.weatherapp.users.controllers;

import com.weatherapp.common.dtos.OpenWeatherResponseDto;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/city")
public class CityController {

  @Value("${API_KEY}")
  private String apikey = "f51524b64c9093b2b839dfc96f9d65f0";

  @GetMapping("/{cityName}")
  ResponseEntity<OpenWeatherResponseDto> getTemperaturebyLocationCoordinates(@PathVariable String cityName) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<OpenWeatherResponseDto> response = restTemplate
        .getForEntity("https://api.openweathermap.org/data/2.5/weather?lat=" + 80.14 + "&lon="
            + 13.36 + "&APPID=" + apikey + "&units=metric", OpenWeatherResponseDto.class);
    return ResponseEntity.ok()
        .body(
            new OpenWeatherResponseDto(
                Objects.requireNonNull(response.getBody()).getMain(),
                response.getBody().getWeather()));
  }
}
