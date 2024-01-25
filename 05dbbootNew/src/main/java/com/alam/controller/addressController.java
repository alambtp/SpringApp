package com.alam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alam.entities.address;
import com.alam.service.addressService;

@RestController
public class addressController {
	
	@Autowired
	addressService addSrv;
	
	//http://localhost:8080/addresses
	@RequestMapping("addresses")
	public List<address> getAddress() {
		return addSrv.getAddress();
	}

//	{
//	    "addressType": "H",
//	    "street": "Hoodi",
//	    "city": "Bangalore",
//	    "country": "India",
//	    "region": "APJ"
//	}
	@PostMapping("/addresses")
	public address createAddress(@RequestBody address payload) {
		return addSrv.createAddress(payload);
	}
}
