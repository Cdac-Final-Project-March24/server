package com.app.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ImageHandelingService;


@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {
	
	@Autowired
	private ImageHandelingService imageService;
	
	@GetMapping(value = "/{path}", produces = 
			{ MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable String path) throws IOException{
		return ResponseEntity.status(HttpStatus.OK)
				.body(imageService.getImage(("images/").concat(path)));
	}
	
}
