package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.CreditCard;
import com.example.bean.Flight;
import com.example.bean.Passenger;
import com.example.bean.Ticket;
import com.example.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketRepository;
	
	public void addTicket(Ticket ticket) {
		
		ticketRepository.saveAndFlush(ticket);
		
	}
	
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}
	
	public Optional<Ticket> findByNo(Long no) {
		return ticketRepository.findById(no);
	}
	
	public List<Ticket> findByPassenger(Passenger passenger) {
		return ticketRepository.findByPassenger(passenger);
	}
	
	public List<Ticket> findByFlight(Flight flight) {
		return ticketRepository.findByFlight(flight);
	}
	
	public List<Ticket> findByCreditCard(CreditCard creditCard) {
		return ticketRepository.findByCreditCard(creditCard);
	}
	
	public List<Ticket> findByCreditCard_Id(Long id){
		return ticketRepository.findByCreditCard_Id(id);
	}
	
	public void deleteById(Long id) {
		ticketRepository.deleteById(id);
	}
	
	public void delete(Ticket ticket) {
		ticketRepository.delete(ticket);
	}
}
