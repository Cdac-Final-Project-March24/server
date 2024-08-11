package com.app.controller;

import java.io.IOException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddBusinessDto;
import com.app.service.BusinessService;

@RestController
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@PostMapping(value = "/{oId}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addBusiness( 
			@PathVariable Long oId,
			@RequestPart @Valid AddBusinessDto newBusiness,
			@RequestPart MultipartFile img)throws IOException{
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(businessService.addBusiness(newBusiness, img, oId));
	}
	
	@PutMapping(value = "/{bId}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> updateBusiness(@PathVariable Long bId,
			@RequestPart(required = false) @Valid AddBusinessDto newBusiness,
			@RequestPart(required = false) MultipartFile img)throws IOException{
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(businessService.updateBusiness(newBusiness, img, bId));
	}
}
