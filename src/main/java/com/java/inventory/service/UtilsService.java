package com.java.inventory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.UtilsDto;
import com.java.inventory.entity.Utils;
import com.java.inventory.repository.UtilsRepository;
import com.java.inventory.vo.Response;

@Service
public class UtilsService {

	private final UtilsRepository utilsRepository;

	public UtilsService(UtilsRepository utilsRepository) {
		this.utilsRepository = utilsRepository;
	}

	public List<UtilsDto> readAllUtils(Response response) {
		List<UtilsDto> resposonseUtilsDtoList = new ArrayList<>();
		List<Utils> utilsList = utilsRepository.findAll();

		for (Utils util : utilsList) {
			UtilsDto utilDto = new UtilsDto();
			BeanUtils.copyProperties(util, utilDto);
			resposonseUtilsDtoList.add(utilDto);
		}
		if (resposonseUtilsDtoList.isEmpty()) {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage("No data found");
			response.setStatusCode(Constants.NO_CONTENT);
		} else {
			response.setTitle(Constants.TITLE_OK);
			response.setMessage("All Utils details successfully fetched");
			response.setStatusCode(Constants.OK);
		}
		return resposonseUtilsDtoList;
	}

	public UtilsDto readUtils(UtilsDto requestUtilsDto, Response response) {
		UtilsDto resposonseUtilsDto = new UtilsDto();
		Optional<Utils> utils = utilsRepository.findByName(requestUtilsDto.getName());

		if (utils.isPresent()) {
			Utils newUtils = utils.get();
			BeanUtils.copyProperties(newUtils, resposonseUtilsDto);
			response.setTitle(Constants.TITLE_OK);
			response.setMessage("Utils details successfully fetched");
			response.setStatusCode(Constants.OK);
		} else {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage("No data found");
			response.setStatusCode(Constants.NO_CONTENT);
		}
		return resposonseUtilsDto;
	}

	public UtilsDto createUtils(UtilsDto requestUtilsDto, Response response) {
		UtilsDto responseUtilsDto = new UtilsDto();
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
		return responseUtilsDto;
	}

	public UtilsDto updateUtils(UtilsDto requestUtilsDto, Response response) {
		UtilsDto responseUtilsDto = new UtilsDto();
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

		return responseUtilsDto;
	}

	public void deleteUtils(UtilsDto requestUtilsDto, Response response) {
		if (requestUtilsDto.getName() != null) {
			Optional<Utils> existingUtils = utilsRepository.findByName(requestUtilsDto.getName());
			if (existingUtils.isPresent()) {
				Utils newUtils = existingUtils.get();
				newUtils.setActive(false);
				utilsRepository.save(newUtils);
				Optional<Utils> existingDeleteUtils = utilsRepository.findByName(requestUtilsDto.getName());
				if (existingDeleteUtils.isPresent()) {
					Utils utilsNew = existingDeleteUtils.get();
					if (!utilsNew.isActive()) {
						response.setMessage(Constants.UTILS_DELETED);
						response.setStatusCode(Constants.OK);
						response.setTitle(Constants.TITLE_OK);
					} else {
						response.setMessage("Unable to delete Utils!... Contact Administrator");
						response.setStatusCode(Constants.OK);
						response.setTitle(Constants.TITLE_OK);
					}
				} else {
					response.setMessage(Constants.UTILS_ERROR);
					response.setStatusCode(Constants.BAD_REQUEST);
					response.setTitle(Constants.TITLE_BAD_REQUEST);
				}

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
	}

}
