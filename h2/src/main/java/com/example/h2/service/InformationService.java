package com.example.h2.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.h2.helper.CSVHelper;
import com.example.h2.model.Information;
import com.example.h2.repository.InformationRepository;

@Service
public class InformationService {

	@Autowired
	private InformationRepository infoRepository;
	
	public void save(MultipartFile file) {
	    try {
	      List<Information> info = CSVHelper.csvToTutorials(file.getInputStream());
	      infoRepository.saveAll(info);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }

	  public List<Information> getAllInformation() {
	    return infoRepository.findAll();
	  }

}

