package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddOfferingDto;
import com.app.entity.OfferingType;
import com.app.service.OfferingService;


@RestController
@RequestMapping("/offering")
public class OfferingController {

	@Autowired
	private OfferingService offeringService;
	
	// @RequestPart is used to get both data and file from client
	@PostMapping(value = "/product/{bId}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addProduct(@PathVariable Long bId, 
			@RequestPart MultipartFile img, 
			@RequestPart AddOfferingDto newProduct) throws IOException{
		newProduct.setType(OfferingType.PRODUCT);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(offeringService.addOffering(bId, img, newProduct));
	}
	
	@GetMapping(value = "/product/{bId}")
	public ResponseEntity<?> getAllProducts(@PathVariable Long bId) throws IOException{
		return ResponseEntity.status(HttpStatus.OK)
				.body(offeringService.getAllOfferings(bId, OfferingType.PRODUCT));
	}
	
	@PutMapping(value = "/product/{id}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> updateProduct(@PathVariable Long id, 
			@RequestPart(required = false) MultipartFile img, 
			@RequestPart(required = false) AddOfferingDto newProduct) throws IOException{
		newProduct.setType(OfferingType.PRODUCT);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(offeringService.updateOffering(id, img, newProduct));
	}
	
	@PostMapping(value = "/service/{bId}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addService(@PathVariable Long bId, 
			@RequestPart MultipartFile img, 
			@RequestPart AddOfferingDto newProduct) throws IOException{
		newProduct.setType(OfferingType.SERVICE);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(offeringService.addOffering(bId, img, newProduct));
	}
	
	@GetMapping(value = "/service/{bId}")
	public ResponseEntity<?> getAllServices(@PathVariable Long bId) throws IOException{
		return ResponseEntity.status(HttpStatus.OK)
				.body(offeringService.getAllOfferings(bId, OfferingType.SERVICE));
	}
	
	@PutMapping(value = "/service/{id}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> updateService(@PathVariable Long id, 
			@RequestPart(required = false) MultipartFile img, 
			@RequestPart(required = false) AddOfferingDto newProduct) throws IOException{
		newProduct.setType(OfferingType.SERVICE);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(offeringService.updateOffering(id, img, newProduct));
	}
}
