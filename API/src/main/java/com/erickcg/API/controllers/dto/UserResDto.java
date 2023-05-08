package com.erickcg.API.controllers.dto;

import java.util.Date;

import com.erickcg.API.models.dto.SuperGenericResponse;

import lombok.Data;

@Data
public class UserResDto extends SuperGenericResponse {
	private String name;
	private String secondName;
	private String email;
	private Date dateOfHire;
	private String rol;
}
