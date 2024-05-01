package com.java.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.UtilsDto;
import com.java.inventory.service.UtilsService;
import com.java.inventory.vo.Response;

@RequestMapping(Constants.API_UTILS)
@RestController
public class UtilsController {

	private static final Logger logger = LoggerFactory.getLogger(UtilsController.class);
	private final UtilsService utilsService;

	public UtilsController(UtilsService utilsService) {
		this.utilsService = utilsService;
	}
	
	@GetMapping(Constants.API_UTILS_GET_ALL)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Response> getAllUtils() {
		logger.info("UtilsController getAllUtils STARTED");
		Response response = new Response();
		response.setData(utilsService.readAllUtils(response));
		response.setInstance(Constants.API_UTILS.concat(Constants.API_UTILS_GET_ALL));
		logger.info("UtilsController getAllUtils ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(Constants.API_UTILS_GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Response> getUtils(@RequestBody UtilsDto requestUtilsDto) {
		logger.info("UtilsController getUtils STARTED");
		Response response = new Response();
		response.setData(utilsService.readUtils(requestUtilsDto, response));
		response.setInstance(Constants.API_UTILS.concat(Constants.API_UTILS_GET));
		logger.info("UtilsController getUtils ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(Constants.API_UTILS_POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response> postUtils(@RequestBody UtilsDto requestUtilsDto) {
		logger.info("UtilsController postUtils STARTED");
		Response response = new Response();
		response.setData(utilsService.createUtils(requestUtilsDto, response));
		response.setInstance(Constants.API_UTILS.concat(Constants.API_UTILS_POST));
		logger.info("UtilsController postUtils ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PutMapping(Constants.API_UTILS_PUT)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response> putUtils(@RequestBody UtilsDto requestUtilsDto) {
		logger.info("UtilsController putUtils STARTED");
		Response response = new Response();
		response.setData(utilsService.updateUtils(requestUtilsDto, response));
		response.setInstance(Constants.API_UTILS.concat(Constants.API_UTILS_POST));
		logger.info("UtilsController putUtils ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(Constants.API_UTILS_DELETE)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response> deleteUtils(@RequestBody UtilsDto requestUtilsDto) {
		logger.info("UtilsController deleteUtils STARTED");
		Response response = new Response();
		utilsService.deleteUtils(requestUtilsDto, response);
		response.setInstance(Constants.API_UTILS.concat(Constants.API_UTILS_DELETE));
		logger.info("UtilsController deleteUtils ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
