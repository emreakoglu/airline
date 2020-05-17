package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
	
	public Airport findByName(String name);

}
