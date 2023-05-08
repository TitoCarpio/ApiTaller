package com.erickcg.API.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeStateReqDto {
	@NotEmpty(message = "El campo es requerido")
	@Size(min = 8, max = 8, message = "Code must be 8 characters long")
	private String code;

}
