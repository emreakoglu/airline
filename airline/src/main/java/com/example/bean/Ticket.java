package com.example.bean;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	
	private BigDecimal price;
	
	// Her bir bilet bir yolcuya aittir.
	@OneToOne(cascade=CascadeType.ALL)
	private Passenger passenger; 
	
	// Birden fazla bilet bir uçuşa ait olabilir
	@ManyToOne
	private Flight flight;
	
	// Ne kadar bilet alındıysa o kadar kredi kartı işlemi yapılmış gibi düşündüm.
	@OneToOne(cascade=CascadeType.ALL)
	private CreditCard creditCard;

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

}
