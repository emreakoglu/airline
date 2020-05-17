package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Airline;
import com.example.bean.Airport;
import com.example.bean.Flight;
import com.example.bean.Route;
import com.example.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository flightRepository;
	
	public void addFligth(Flight flight) {
		
		flightRepository.saveAndFlush(flight);
		
	}
	
	public List<Flight> findAll() {
		return flightRepository.findAll();
	}

	public Optional<Flight> findById(Long id) {

		return flightRepository.findById(id);
	}
	
	public List<Flight> findByFromName(String name) {
		return flightRepository.findByFrom_Name(name);
	}
	
	public List<Flight> findByToName(String name) {
		return flightRepository.findByTo_Name(name);
	}
	
	public List<Flight> findByFrom(Airport airport) {
		return flightRepository.findByFrom(airport);
	}
	
	public List<Flight> findByTo(Airport airport) {
		return flightRepository.findByTo(airport);
	}
	
	public List<Flight> findByFromAndTo(Airport from, Airport to) {
		return flightRepository.findByFromAndTo(from,to);
	}
	
	public void deleteById(Long id) {
		flightRepository.deleteById(id);
	}
	
	public void delete(Flight flight) {
		flightRepository.delete(flight);
	}
	
	public Flight findByCode(String code) {
		return flightRepository.findByCode(code);
	}
	
	public List<Flight> findByAirline(Airline airline) {
		return flightRepository.findByAirline(airline);
	}

}
