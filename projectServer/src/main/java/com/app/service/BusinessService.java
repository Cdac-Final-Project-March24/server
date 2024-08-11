package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;

public interface BusinessService {
	
	AddBusinessDto addBusiness(AddBusinessDto newBusiness, MultipartFile img, Long oId) throws IOException;
	ApiResponse updateBusiness(AddBusinessDto newBusiness, MultipartFile img, Long bId) throws IOException;

}
