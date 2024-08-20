package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BusinessDao;
import com.app.dao.OfferingDao;
import com.app.dto.AddBusinessDto;
import com.app.dto.AddOfferingDto;
import com.app.dto.ApiResponse;
import com.app.dto.GetOfferingDto;
import com.app.entity.Business;
import com.app.entity.Offering;
import com.app.entity.OfferingReview;
import com.app.entity.OfferingType;

@Service
@Transactional
public class OfferingServiceImpl implements OfferingService {
	@Autowired
	private OfferingDao offeringDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private ImageHandelingService imageService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addOffering(Long bId, MultipartFile img, AddOfferingDto newOffering) throws IOException {
		String path = imageService.saveImage(img); // save image and get its path
		Business business = businessDao.findById(bId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid business id"));
		Offering offering = mapper.map(newOffering, Offering.class);
		offering.setBusiness(business);
		offering.setImage(("https://server-production-cdd5.up.railway.app/").concat(path)); // add url to image path
		offeringDao.save(offering);
		return new ApiResponse("Offering Added successfully");
	}

	@Override
	public List<GetOfferingDto> getAllOfferings(Long bId, OfferingType type)  {
		if(!businessDao.existsById(bId)) throw new ResourceNotFoundException("Invalid Business Id");
		return offeringDao.findByBusinessIdAndType(bId, type, Sort.by("updatedOn").descending()) // Latest offerings
				.stream().map(o -> mapper.map(o, GetOfferingDto.class)) // convert entity to dto for every element
				.collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse updateOffering(Long id, MultipartFile img, AddOfferingDto newOffering) throws IOException {
		Offering offering = offeringDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid offering id"));
		mapper.map(newOffering, offering);
		System.out.println(offering);
		// Check if image is sent by user for update or not
		System.out.println(img);
		if(img != null && img.getOriginalFilename() != null && !img.getOriginalFilename().isBlank()) {
			String path = imageService.saveImage(img);
			offering.setImage(("https://server-production-cdd5.up.railway.app/").concat(path));
		}
		
		return new ApiResponse("Offering Updated successfully");
	}

	@Override
	public List<GetOfferingDto> getTopOfferings(double latitude, double longitude, OfferingType type, int limit) {
		return offeringDao
				.findTopClosest(type, latitude, longitude, PageRequest.of(0, limit, Sort.by("orderCount").descending()))
				.stream().map(o -> mapper.map(o, GetOfferingDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public AddOfferingDto getOfferingById(Long id) { //get offering deatil..
		 Offering offering = offeringDao.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("Invalid Offering id"));
		 return mapper.map(offering, AddOfferingDto.class);
		
	}
	
	@Override
    public List<OfferingReview> getReviewsByOfferingId(Long offeringId) { //get offering reviews.
        Offering offering = offeringDao.findById(offeringId)
        		.orElseThrow(()-> new ResourceNotFoundException("Invalid Offering id"));; 
        return offering.getReviews(); 
    }
	

	@Override
	  public List<AddBusinessDto> getRelatedBusinessesByOfferingName(String offeringName) {
	        // Find all offerings by name
	        List<Offering> offerings = offeringDao.findByNameContaining(offeringName);

	        // Extract business IDs from offerings
	       return  offerings.stream()
	                .map(offering -> mapper.map(offering.getBusiness(),AddBusinessDto.class ))
	                .distinct() // Ensure unique business IDs
	                .collect(Collectors.toList());
	       
	    }
}
