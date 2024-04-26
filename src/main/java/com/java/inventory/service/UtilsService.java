package com.java.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.java.inventory.dto.UtilsDto;
import com.java.inventory.entity.Utils;
import com.java.inventory.repository.UtilsRepository;

@Service
public class UtilsService {
	
	private final UtilsRepository utilsRepository;
	
	public UtilsService (UtilsRepository utilsRepository) {
		this.utilsRepository = utilsRepository;
	}
	
	public List<UtilsDto> getAllUtils(){
		List<UtilsDto> resposonseUtilsDtoList = new ArrayList<>();
		List<Utils> utilsList = utilsRepository.findAll();
		
		for(Utils util: utilsList) {
			UtilsDto utilDto = new UtilsDto();
			BeanUtils.copyProperties(util, utilDto);
			resposonseUtilsDtoList.add(utilDto);
		}
		return resposonseUtilsDtoList;
	}

}
