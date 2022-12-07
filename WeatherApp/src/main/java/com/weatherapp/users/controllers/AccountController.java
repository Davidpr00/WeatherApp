package com.weatherapp.users.controllers;

import com.weatherapp.common.dtos.RegisterRequestDto;
import com.weatherapp.users.services.AccountService;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
  private final AccountService accountService;


  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/")
  public ResponseEntity index(){
    return ResponseEntity.ok().body(accountService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity show(@PathVariable long id){
    return ResponseEntity.ok().body(accountService.findUserById(id));
  }

  @PostMapping("/")
  public ResponseEntity store(@RequestBody RegisterRequestDto registerRequestDto){
    accountService.validateAndRegister(registerRequestDto);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity update(@PathVariable long id, @RequestBody RegisterRequestDto registerRequestDto){
    return ResponseEntity.ok().body(accountService.updateAccountById(id,registerRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity destroy(@PathVariable long id) throws AccountNotFoundException {
    accountService.deleteAccountById(id);
    return ResponseEntity.ok().build();
  }
}
