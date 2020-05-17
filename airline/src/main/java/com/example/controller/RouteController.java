package com.example.controller;

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

import com.example.bean.Airline;
import com.example.bean.Airport;
import com.example.bean.Airport_Multiple;
import com.example.bean.City;
import com.example.bean.Flight;
import com.example.bean.General_Message;
import com.example.bean.Route;
import com.example.error.NotFoundException;
import com.example.service.AirportService;
import com.example.service.CityService;
import com.example.service.FlightService;
import com.example.service.RouteService;

@Controller
public class RouteController {
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping(method = RequestMethod.POST, value ="/route/add")
	@ResponseBody
	public List<Route> addroute(@RequestBody Route route) {
		
		route.setFrom(airportService.findByName(route.getFrom().getName()));
		route.setTo(airportService.findByName(route.getTo().getName()));
		route.setHash(route.hashCode());
		
		try {
			routeService.addRoute(route);
		}catch (Exception pe) {
			// TODO: handle exception
			System.out.println(pe.getMessage());
		}
		
		return routeService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/route/getAll")
	@ResponseBody
	public List<Route> getAll() {
		return routeService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/route/searchbyid")
	@ResponseBody
	public Route searchbyid(@RequestParam Long id) {
		return routeService.findById(id)
				.orElseThrow(() -> new NotFoundException(id,Route.class));
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/route/searchbyfromname")
	@ResponseBody
	public List<Route> searchbyfromname(@RequestParam String fromName){
		return routeService.findByFromName(fromName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/route/searchbyfromto")
	@ResponseBody
	public List<Route> searchbyfromto(@RequestParam String toName){
		return routeService.findByToName(toName);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/route/searchbyfrom")
	@ResponseBody
	public List<Route> searchbyfrom(@RequestBody Airport airport) {
		
		airport = airportService.findByName(airport.getName());
		
		return routeService.findByFrom(airport);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/route/searchbyto")
	@ResponseBody
	public List<Route> searchbyto(@RequestBody Airport airport) {
		
		airport = airportService.findByName(airport.getName());
		
		return routeService.findByTo(airport);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/route/searchbyfromandto")
	@ResponseBody
	public List<Route> searchbyfromandto(@RequestBody Airport_Multiple airport_Multiple) {
		
		airport_Multiple.setFrom(airportService.findByName(airport_Multiple.getFrom().getName()));
		airport_Multiple.setTo(airportService.findByName(airport_Multiple.getTo().getName()));
		
		return routeService.findByFromAndTo(airport_Multiple.getFrom(),airport_Multiple.getTo());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/route/searchbyflight")
	@ResponseBody
	public List<Route> searchbyflight(@RequestBody Flight flight){
		flight = flightService.findByCode(flight.getCode());
		return routeService.findByFligt(flight);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/route/deletebyid")
	@ResponseBody
	public General_Message deletebyid(@RequestParam Long id) {
		try {
			routeService.deleteById(id);
		}catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException(id, Route.class);
		}
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(id + "id li kayıt silindi");
		message.setType(Route.class.getSimpleName());
		return message;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/route/deletebyairport")
	@ResponseBody
	public General_Message deletebyairport(@RequestBody Airport airport) {
		airport = airportService.findById(airport.getId()).get();
		
		List<Route> fromRoutes = routeService.findByFrom(airport);
		List<Route> toRoutes = routeService.findByTo(airport);
		List<Route> routes = new ArrayList<Route>();
		routes.addAll(fromRoutes);routes.addAll(toRoutes);
		for (Route route : routes) {
			routeService.delete(route);
		}
		
		General_Message message = new General_Message();
		message.setStatus("Success");
		message.setMessage(airport.getName() + " isimli havalimanına ait tüm rotalar silinmiştir");
		message.setType(Route.class.getSimpleName());
		return message;
	}
	
	

}
