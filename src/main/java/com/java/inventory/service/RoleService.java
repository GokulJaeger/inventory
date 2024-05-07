package com.java.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.RoleDto;
import com.java.inventory.entity.Role;
import com.java.inventory.repository.RoleRepository;
import com.java.inventory.vo.Response;

import jakarta.transaction.Transactional;

@Service
public class RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Transactional
	public List<RoleDto> readAllRole(Response response) {
		logger.info("RoleService readAllRole STARTED");
		List<RoleDto> responseRoleDto = new ArrayList<>();
		try {
			List<Role> roleList = roleRepository.findAll();
			roleList.forEach(System.out::println);
			responseRoleDto = roleEntitytoDto(roleList);

		} catch (Exception e) {
			logger.info("RoleService readAllRole ERROR: {}", e.getMessage());
			response.setMessage(Constants.ROLE_ERROR);
			response.setTitle(Constants.TITLE_INTERNAL_SERVER_ERROR);
			response.setStatusCode(Constants.INTERNAL_SERVER_ERROR);
		}
		if (!responseRoleDto.isEmpty()) {
			response.setMessage(Constants.ROLE_FETCH_ALL);
			response.setTitle(Constants.TITLE_OK);
			response.setStatusCode(Constants.OK);
		} else {
			response.setMessage(Constants.NO_DATA_FOUND);
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setStatusCode(Constants.NO_CONTENT);
		}
		logger.info("RoleService readAllRole ENDED");
		return responseRoleDto;
	}

	@Transactional
	public List<RoleDto> roleEntitytoDto(List<Role> roleList) {
		logger.info("RoleService roleEntitytoDto Called!...");
		return roleList.stream().map(role -> new RoleDto(role.getId(), role.getName().toString())).toList();
	}
}
