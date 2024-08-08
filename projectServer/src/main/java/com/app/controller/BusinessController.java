package com.app.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UpdateBusinessDto;
import com.app.service.BusinessService;

@RestController
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@PutMapping("/{bId}/{oId}")
	public ResponseEntity<?> updateBusiness(@RequestBody @Valid UpdateBusinessDto newBusiness, @PathVariable Long bId,@PathVariable Long oId){
		System.out.println(newBusiness);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(businessService.updateBusiness(newBusiness, bId, oId));
	}
}
