package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Airline;
import com.example.bean.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	public Airline findByName(String name);
}
