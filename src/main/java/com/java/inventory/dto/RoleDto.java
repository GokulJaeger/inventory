package com.java.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.java.inventory.entity.RoleEnum;

@JsonInclude(Include.NON_NULL)
public class RoleDto {
	private Integer id;
	private String name;

	public RoleDto() {
	}

	public RoleDto(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
