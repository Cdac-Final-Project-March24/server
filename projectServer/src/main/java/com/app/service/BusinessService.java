package com.app.service;

import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;

public interface BusinessService {
	
	AddBusinessDto addBusiness(AddBusinessDto newBusiness, Long oId);
	ApiResponse updateBusiness(AddBusinessDto newBusiness,Long bId);

}
