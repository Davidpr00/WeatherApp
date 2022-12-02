package com.weatherapp.users.repositories;

import com.weatherapp.users.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AccountRepository<User> {

}
