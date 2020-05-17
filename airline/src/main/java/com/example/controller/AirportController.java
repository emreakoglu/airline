package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
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
import com.example.bean.Flight;
import com.example.bean.General_Message;
import com.example.bean.Route;
import com.example.error.NotFoundException;
import com.example.error.ObjectNotBeDeleted;
import com.example.service.AirportService;

@Controller
public class AirportController {
	
	@Autowired
	AirportService airportService;
	
	@RequestMapping(method = RequestMethod.POST, value="/airport/add")
	@ResponseBody
	public List<Airport> addAirport(@RequestBody Airport airport) {
		try {
			airportService.addAirport(airport);
		}catch (Exception pe) {
	        System.out.println(pe.getMessage());
	        // do your operation based on sql errocode
	    }
		
		return airportService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/airport/addAll")
	@ResponseBody
	public List<Airport> addAllAirport(@RequestBody List<Airport> airports) {
		
		for (int i=0; i<airports.size(); i++) {
			try {
				airportService.addAirport(airports.get(i));
			}catch (Exception pe) {
		        System.out.println(pe.getMessage());
		        // do your operation based on sql errocode
		    }
		}
		
		return airportService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/airport/getAll")
	@ResponseBody
	public List<Airport> getAll() {
		return airportService.findAll();
	}
	
	@RequestMapping(method= RequestMethod.GET, value="/airport/searchbyid")
	@ResponseBody
	public Airport seacrhByIdAirport(@RequestParam Long id) {
		
		return airportService.findById(id)
				.orElseThrow(() -> new NotFoundException(id,Airline.class));
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/airport/searchbyname")
	@ResponseBody
	public Airport searchbyname(@RequestParam  String name) {
		
		return airportService.findByName(name);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/airport/deletebyid")
	@ResponseBody
	public General_Message deletebyid(@RequestParam Long id) {
		try {
			airportService.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			String constraintName= ((ConstraintViolationException) e.getCause()).getConstraintName();
			if(constraintName.contains("FLIGHT")) {
				throw new ObjectNotBeDeleted(id, Airport.class, Flight.class);
			}else {
				throw new ObjectNotBeDeleted(id, Airport.class, Route.class);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException(id, Airport.class);
		}
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(id + "id li kayıt silindi");
		message.setType(Airport.class.getSimpleName());
		return message;
	}
}
