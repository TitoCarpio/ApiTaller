package com.erickcg.API.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePassReqDto {
	@NotEmpty(message = "El campo es requerido")
	@Size(min = 6, max = 8, message = "Code must be 8 characters long")
	private String code;
	
	@NotEmpty(message = "El campo es requerido")
	@Size(min = 5, max = 20, message = "Code must be 8 characters long")
	private String password;
	
	@NotEmpty(message = "El campo es requerido")
	@Size(min = 5, max = 20, message = "Code must be 8 characters long")
	private String newPass;

}
