package com.alam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alam.entities.Vendor;
import com.alam.service.vendorService;

@RestController
public class VendorController {

	@Autowired
	vendorService vendorService;

	// GET_ENTITYSET
	@RequestMapping("/vendor")
	public List<Vendor> getVendor() {
		return vendorService.readAllVendors();

	}

	// GET_CREATE_ENTITY
	@PostMapping("/vendor")
	public Vendor createVendor(@RequestBody Vendor myPostBody) {
		return vendorService.createVendor(myPostBody);
	}
	
	//Test Using localhost:8080/vendor/search?company=TCS Ltd
	@RequestMapping("/vendor/search")
	public List<Vendor> searchByCompany(@RequestParam String company){
		return vendorService.searchByCompanyName(company);
	}
	
	//Test Using http://localhost:8080/vendor/lookup?GSTNo=345 : It works as fixed parameter name.
	@RequestMapping("/vendor/lookup")
	public List<Vendor> searchVendorByGST(@RequestParam String GSTNo){
		return vendorService.lookupVendorByGST(GSTNo);
	}
//	OR
	//Test Using http://localhost:8080/vendor/lookup/34 : It works as without parameter name.
	@RequestMapping("/vendor/lookup/{gstNo}")
	public List<Vendor> searchVendorByGSTNo(@PathVariable("gstNo") String GSTNo){
		return vendorService.lookupVendorByGST(GSTNo);
	}
	
	
	//Test Using http://localhost:8080/vendor/102
	// GET_ENTITY
	@RequestMapping("/vendor/{vendorCode}")
	public Vendor getVendorById(@PathVariable("vendorCode") Long code) {
		Optional<Vendor> searchResult = vendorService.getSingleVendor(code);
		if(!searchResult.isPresent()) {
			return new Vendor((long)0, "", "", "", "", "", "", null);
		}
		return searchResult.get();
	}
	
	//Test Using http://localhost:8080/vendor
//	Payload
//	  {
//	      "id": 102,
//	    "companyName": "SAP",
//	    "firstName": "Naval",
//	    "lastName": "Sharma",
//	    "website": "infosys.com",
//	    "email": "naval.sharma@infosys.com",
//	    "status": "A",
//	    "gstNo": "GSTIN55555"
//	  }
	@RequestMapping(method =RequestMethod.PUT, value="/vendor")
	public Vendor UpdateVendor(@RequestBody Vendor vendor) {
		return vendorService.changeVendor(vendor);
	}
	
	//Test Using //Test Using http://localhost:8080/vendor/2
	@RequestMapping(method =RequestMethod.DELETE, value="/vendor/{id}")
	public String removeVendor(@PathVariable("id") Long Id) {
		return vendorService.deleteVendor(Id);
	}

}
