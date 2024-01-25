package com.alam.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alam.entities.Vendor;

//This will automatically handle the CRUD operations. Bt this will better work with single entity.
// Test using http://localhost:8080/newVendor
@RepositoryRestResource(collectionResourceRel = "vendor", path="newVendor")
public interface vendorControllerNew extends JpaRepository<Vendor, Long>{

}
