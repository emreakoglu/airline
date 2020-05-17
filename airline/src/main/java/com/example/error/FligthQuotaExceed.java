package com.example.error;

import com.example.bean.Flight;

public class FligthQuotaExceed extends RuntimeException {
	
	public FligthQuotaExceed(Flight flight) {
		super(flight.getCode() + " kodlu uçuşta yeterli kontenjan yoktur.");
	}

}
