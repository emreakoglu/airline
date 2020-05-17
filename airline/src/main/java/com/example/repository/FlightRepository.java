package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Airline;
import com.example.bean.Airport;
import com.example.bean.Flight;
import com.example.bean.Route;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	public List<Flight> findByFrom_Name(String name);
	
	public List<Flight> findByTo_Name(String name);
	
	public List<Flight> findByFrom(Airport airport);
	
	public List<Flight> findByTo(Airport airport);
	
	public List<Flight> findByFromAndTo(Airport from, Airport to);
	
	public Flight findByCode(String code);
	
	public List<Flight> findByAirline(Airline airline);
	
}
