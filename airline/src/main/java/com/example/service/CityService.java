package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.City;
import com.example.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository cityRepository;
	
	public void addCity(City city) {
		cityRepository.saveAndFlush(city);
	}
	
	public Optional<City> findById(Long id) {
		return cityRepository.findById(id);
	}
}
