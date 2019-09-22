package com.ruczajsoftware.workoutrival.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends Exception{

	public UnAuthorizedException(String message) {
		super(message);
	}
}
