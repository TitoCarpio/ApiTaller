package com.erickcg.API.controllers.dto;

import com.erickcg.API.models.dto.SuperGenericResponse;


import lombok.Data;

@Data
public class LoginResDto extends SuperGenericResponse  {
	private String name;
	private String secondName;
	private String email;
	private String rol;
}


