package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;
import com.app.entity.Business;
import com.app.entity.Offering;
import com.app.entity.OfferingType;

public interface BusinessService {
	
	ApiResponse addBusiness(AddBusinessDto newBusiness, MultipartFile img, Long oId) throws IOException;
	ApiResponse updateBusiness(AddBusinessDto newBusiness, MultipartFile img, Long bId) throws IOException;
	List<AddBusinessDto> getTopBusiness(double latitude, double longitude, int limit);
	Optional<Business> getBusinessDetails(Long Id);
	List<Offering> getMostPreferredOffering(OfferingType Type, Long Business_ID);
	boolean softDeleteBusiness(Long id);
	boolean softRestoreBusiness(Long id);

}
