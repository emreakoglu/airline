package com.example.bean;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
	
	@ManyToOne
	private Airport from;
	
	@ManyToOne
	private Airport to;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<City> cities;
	
	@Column(unique = true)
	private int hash;
	
	public List<City> getCities() {
		return cities;
	}


	public void setCities(List<City> cities) {
		this.cities = cities;
	}


	public Route() {
		// TODO Auto-generated constructor stub
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Airport getFrom() {
		return from;
	}


	public void setFrom(Airport from) {
		this.from = from;
	}


	public Airport getTo() {
		return to;
	}


	public void setTo(Airport to) {
		this.to = to;
	}
	
	public void setHash(int hash) {
		this.hash = hash;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		
		if (hash != other.hash)
			return false;
		return true;
	}
	
	
	
}
