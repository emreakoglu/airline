package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Airline;
import com.example.repository.AirlineRepository;

@Service
public class AirlineService {
	
	
	@Autowired(required=true)
	AirlineRepository airlineRepository;
	
	public void addAirline(Airline airline) {
		
		airlineRepository.saveAndFlush(airline);
		
	}
	
	public List<Airline> findAll() {
		return airlineRepository.findAll();
	}
	
	public Optional<Airline> findById(Long id){
		
		return airlineRepository.findById(id);
	}
	
	public Airline findByName(String name) {
		return airlineRepository.findByName(name);
	}
	
	public void deleteById(Long id) {
		airlineRepository.deleteById(id);
	}

}
