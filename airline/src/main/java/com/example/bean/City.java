package com.example.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {
	
	private int order_no;
	
	private String name;

	public City() {
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


	public int getOrder() {
		return order_no;
	}


	public void setOrder(int order) {
		this.order_no = order;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getOrder_no() {
		return order_no;
	}


	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	
	
	
}
