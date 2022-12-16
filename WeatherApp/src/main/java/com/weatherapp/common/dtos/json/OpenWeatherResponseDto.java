package com.weatherapp.common.dtos.json;

public class OpenWeatherResponseDto {
  private Main main;
  private Weather[] weather;

  public OpenWeatherResponseDto(Main main, Weather[] weather) {
    this.main = main;
    this.weather = weather;
  }

  public OpenWeatherResponseDto() {}

  public Main getMain() {
    return main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public Weather[] getWeather() {
    return weather;
  }

  public void setWeather(Weather[] weather) {
    this.weather = weather;
  }
}
