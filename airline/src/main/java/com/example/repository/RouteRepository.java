package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.Airline;
import com.example.bean.Airport;
import com.example.bean.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
	
	public List<Route> findByFrom_Name(String name);
	
	public List<Route> findByTo_Name(String name);
	
	public List<Route> findByFrom(Airport airport);
	
	public List<Route> findByTo(Airport airport);
	
	public List<Route> findByFromAndTo(Airport from, Airport to);
	
}
