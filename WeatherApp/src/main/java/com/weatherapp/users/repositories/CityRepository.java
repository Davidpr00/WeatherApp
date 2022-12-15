package com.weatherapp.users.repositories;

import com.weatherapp.users.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  boolean existsCityByCityName(String cityName);

  City findCityByCityName(String name);
}
