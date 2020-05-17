package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Flight;
import com.example.bean.Passenger;
import com.example.bean.Ticket;
import com.example.repository.PassengerRepository;
import com.example.repository.TicketRepository;

@Service
public class PassengerService {
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	public void addPassenger(Passenger passenger) {
		passengerRepository.saveAndFlush(passenger);
	}
	
	public List<Passenger> findAll() {
		return passengerRepository.findAll();
	}
	
	public List<Passenger> findByName(String name){
		return passengerRepository.findByName(name);
	}
	
	public List<Passenger> findBySurname(String surname){
		return passengerRepository.findBySurname(surname);
	}
	
	public List<Passenger> findByTckn(Long tckn) {
		return passengerRepository.findByTckn(tckn);
	}
	
	public List<Passenger> findByFlight(Flight flight){
		
		List<Ticket> ticketsByFlight = ticketRepository.findByFlight(flight);
		
		List<Passenger> passengersByFlight = new ArrayList<Passenger>();
		
		ticketsByFlight.forEach(ticket -> 
			passengersByFlight.add(ticket.getPassenger())
		);
		
		return passengersByFlight;
	}

}
