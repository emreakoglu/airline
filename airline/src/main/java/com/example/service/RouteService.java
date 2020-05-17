package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Airline;
import com.example.bean.Airport;
import com.example.bean.Flight;
import com.example.bean.Route;
import com.example.repository.AirlineRepository;
import com.example.repository.RouteRepository;

@Service
public class RouteService {

	@Autowired
	RouteRepository routeRepository;

	public void addRoute(Route route) {

		routeRepository.saveAndFlush(route);

	}

	public List<Route> findAll() {
		return routeRepository.findAll();
	}

	public Optional<Route> findById(Long id) {

		return routeRepository.findById(id);
	}

	public List<Route> findByFromName(String name) {
		return routeRepository.findByFrom_Name(name);
	}
	
	public List<Route> findByToName(String name) {
		return routeRepository.findByTo_Name(name);
	}
	
	public List<Route> findByFrom(Airport airport) {
		return routeRepository.findByFrom(airport);
	}
	
	public List<Route> findByTo(Airport airport) {
		return routeRepository.findByTo(airport);
	}
	
	public List<Route> findByFromAndTo(Airport from, Airport to) {
		return routeRepository.findByFromAndTo(from, to);
	}
	
	public List<Route> findByFligt(Flight flight) {
		return routeRepository.findByFromAndTo(flight.getFrom(), flight.getTo());
	}
	
	public void deleteById(Long id) {
		routeRepository.deleteById(id);
	}
	
	public void delete(Route route) {
		routeRepository.delete(route);
	}
	
}
