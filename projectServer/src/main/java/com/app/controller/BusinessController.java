package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.app.dto.AddBusinessDto;
import com.app.entity.OfferingType;
import com.app.service.BusinessService;

@RestController
@RequestMapping("/business")
@CrossOrigin
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	@PostMapping(value = "/register", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addBusiness( 
			@RequestPart @Valid AddBusinessDto newBusiness,
			@RequestPart MultipartFile img)throws IOException{
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(email);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(businessService.addBusiness(newBusiness, img, email));
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

	@GetMapping("/top-business")
	public ResponseEntity<?> getTopBusiness(
			@RequestParam double latitude, @RequestParam double longitude){
		return ResponseEntity.status(HttpStatus.OK)
				.body(businessService.getTopBusiness(latitude, longitude, 12));
	}
	@GetMapping("/business-details/{Id}")
	public ResponseEntity<?> getBussinessDetails(@PathVariable Long Id)
	{
		return ResponseEntity.status(HttpStatus.OK)
				.body(businessService.getBusinessDetails(Id)); //Add DTO to send exact details; 
	}

	@GetMapping("/MostPreferredProduct/{Id}")
	public ResponseEntity<?> getMostPreferredProduct(@PathVariable Long Id){
		//System.out.println("Endpoint hit");
		OfferingType type = OfferingType.PRODUCT;
		return ResponseEntity.status(HttpStatus.OK).body(businessService.getMostPreferredOffering(type, Id));//Create DTO here as well
	}

	@GetMapping("/MostPreferredService/{Id}")
	public ResponseEntity<?> getMostPreferredService(@PathVariable Long Id){
		OfferingType type = OfferingType.SERVICE;
		return ResponseEntity.status(HttpStatus.OK).body(businessService.getMostPreferredOffering(type, Id));//Create DTO here as well
	}
	
	@GetMapping("/owner")
	public ResponseEntity<?> getBusinessByOwner(){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.status(HttpStatus.OK)
				.body(businessService.getBusinessByOwner(email));
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBusiness(@PathVariable("id") Long id) {
		System.out.println("Api hit");
		boolean isDeleted = businessService.softDeleteBusiness(id);

		if (isDeleted) {
			return ResponseEntity.ok("Business deleted successfully");
		} else {
			return ResponseEntity.status(404).body("Business not found");
		}
	}

	@PostMapping("/restore/{id}")
	public ResponseEntity<String> restoreBusiness(@PathVariable("id") Long id) {
		boolean isRestored = businessService.softRestoreBusiness(id);

		if (isRestored) {
			return ResponseEntity.ok("Business restored successfully");
		} else {
			return ResponseEntity.status(404).body("Business not found or not deleted");
		}
	}
	
	@GetMapping("/getAllBusiness")
	  public ResponseEntity<List<AddBusinessDto>> getAllBusinesses() {
			
	        List<AddBusinessDto> businesses = businessService.findAllBusiness();
	        return ResponseEntity.ok(businesses);
	    }

}
