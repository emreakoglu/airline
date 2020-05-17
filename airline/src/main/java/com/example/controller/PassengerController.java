package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.Flight;
import com.example.bean.Passenger;
import com.example.service.FlightService;
import com.example.service.PassengerService;

@Controller
public class PassengerController {
	
	@Autowired
	PassengerService passengerService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping(method=RequestMethod.GET, value="/passenger/getAll")
	@ResponseBody
	public List<Passenger> getAll(){
		return passengerService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/passenger/searchbyname")
	@ResponseBody
	public List<Passenger> searchbyname(@RequestParam String name){
		return passengerService.findByName(name);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/passenger/searchbysurname")
	@ResponseBody
	public List<Passenger> searchbysurname(@RequestParam String surname){
		return passengerService.findBySurname(surname);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/passenger/searchbytckn")
	@ResponseBody
	public List<Passenger> searchbytckn(@RequestBody Passenger passenger){
		
		return passengerService.findByTckn(passenger.getTckn());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/passenger/searchbyflight")
	@ResponseBody
	public List<Passenger> searchbyflight(@RequestBody Flight flight) {
		flight = flightService.findByCode(flight.getCode());
		return passengerService.findByFlight(flight);
	}
	
	
}
