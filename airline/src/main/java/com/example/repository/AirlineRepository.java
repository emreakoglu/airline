package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
	
	public Airline findByName(String name);
	

}
