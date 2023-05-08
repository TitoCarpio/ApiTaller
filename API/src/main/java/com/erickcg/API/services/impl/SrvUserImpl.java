package com.erickcg.API.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.erickcg.API.controllers.dto.AllUserResDto;
import com.erickcg.API.controllers.dto.ChangePassReqDto;
import com.erickcg.API.controllers.dto.ChangeStateReqDto;
import com.erickcg.API.controllers.dto.LoginReqDto;
import com.erickcg.API.controllers.dto.LoginResDto;

import com.erickcg.API.controllers.dto.SaveUserDto;
import com.erickcg.API.controllers.dto.UserResDto;
import com.erickcg.API.models.entities.User;
import com.erickcg.API.services.IUser;
import java.util.stream.Collectors;

@Service
public class SrvUserImpl implements IUser {
	// creando la lista de usuarios
	private static List<User> users = new ArrayList<>();
	private static DateFormat date = new SimpleDateFormat("dd/MM/yyyy");

	static {
		try {
			users.add(new User("AA252525", "Erick", "Carpio", "titocarpio9@gmail.com", date.parse("01/01/2023"),
					"ACTIVO", "ADMIN", "123456"));
			users.add(new User("BB303030", "Tito", "Guerra", "carpio9@gmail.com", date.parse("01/02/2023"), "INACTIVO",
					"USER", "123456"));
			users.add(new User("CC515151", "Carlos", "Lopez", "tito9@gmail.com", date.parse("01/04/2023"), "ACTIVO",
					"USER", "123456"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public LoginResDto signin(LoginReqDto loginInfo) {
		LoginResDto log = new LoginResDto();

		// buscando el usuario
		User user = users.stream()
				.filter(u -> u.getEmail().equals(loginInfo.getEmail()))
				.findFirst().orElse(null);

		// validando la contraseña
		if (user != null && user.getPassword().equals(loginInfo.getPassword())) {
			log.setCode(0);
			log.setMessage("OK");
			log.setName(user.getName());
			log.setSecondName(user.getSecondName());
			log.setEmail(user.getEmail());
			log.setRol(user.getRol());

			return log;
		} else {
			log.setCode(1);
			log.setMessage("Usuario o contraseña incorrectos");
			return log;
		}
	}

	@Override
	public List<AllUserResDto> findAllUser() {
		// creando lista del tipo AllUserResDto
		List<AllUserResDto> allUser = new ArrayList<AllUserResDto>();
		// creo una variable de tipo AllUserResDto
		AllUserResDto all = new AllUserResDto();
		// recorriendo la lista de usuarios
		for (User user : users) {
			// llenando la variable all

			all.setName(user.getName());
			all.setSecondName(user.getSecondName());
			all.setEmail(user.getEmail());
			all.setDateOfHire(user.getDateOfHire());

			all.setRol(user.getRol());
			// agregando la variable all a la lista allUser
			allUser.add(all);
			// limpiando la variable all
			all = new AllUserResDto();
		}

		return allUser;

	}

	@Override
	public UserResDto userByCode(String code) {

		// creando una variable de tipo UserResDto
		UserResDto userRes = new UserResDto();
		// verificando si el codigo es nulo

		if (code != null) {
			// buscando el usuario
			User user = users.stream()
					.filter(u -> u.getCode().equals(code))
					.findFirst().orElse(null);

			// validando si el usuario es nulo
			if (user != null) {
				userRes.setCode(0);
				userRes.setMessage("OK");
				userRes.setName(user.getName());
				userRes.setSecondName(user.getSecondName());
				userRes.setEmail(user.getEmail());
				userRes.setDateOfHire(user.getDateOfHire());
				userRes.setRol(user.getRol());
				return userRes;
			} else {
				userRes.setCode(1);
				userRes.setMessage("Usuario no encontrado");
				return userRes;
			}
		}

		return userRes;
	}

	// metodo para encriptar contrasena
	private String encriptar(String password) {
		// cambiar numeros por letras
		String passwordEncriptada = password.replace("1", "a");
		passwordEncriptada = passwordEncriptada.replace("2", "b");
		passwordEncriptada = passwordEncriptada.replace("3", "c");
		passwordEncriptada = passwordEncriptada.replace("4", "d");
		passwordEncriptada = passwordEncriptada.replace("5", "e");
		passwordEncriptada = passwordEncriptada.replace("6", "f");
		passwordEncriptada = passwordEncriptada.replace("7", "g");
		passwordEncriptada = passwordEncriptada.replace("8", "h");
		passwordEncriptada = passwordEncriptada.replace("9", "i");
		passwordEncriptada = passwordEncriptada.replace("0", "j");

		return passwordEncriptada;
	}

	// @Override
	// public NewUserResDto newUser(NewUserReqDto infoUser) {
	//
	// // creando una variable de tipo NewUserResDto
	// NewUserResDto newUser = new NewUserResDto();
	// // creando una variable de tipo User
	// User userNew = new User();
	//
	// //verificando si el usuario existe
	// User userExist = users.stream()
	// .filter(u -> u.getEmail().equals(infoUser.getEmail()))
	// .findFirst().orElse(null);
	//
	// //validando si el usuario existe
	// if (userExist != null) {
	// newUser.setCode(1);
	// newUser.setMessage("El usuario ya existe");
	// return newUser;
	// } else {
	// // creando el nuevo usuario
	// userNew.setCode(infoUser.getCode());
	// userNew.setName(infoUser.getName());
	// userNew.setSecondName(infoUser.getSecondName());
	// userNew.setEmail(infoUser.getEmail());
	// userNew.setDateOfHire(infoUser.getDateOfHire());
	// userNew.setState(infoUser.getState());
	// userNew.setRol(infoUser.getRol());
	// userNew.setPassword(encriptar(infoUser.getPassword()));
	// users.add(userNew);
	//
	// newUser.setCode(0);
	// newUser.setMessage("OK Usuario creado con exito");
	// newUser.setEmail(infoUser.getEmail());
	// newUser.setPassword(userNew.getPassword());
	//
	// //agregando el nuevo usuario a la lista
	// //users.add(userNew);
	// users = Stream.concat(users.stream(), Stream.of(userNew))
	// .collect(Collectors.toList());
	//
	//
	// return newUser;
	// }
	//
	//
	// }

	@Override
	public void newUser(SaveUserDto info) {
		User postUser = new User(
				info.getCode(),
				info.getName(),
				info.getSecondName(),
				info.getEmail(),
				info.getDateOfHire(),
				info.getState(),
				info.getRol(),
				encriptar(info.getPassword()));

		// solo para conocer la contrasena encriptada
		System.out.println(encriptar(postUser.getPassword()));

		users = Stream.concat(users.stream(), Stream.of(postUser))
				.collect(Collectors.toList());
	}

	// funcion que permite cambiar la contrasena
	private void change(String code, String password) {
		// cambiando la contrasena
		for (User p : users) {
			if (p.getCode().equals(code)) {
				p.setPassword(password);
			}
		}
	}

	// funcion que permite cambiar el estado de un usuario de la lista
	private void cState(String code, String state) {
		// cambiando el estado
		if (state.equals(("ACTIVO"))) {
			state = "INACTIVO";
			for (User p : users) {
				if (p.getCode().equals(code)) {
					p.setState(state);
				}

			}
			System.out.println("se realizo el cambio de estado a INACTIVO");
		} else {
			state = "ACTIVO";
			for (User p : users) {
				if (p.getCode().equals(code)) {
					p.setState(state);
				}
			}
			System.out.println("se realizo el cambio de estado a Activo");
		}

	}

	@Override
	public void changePass(ChangePassReqDto infoUser) {
		// verificando si el usuario existe
		User userExist = users.stream()
				.filter(u -> u.getCode().equals(infoUser.getCode()))
				.findFirst().orElse(null);

		// validando si el usuario existe
		if (userExist != null) {
			// validando si la contrasena es correcta
			if (userExist.getPassword().equals(infoUser.getPassword())) {
				// cambiando la contrasena
				userExist.setPassword(encriptar(infoUser.getNewPass()));
				System.out.println(encriptar(infoUser.getNewPass()));
				// modificando el usuario de la lista users
				change(infoUser.getCode(), encriptar(infoUser.getNewPass()));

			} else {
				System.out.println("Contrasena incorrecta");
			}
		} else {
			System.out.println("Usuario no encontrado");
		}

	}

	@Override
	public void changeState(ChangeStateReqDto infoUser) {
		// buanco si el usuario existe
		User userExist = users.stream()
				.filter(u -> u.getCode().equals(infoUser.getCode()))
				.findFirst().orElse(null);

		// validando si el usuario existe
		if (userExist != null) {
			cState(infoUser.getCode(), userExist.getState());
		} else {
			System.out.println("Usuario no encontrado");
		}

	}

}
