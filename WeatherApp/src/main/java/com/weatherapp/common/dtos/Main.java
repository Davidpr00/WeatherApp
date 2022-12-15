package com.weatherapp.common.dtos;

public class Main {
  private int temp;
  private int humidity;

  public Main() {}

  public Main(int temp, int humidity) {
    this.temp = temp;
    this.humidity = humidity;
  }

  public int getTemp() {
    return temp;
  }

  public void setTemp(int temp) {
    this.temp = temp;
  }

  public int getHumidity() {
    return humidity;
  }

  public void setHumidity(int humidity) {
    this.humidity = humidity;
  }
}
