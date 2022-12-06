package com.weatherapp.users.controllers;

import com.weatherapp.common.dtos.LoginRequestDto;
import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.security.JwtUtil;
import com.weatherapp.users.models.Account;
import com.weatherapp.users.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

  private final AccountService accountService;

  public UserController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
    ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
    builder.scheme("http");
    String urlPath = builder.build().toString();
    Account account = accountService.findUserById(accountService.loginUser(loginRequestDto).getId());
    JwtUtil jwtUtil = new JwtUtil();
    final String jwt = jwtUtil.createAccess(account, urlPath);
    return ResponseEntity.status(200).body(jwt);
  }
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {
    accountService.validateAndRegister(registerRequestDto);
    return ResponseEntity.ok().body("ok");
  }
}
