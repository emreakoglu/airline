package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	
	public List<Passenger> findByName(String name);
	
	public List<Passenger> findBySurname(String surname);
	
	public List<Passenger> findByTckn(Long tckn);
	
}
