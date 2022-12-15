package com.weatherapp.common.dtos;

import com.weatherapp.users.models.RoleEnum;

public class AccountResponseDto {
  private Long id;
  private String username;
  private String email;
  private String creationDate;
  private Enum<RoleEnum> role;

  public AccountResponseDto(
      Long id, String username, String email, String creationDate, Enum<RoleEnum> role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.creationDate = creationDate;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public Enum<RoleEnum> getRole() {
    return role;
  }

  public void setRole(Enum<RoleEnum> role) {
    this.role = role;
  }
}
