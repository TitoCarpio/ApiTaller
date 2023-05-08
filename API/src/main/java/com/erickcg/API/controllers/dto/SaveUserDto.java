package com.erickcg.API.controllers.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserDto {
	@NotEmpty(message = "code required")
	@Size(min = 8, max = 8, message = "Code must be 8 characters long")
	private String code;
	@NotEmpty(message = "name required")
	private String name;
	@NotEmpty(message = "email required")
	@Email(message = "Email should be valid")
	private String email;
	@NotEmpty(message = "secondName required")
	private String secondName;
	@NotNull(message = "dateOfHire required")
	private Date dateOfHire;
	@NotEmpty(message = "state required")
	private String state;
	@NotEmpty(message = "rol required")
	private String rol;
	@NotEmpty(message = "password required")
	private String password;
}
