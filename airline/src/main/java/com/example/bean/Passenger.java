package com.example.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger {
	
	private String name;
	
	private String surname;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tckn;
	
	private int age;

	public Passenger() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public Long getTckn() {
		return tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
