package com.alam.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alam.entities.address;

public interface IAddressPersistence extends JpaRepository<address, Long> {
	
}
