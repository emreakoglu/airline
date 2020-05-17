package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.bean.CreditCard;
import com.example.bean.Flight;
import com.example.bean.Passenger;
import com.example.bean.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	public List<Ticket> findByPassenger(Passenger passenger);
	
	public List<Ticket> findByFlight(Flight flight);
	
	public List<Ticket> findByCreditCard(CreditCard creditCard);
	
	public List<Ticket> findByCreditCard_Id(Long id);

}
