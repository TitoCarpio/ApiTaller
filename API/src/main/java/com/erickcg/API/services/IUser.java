package com.erickcg.API.services;

import java.util.List;

import com.erickcg.API.controllers.dto.AllUserResDto;
import com.erickcg.API.controllers.dto.ChangePassReqDto;
import com.erickcg.API.controllers.dto.ChangeStateReqDto;
import com.erickcg.API.controllers.dto.LoginReqDto;
import com.erickcg.API.controllers.dto.LoginResDto;

import com.erickcg.API.controllers.dto.SaveUserDto;
import com.erickcg.API.controllers.dto.UserResDto;

public interface IUser {
	//servicio que permite el inicio de sesion 
	LoginResDto signin(LoginReqDto loginInfo);

	//servicio que devulve todos los usuarios
	List<AllUserResDto> findAllUser();

	//servicio que busca un usuario por id
	UserResDto userByCode(String code);

	//servicio que guarda un nuevo usuario
	void newUser(SaveUserDto info);
	
	//servicio que realiza el cambio de contrasena de un usuario
	void changePass(ChangePassReqDto infoUser);
	
	//servicio que modifica el estado de un usuario
	void changeState(ChangeStateReqDto infoUser);

}
