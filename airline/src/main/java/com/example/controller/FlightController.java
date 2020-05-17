package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.Airline;
import com.example.bean.Airport;
import com.example.bean.Airport_Multiple;
import com.example.bean.Flight;
import com.example.bean.General_Message;
import com.example.bean.Passenger;
import com.example.bean.Route;
import com.example.bean.Ticket;
import com.example.error.NotFoundException;
import com.example.error.ObjectNotBeDeleted;
import com.example.service.AirlineService;
import com.example.service.AirportService;
import com.example.service.FlightService;
import com.example.service.PassengerService;
import com.example.service.TicketService;

@Controller
public class FlightController {
	
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	PassengerService passengerService;
	
	@RequestMapping(method = RequestMethod.POST, value="/flight/add")
	@ResponseBody
	public List<Flight> addFlight(@RequestBody Flight flight) {
		
		flight.setFrom(airportService.findByName(flight.getFrom().getName()));
		flight.setTo(airportService.findByName(flight.getTo().getName()));
		flight.setAirline(airlineService.findByName(flight.getAirline().getName()));
		
		try {
			flightService.addFligth(flight);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return flightService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/flight/getAll")
	@ResponseBody
	public List<Flight> getAll() {
		return flightService.findAll();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/flight/searchbyid")
	@ResponseBody
	public Flight searchbyid(@RequestParam Long id) {
		return flightService.findById(id)
				.orElseThrow(() -> new NotFoundException(id,Flight.class));
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/flight/searchbyfromname")
	@ResponseBody
	public List<Flight> searchbyfromname(@RequestParam String fromName){
		return flightService.findByFromName(fromName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/flight/searchbytoname")
	@ResponseBody
	public List<Flight> searchbyfromto(@RequestParam String toName){
		return flightService.findByToName(toName);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/flight/searchbyfrom")
	@ResponseBody
	public List<Flight> searchbyfrom(@RequestBody Airport airport) {
		
		airport = airportService.findByName(airport.getName());
		
		return flightService.findByFrom(airport);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/flight/searchbyto")
	@ResponseBody
	public List<Flight> searchbyto(@RequestBody  Airport airport) {
		
		airport = airportService.findByName(airport.getName());
		
		return flightService.findByTo(airport);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/flight/searchbyfromandto")
	@ResponseBody
	public List<Flight> searchbyfromandto(@RequestBody Airport_Multiple airport_Multiple) {
		
		airport_Multiple.setFrom(airportService.findByName(airport_Multiple.getFrom().getName()));
		airport_Multiple.setTo(airportService.findByName(airport_Multiple.getTo().getName()));
		
		return flightService.findByFromAndTo(airport_Multiple.getFrom(),airport_Multiple.getTo());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/flight/searchbytckn")
	@ResponseBody
	public List<Flight> searchbytckn (@RequestBody Passenger passenger){
		
		List<Passenger> passengers = passengerService.findByTckn(passenger.getTckn());
		List<Ticket> allTicketByPassenger = new ArrayList<Ticket>();
		
		for (Passenger passengerTemp : passengers) {
			allTicketByPassenger.addAll(ticketService.findByPassenger(passengerTemp));
		}
		
		List<Flight> flightsByPassenger = new ArrayList<Flight>();
		
		allTicketByPassenger.forEach(ticket -> 
			flightsByPassenger.add(ticket.getFlight())
		);
		
		return flightsByPassenger;
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value="/flight/deletebyid")
	@ResponseBody
	public General_Message deletebyid(@RequestParam Long id) {
		try {
			
			flightService.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new ObjectNotBeDeleted(id, Flight.class, Ticket.class);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException(id, Flight.class);
		}
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(id + "id li kayıt silindi");
		message.setType(Flight.class.getSimpleName());
		return message;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/flight/deletebyairline")
	@ResponseBody
	public General_Message deletebyairline(@RequestBody Airline airline) {
		airline = airlineService.findById(airline.getId()).get();
		
		List<Flight> flights = flightService.findByAirline(airline);
		for (Flight flight : flights) {
			flightService.delete(flight);
		}
		
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(airline.getName() + " isimli şirkete ait tüm uçuşlar silinmiştir");
		message.setType(Flight.class.getSimpleName());
		return message;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/flight/deletebyairport")
	@ResponseBody
	public General_Message deletebyairport(@RequestBody Airport airport) {
		airport = airportService.findById(airport.getId()).get();
		
		List<Flight> fromFlights = flightService.findByFrom(airport);
		List<Flight> toFlights = flightService.findByTo(airport);
		List<Flight> flights = new ArrayList<Flight>();
		flights.addAll(fromFlights);flights.addAll(toFlights);
		for (Flight flight : flights) {
			flightService.delete(flight);
		}
		
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(airport.getName() + " isimli havalimanına ait tüm uçuşlar silinmiştir");
		message.setType(Flight.class.getSimpleName());
		return message;
	}
	
}
