package com.erickcg.API.models.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String code;
	private String name;
	private String secondName;
	private String email;
	private Date dateOfHire;
	private String state;
	private String rol;
	private String password;

}
