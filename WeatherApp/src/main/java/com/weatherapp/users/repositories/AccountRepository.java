package com.weatherapp.users.repositories;

import com.weatherapp.users.models.Account;
import com.weatherapp.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository<T extends Account> extends JpaRepository<T, Long> {


}
