package com.app.service;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageHandelingService {
	@Value("${file.upload.location}")
	private String uploadFolder;
	
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
	
	public String saveImage(MultipartFile img) throws IOException {
		String path = uploadFolder.concat(img.getOriginalFilename());
		System.out.println(path);
		writeByteArrayToFile(new File(path), img.getBytes());
		return path;
	}
	
	public byte[] getImage(String path) throws IOException {
		return readFileToByteArray(new File(path));
	}
}
