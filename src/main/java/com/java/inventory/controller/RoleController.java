package com.java.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.constant.Constants;
import com.java.inventory.service.RoleService;
import com.java.inventory.vo.Response;

@RequestMapping(value = Constants.API_ROLE)
@RestController
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	private RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping(value = Constants.API_ROLE_GET_ALL)
	public ResponseEntity<Response> getAllRole() {
		logger.info("RoleController getAllRole STARTED");
		Response response = new Response();
		// read All Roles
		response.setData(roleService.readAllRole(response));
		response.setInstance(Constants.API_ROLE.concat(Constants.API_ROLE_GET_ALL));
		logger.info("RoleController getAllRole ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
