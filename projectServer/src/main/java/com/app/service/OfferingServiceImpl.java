package com.app.service;

import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BusinessDao;
import com.app.dao.OfferingDao;
import com.app.dao.UserDao;
import com.app.dto.AddOfferingDto;
import com.app.entity.Business;
import com.app.entity.Offering;
import com.app.entity.OfferingType;

@Service
@Transactional
public class OfferingServiceImpl implements OfferingService {
	@Autowired
	private OfferingDao offeringDao;
	@Autowired
	private BusinessDao businessDao;
	
	@Value("${file.upload.location}")
	private String uploadFolder;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostConstruct
	public void init() throws IOException {
		File folder = new File(uploadFolder);
		if (folder.exists()) {
			System.out.println("folder exists alrdy !");
		} else {
			folder.mkdir();
			System.out.println("created a folder !");
		}
	}
	
	public AddOfferingDto addOffering(Long bId, MultipartFile img, String name, String description, double price) throws IOException {
		String path = uploadFolder.concat(img.getOriginalFilename());
		System.out.println(path);
		writeByteArrayToFile(new File(path), img.getBytes());
		Business business = businessDao.findById(bId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid business id"));
		Offering product = new Offering(name, description, price, path, OfferingType.PRODUCT, business, 0);
		return mapper.map(offeringDao.save(product), AddOfferingDto.class);
	}
}
