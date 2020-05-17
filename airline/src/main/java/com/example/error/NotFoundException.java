package com.example.error;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException(Long id, Class class1) {
		super(class1.getSimpleName() + " id not found : " + id);
	}

}
