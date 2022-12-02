package com.weatherapp.users.models;

import jakarta.persistence.Entity;

@Entity
public class Admin extends Account{

  public Admin() {
  }
  public Admin(String username, String password, String email) {
    super(username, email, password);
  }
  @Override
  public RoleEnum getRole() {
    return RoleEnum.ADMIN;
  }
}
