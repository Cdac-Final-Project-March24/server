package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddBusinessDto;
import com.app.dto.AddOfferingDto;
import com.app.dto.ApiResponse;
import com.app.dto.GetOfferingDto;
import com.app.entity.Business;
import com.app.entity.Offering;
import com.app.entity.OfferingReview;
import com.app.entity.OfferingType;

public interface OfferingService {
	ApiResponse addOffering(Long bId, MultipartFile img, AddOfferingDto newOffering) throws IOException;
	
	List<GetOfferingDto> getAllOfferings(Long bId, OfferingType type) throws IOException;
	
	ApiResponse updateOffering(Long bId, MultipartFile img, AddOfferingDto newOffering) throws IOException;
	
	List<GetOfferingDto> getTopOfferings(double latitude, double longitude, OfferingType type, int limit);
	
	AddOfferingDto getOfferingById(Long id); 
	
	 List<OfferingReview> getReviewsByOfferingId(Long offeringId);
	 
	 List<AddBusinessDto> getRelatedBusinessesByOfferingName(String offeringName);
}
