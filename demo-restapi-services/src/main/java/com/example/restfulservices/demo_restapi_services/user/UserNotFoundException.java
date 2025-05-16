package com.example.restfulservices.demo_restapi_services.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	


	public UserNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
