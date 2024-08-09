package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping(value = "/product/{bId}", consumes = "multipart/form-data")
	public ResponseEntity<?> addProduct(@PathVariable Long bId, 
			@RequestParam MultipartFile img, @RequestParam String name, 
			@RequestParam String description, @RequestParam double price) throws IOException{
		System.out.println(img.getBytes());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(offeringService.addOffering(bId, img, name, description, price));
	}
}
