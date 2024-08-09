package com.app.service;

import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;
import com.app.dto.UpdateBusinessDto;

public interface BusinessService {
	
	AddBusinessDto addBusiness(AddBusinessDto newBusiness, Long oId);
	ApiResponse updateBusiness(UpdateBusinessDto newBusiness,Long bId, Long oId);

}
