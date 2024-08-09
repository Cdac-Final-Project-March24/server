package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddOfferingDto;

public interface OfferingService {
	AddOfferingDto addOffering(Long bId, MultipartFile img, String name, String description, double price) throws IOException;
}
