package com.weatherapp.common.dtos;

public class Weather {

  private String main;

  public Weather() {}

  public Weather(String main) {
    this.main = main;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }
}
