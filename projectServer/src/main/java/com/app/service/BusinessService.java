package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.UpdateBusinessDto;

public interface BusinessService {
	
	ApiResponse updateBusiness(UpdateBusinessDto newBusiness,Long bId, Long oId);

}
