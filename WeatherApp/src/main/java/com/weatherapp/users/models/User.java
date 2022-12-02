package com.weatherapp.users.models;

public class User extends Account{

  public User() {
  }

  public User(String username, String password, String email) {
    super(username, password, email);
  }

  @Override
  public RoleEnum getRole() {
    return RoleEnum.USER;
  }
}
