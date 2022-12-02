package com.weatherapp.users.repositories;

import com.weatherapp.users.models.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends AccountRepository<Admin>{

}
