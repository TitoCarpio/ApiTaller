package com.erickcg.API.controllers.dto;


import java.util.Date;

//import java.util.ArrayList;


import com.erickcg.API.models.dto.SuperGenericResponse;


import lombok.Data;

@Data

public class AllUserResDto  {
	private String name;
	private String secondName;
	private String email;
	private Date dateOfHire;
	private String rol;
	
}
