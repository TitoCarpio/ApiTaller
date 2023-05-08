package com.erickcg.API.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("API/v1/")
public class TestController {
	@GetMapping(value="/testConnecction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> testConection(){
		
		return new ResponseEntity<>("Conexion exitosa XD", HttpStatus.OK);
	}

}
