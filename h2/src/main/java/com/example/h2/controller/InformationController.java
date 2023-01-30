package com.example.h2.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.h2.helper.CSVHelper;
import com.example.h2.helper.ResponseMessage;
import com.example.h2.model.Information;
import com.example.h2.repository.InformationRepository;
import com.example.h2.service.InformationService;

@RestController
@RequestMapping("/api/csv")
public class InformationController {

	org.slf4j.Logger logger = LoggerFactory.getLogger(InformationController.class);

	@Autowired
	InformationService fileService;

	@Autowired
	private InformationRepository infoRepository;
	
	// Uploading data by passing csv file through API.
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (!file.isEmpty() && CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				System.out.println(e);
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	// fetching all Information.
	@GetMapping("/info")
	public ResponseEntity<List<Information>> getAllInformation() {
		try {
			List<Information> tutorials = fileService.getAllInformation();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Getting Information by passing primary Key.
	@GetMapping("/info/{id}")
	public Information getNoteById(@PathVariable(value = "id") Long Id) throws FileNotFoundException {

		return infoRepository.findById(Id).orElseThrow(() -> new FileNotFoundException());
	}

	// Delete Information by passing primary Key.
	@DeleteMapping("/info/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long Id) throws FileNotFoundException {
		Information book = infoRepository.findById(Id).orElseThrow(() -> new FileNotFoundException());
		infoRepository.delete(book);

		return ResponseEntity.ok().build();
	}

}
