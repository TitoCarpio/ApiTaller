package com.erickcg.API.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.erickcg.API.controllers.dto.ChangePassReqDto;
import com.erickcg.API.controllers.dto.ChangeStateReqDto;
import com.erickcg.API.controllers.dto.LoginReqDto;
import com.erickcg.API.controllers.dto.LoginResDto;

import com.erickcg.API.controllers.dto.SaveUserDto;
import com.erickcg.API.services.IUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("API/v1/")
@CrossOrigin("*")
public class UserController {
	// implementando los servicios
	@Autowired
	private IUser userServices;

	// metodo PostMapping para el inicio de sesion del usuario mediante correo y
	// contrasena
	// solicit un json y devuelve un json
	@PostMapping(value = "/auth/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@RequestBody @Valid LoginReqDto loginInfo, BindingResult validations) {
		LoginResDto login = null;

		// Por si detectamos errores de validacion de
		if (validations.hasErrors()) {
			login = new LoginResDto();
			login.setCode(-1);
			login.setMessage("Correo o contra vacia");
			return new ResponseEntity<>(login, HttpStatus.BAD_REQUEST);
		}

		login = userServices.signin(loginInfo);
		return new ResponseEntity<LoginResDto>(login, HttpStatus.OK);

	}

	// Peticion para obtener todos los usuarios, con los parametros definidos en la
	// guia
	@GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> allUser() {
		return new ResponseEntity<>(userServices.findAllUser(), HttpStatus.OK);
	}

	// Peticion para obtener solo un usuario mediante el codigo de este
	@GetMapping(value = "/user/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userByCode(@PathVariable String code) {
		return new ResponseEntity<>(userServices.userByCode(code.toString()), HttpStatus.OK);
	}

	
	@PostMapping(value = "/user/")
	public ResponseEntity<?> postUser(
			@RequestBody @Valid SaveUserDto information, BindingResult validations) {
		if (validations.hasErrors()) {
			String message = validations.getFieldErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining(", "));
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		userServices.newUser(information);
		return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
	}
	
	
	@PatchMapping(value = "/user/change-password")
	public ResponseEntity<?> changePass (@RequestBody @Valid ChangePassReqDto infoUser, BindingResult validations){
		//verificando que todos los campos vengan llenos
		if (validations.hasErrors()) {
			return new ResponseEntity<>("Los datos no son validos", HttpStatus.BAD_REQUEST);
		}
		
		userServices.changePass(infoUser);
		
		return new ResponseEntity<>("Cambio realizado", HttpStatus.OK);
	}
	
	@PatchMapping(value ="/user/toggle-active")
	public ResponseEntity<?> changeState (@RequestBody @Valid ChangeStateReqDto infoUser, BindingResult validations ){
		//verificando que todos los campos vengan llenos
				if (validations.hasErrors()) {
					return new ResponseEntity<>("Los datos no son validos", HttpStatus.BAD_REQUEST);
				}
				
			userServices.changeState(infoUser);
			
		
			return new ResponseEntity<>("Se realizo el cambio de estado", HttpStatus.OK);
	}
}
