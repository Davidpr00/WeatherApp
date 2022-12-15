package com.weatherapp.users.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;
  private String email;
  private String createdAt;
  private String verifiedAt;
  private Enum<RoleEnum> role;
  private String verificationToken;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "account_city",
      joinColumns = @JoinColumn(name = "account_id"),
      inverseJoinColumns = @JoinColumn(name = "city_id"))
  private List<City> citiesList;

  public Account(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public Account() {}

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String creationDate) {
    this.createdAt = creationDate;
  }

  public Enum<RoleEnum> getRole() {
    return role;
  }

  public void setRole(Enum<RoleEnum> role) {
    this.role = role;
  }

  public List<City> getCitiesList() {
    return citiesList;
  }

  public void setCitiesList(List<City> citiesList) {
    this.citiesList = citiesList;
  }

  public String getVerificationToken() {
    return verificationToken;
  }

  public void setVerificationToken(String verificationToken) {
    this.verificationToken = verificationToken;
  }

  public String getVerifiedAt() {
    return verifiedAt;
  }

  public void setVerifiedAt(String verifiedAt) {
    this.verifiedAt = verifiedAt;
  }
}
