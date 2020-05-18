package com.example.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.CreditCard;
import com.example.bean.Flight;
import com.example.bean.General_Message;
import com.example.bean.Passenger;
import com.example.bean.Route;
import com.example.bean.Ticket;
import com.example.error.FligthQuotaExceed;
import com.example.error.NotFoundException;
import com.example.service.FlightService;
import com.example.service.PassengerService;
import com.example.service.TicketService;
import com.example.utils.Tools;

@Controller
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	PassengerService passengerService;
	
	
	@RequestMapping(method= RequestMethod.POST, value ="/ticket/add")
	@ResponseBody
	public Ticket addTicket(@RequestBody Ticket ticket) {
		
		
		Flight flight = flightService.findByCode(ticket.getFlight().getCode());
		if (flight == null) {
			throw new NullPointerException(ticket.getFlight().getCode() +" Flight bulunamadı");
		}
		if (ticket.getPassenger() == null) {
			throw new NullPointerException("Yolcu bilgilerini giriniz");
		}
		if (ticket.getCreditCard() == null) {
			throw new NullPointerException("Kredi Kartı bilgilerini giriniz");
		}
		
		ticket.setPrice(flight.getPrice());
		ticket.setFlight(flight);
		
		ticket.getCreditCard().setCardNo(Tools.maskCreditCard(ticket.getCreditCard().getCardNo()));
		
		int currentTicketsCount = ticketService.findByFlight(flight).size(); //mevcut bilet sayısı
		
		if(flight.getEmptySeat() < 1) { // uçakta yer yoktur, kontenjan aşımı hatası
			throw new FligthQuotaExceed(flight);
		}
		ticketService.addTicket(ticket);
		flight.setEmptySeat(flight.getEmptySeat()-1);
		
		// Uçuş kontenjanın her %10 artışında, bilet fiyatı da %10 arttırılmalı.
		if ((flight.getOldQuota()*0.1 + flight.getOldQuota()) <= currentTicketsCount) {
			flight.setPrice(flight.getPrice().add(flight.getPrice().multiply(new BigDecimal(0.1))));
			flight.setOldQuota(currentTicketsCount);
		}
		flightService.addFligth(flight);
		
		return ticket;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/ticket/getAll")
	@ResponseBody
	public List<Ticket> getAll(){
		return ticketService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/ticket/searchbyid")
	@ResponseBody
	public Ticket searchbyid(@RequestParam Long id) {
		return ticketService.findByNo(id)
				.orElseThrow(() -> new NotFoundException(id, Ticket.class));
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/ticket/searchbytckn")
	@ResponseBody
	public List<Ticket> findByPassenger(@RequestBody Passenger passenger) {
		
		List<Passenger> passengers = passengerService.findByTckn(passenger.getTckn());
		List<Ticket> allTicketByPassenger = new ArrayList<Ticket>();
		for (Passenger passengerTemp : passengers) {
			allTicketByPassenger.addAll(ticketService.findByPassenger(passengerTemp));
		}
		return allTicketByPassenger;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/ticket/searchbyflight")
	@ResponseBody
	public List<Ticket> findByFlight(@RequestBody Flight flight) {
		flight = flightService.findByCode(flight.getCode());
		return ticketService.findByFlight(flight);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/ticket/searchbycreditcard")
	@ResponseBody 
	public List<Ticket> findByCreditCard(@RequestBody CreditCard creditCard) {
		
		return ticketService.findByCreditCard_Id(creditCard.getId());
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value="/ticket/cancelbyno")
	@ResponseBody
	public General_Message cancelbyid(@RequestParam Long no) {
		try {
			Optional<Ticket> tickets = ticketService.findByNo(no);
			Flight flight = tickets.get().getFlight();
			ticketService.deleteById(no);
			flight.setEmptySeat(flight.getEmptySeat()+1);
			flightService.addFligth(flight);
		}catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException(no, Ticket.class);
		}
		
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(no + " numaları bilet iptal edildi");
		message.setType(Ticket.class.getSimpleName());
		return message;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value ="/ticket/cancelbyflight")
	@ResponseBody
	public General_Message cancelbyflight(@RequestBody Flight flight) {
		
		flight = flightService.findByCode(flight.getCode());
		List<Ticket> tickets = ticketService.findByFlight(flight);
		for (Ticket ticket : tickets) {
			ticketService.delete(ticket);
		}
		
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(flight.getCode() +" kodlu uçuşa ait tüm biletler iptal edilmiştir.");
		message.setType(Ticket.class.getSimpleName());
		flight.setEmptySeat(flight.getQuota());
		flightService.addFligth(flight);
		return message;
		
	}

}
