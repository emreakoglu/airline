package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.bean.Airline;
import com.example.bean.Flight;
import com.example.bean.General_Message;
import com.example.bean.Ticket;
import com.example.error.NotFoundException;
import com.example.error.ObjectNotBeDeleted;
import com.example.service.AirlineService;

@Controller
public class AirlineController {
	
	
	@Autowired AirlineService airlineService;

	@RequestMapping(method = RequestMethod.POST, value="/airline/add")
	@ResponseBody
	public List<Airline> addAirline(@RequestBody Airline airline){
		
		try {
			airlineService.addAirline(airline);
		}catch (Exception pe) {
	        System.out.println(pe.getMessage());
	        // do your operation based on sql errocode
	    }
		
		return airlineService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/airline/getAll")
	@ResponseBody
	public List<Airline> getAll() {
		return airlineService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/airline/searchbyid")
	@ResponseBody
	public Airline searchByIdAirline(@RequestParam  Long id) {
		
		return airlineService.findById(id)
				.orElseThrow(() -> new NotFoundException(id,Airline.class));
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/airline/searchbyname")
	@ResponseBody
	public Airline searchbyname(@RequestParam  String name) {
		
		return airlineService.findByName(name);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/airline/deletebyid")
	@ResponseBody
	public General_Message deletebyid(@RequestParam Long id) {
		try {
			airlineService.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			String constraintName= ((ConstraintViolationException) e.getCause()).getConstraintName();
			throw new ObjectNotBeDeleted(id, Airline.class, Flight.class);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException(id, Airline.class);
		}
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(id + "id li kayıt silindi");
		message.setType(Airline.class.getSimpleName());
		return message;
	}
	

}
