package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;

public interface BusinessService {
	
	ApiResponse addBusiness(AddBusinessDto newBusiness, MultipartFile img, Long oId) throws IOException;
	ApiResponse updateBusiness(AddBusinessDto newBusiness, MultipartFile img, Long bId) throws IOException;
	List<AddBusinessDto> getTopBusiness(double latitude, double longitude, int limit);

}
