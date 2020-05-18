package com.example.bean;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {
	
	@Column(unique = true)
	private String code;
	
	@ManyToOne
	private Airport from;
	
	@ManyToOne
	private Airport to;
	
	private BigDecimal price;
	
	private int quota = 15;
	
	private int oldQuota = 10;
	
	private int emptySeat = quota;
	
	@ManyToOne
	@JoinColumn(name="airline_id", nullable=false)
	private Airline airline;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Airline getAirline() {
		return airline;
	}
	
	public void setAirline(Airline airline) {
		this.airline = airline;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.price = price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getOldQuota() {
		return oldQuota;
	}

	public void setOldQuota(int oldQuota) {
		this.oldQuota = oldQuota;
	}
	

	public int getEmptySeat() {
		return emptySeat;
	}

	public void setEmptySeat(int emptySeat) {
		this.emptySeat = emptySeat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((airline == null) ? 0 : airline.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		//result = prime * result + ((from == null) ? 0 : from.hashCode());
		//result = prime * result + ((id == null) ? 0 : id.hashCode());
		//result = prime * result + oldQuota;
		//result = prime * result + ((price == null) ? 0 : price.hashCode());
		//result = prime * result + quota;
		//result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Flight other = (Flight) obj;

		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		
		return true;
	}
	
}
