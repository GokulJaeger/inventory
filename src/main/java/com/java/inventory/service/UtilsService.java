package com.java.inventory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.UtilsDto;
import com.java.inventory.entity.Utils;
import com.java.inventory.repository.UtilsRepository;
import com.java.inventory.vo.Response;

@Service
public class UtilsService {
	private static final Logger logger = LoggerFactory.getLogger(UtilsService.class);

	private final UtilsRepository utilsRepository;

	public UtilsService(UtilsRepository utilsRepository) {
		this.utilsRepository = utilsRepository;
	}

	public List<UtilsDto> readAllUtils(Response response) {
		logger.info("UtilsService readAllUtils STARTED");
		logger.info("UtilsService readAllUtils Utils all details fetch");
		List<UtilsDto> resposonseUtilsDtoList = new ArrayList<>();
		List<Utils> utilsList = utilsRepository.findAll();

		for (Utils util : utilsList) {
			UtilsDto utilDto = new UtilsDto();
			BeanUtils.copyProperties(util, utilDto);
			resposonseUtilsDtoList.add(utilDto);
		}
		if (resposonseUtilsDtoList.isEmpty()) {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage(Constants.NO_DATA_FOUND);
			response.setStatusCode(Constants.NO_CONTENT);
		} else {
			response.setTitle(Constants.TITLE_OK);
			response.setMessage(Constants.UTILS_FETCH_ALL);
			response.setStatusCode(Constants.OK);
		}
		logger.info("UtilsService readAllUtils ENDED");
		return resposonseUtilsDtoList;
	}

	public UtilsDto readUtils(UtilsDto requestUtilsDto, Response response) {
		logger.info("UtilsService readUtils STARTED");
		UtilsDto resposonseUtilsDto = new UtilsDto();
		logger.info("UtilsService readUtils Utils details fetch for: {}", requestUtilsDto.getName());
		Optional<Utils> utils = utilsRepository.findByName(requestUtilsDto.getName());
		if (utils.isPresent()) {
			Utils newUtils = utils.get();
			BeanUtils.copyProperties(newUtils, resposonseUtilsDto);
			response.setTitle(Constants.TITLE_OK);
			response.setMessage(Constants.UTILS_FETCH);
			response.setStatusCode(Constants.OK);
		} else {
			resposonseUtilsDto = null;
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage(Constants.NO_DATA_FOUND);
			response.setStatusCode(Constants.NO_CONTENT);
		}
		logger.info("UtilsService readUtils ENDED");
		return resposonseUtilsDto;
	}

	public UtilsDto createUtils(UtilsDto requestUtilsDto, Response response) {
		logger.info("UtilsService createUtils STARTED");
		UtilsDto responseUtilsDto = new UtilsDto();
		logger.info("UtilsService createUtils Utils create request for: {}", requestUtilsDto.getName());
		if (requestUtilsDto.getName() != null) {
			Optional<Utils> existingUtils = utilsRepository.findByName(requestUtilsDto.getName());
			if (!existingUtils.isPresent()) {
				Utils utils = new Utils();
				utils.setName(requestUtilsDto.getName());
				utils.setCreatedAt(new Date());
				utils.setUpdatedAt(new Date());
				utils.setActive(true);
				utils = utilsRepository.save(utils);
				BeanUtils.copyProperties(utils, responseUtilsDto);
				response.setMessage(Constants.UTILS_CREATED);
				response.setStatusCode(Constants.CREATED);
				response.setTitle(Constants.TITLE_CREATED);
			} else {
				response.setMessage(Constants.UTILS_EXIST);
				response.setStatusCode(Constants.BAD_REQUEST);
				response.setTitle(Constants.TITLE_BAD_REQUEST);
			}
		} else {
			response.setMessage(Constants.UTILS_NAME_NULL);
			response.setStatusCode(Constants.BAD_REQUEST);
			response.setTitle(Constants.TITLE_BAD_REQUEST);
		}
		logger.info("UtilsService createUtils ENDED");
		return responseUtilsDto;
	}

	public UtilsDto updateUtils(UtilsDto requestUtilsDto, Response response) {
		logger.info("UtilsService updateUtils STARTED");
		UtilsDto responseUtilsDto = new UtilsDto();
		logger.info("UtilsService updateUtils Utils update request for: {}", requestUtilsDto.getName());
		if (requestUtilsDto.getName() != null) {
			Optional<Utils> existingUtils = utilsRepository.findById(requestUtilsDto.getId());
			if (existingUtils.isPresent()) {
				Utils utils = existingUtils.get();
				utils.setName(requestUtilsDto.getName());
				utils.setUpdatedAt(new Date());
				utils.setActive(true);
				utils = utilsRepository.save(utils);
				BeanUtils.copyProperties(utils, responseUtilsDto);
				response.setMessage(Constants.UTILS_UPDATED);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			} else {
				response.setMessage(Constants.UTILS_NOTEXIST);
				response.setStatusCode(Constants.BAD_REQUEST);
				response.setTitle(Constants.TITLE_BAD_REQUEST);
			}
		} else {
			response.setMessage(Constants.UTILS_NAME_NULL);
			response.setStatusCode(Constants.BAD_REQUEST);
			response.setTitle(Constants.TITLE_BAD_REQUEST);
		}
		logger.info("UtilsService updateUtils ENDED");
		return responseUtilsDto;
	}

	public void deleteUtils(UtilsDto requestUtilsDto, Response response) {
		logger.info("UtilsService deleteUtils STARTED");
		logger.info("UtilsService deleteUtils Utils delete request for: {}", requestUtilsDto.getName());
		if (requestUtilsDto.getName() != null) {
			Optional<Utils> existingDeleteUtils = utilsRepository.findByName(requestUtilsDto.getName());
			if (existingDeleteUtils.isPresent()) {
				utilsRepository.deleteById(existingDeleteUtils.get().getId());
				response.setMessage(Constants.UTILS_DELETED);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			} else {
				response.setMessage(Constants.UTILS_NOTEXIST);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			}
		} else {
			response.setMessage(Constants.UTILS_NAME_NULL);
			response.setStatusCode(Constants.BAD_REQUEST);
			response.setTitle(Constants.TITLE_BAD_REQUEST);
		}
		logger.info("UtilsService deleteUtils ENDED");
	}

}
