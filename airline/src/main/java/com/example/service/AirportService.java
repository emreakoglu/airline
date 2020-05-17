package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Airport;
import com.example.repository.AirlineRepository;
import com.example.repository.AirportRepository;

@Service
public class AirportService {
	
	@Autowired
	AirportRepository airportRepository;
	
	public void addAirport(Airport airport) {
		airportRepository.saveAndFlush(airport);
	}
	
	public List<Airport> findAll() {
		return airportRepository.findAll();
	}
	
	public Optional<Airport> findById(Long id) {
		return airportRepository.findById(id);
	}
	
	public Airport findByName(String name) {
		return airportRepository.findByName(name);
	}
	
	public void deleteById(Long id) {
		airportRepository.deleteById(id);
	}

}
