package com.ruczajsoftware.workoutrival.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityConflictException extends Exception {

	public EntityConflictException(String message) {
		super(message);
	}
}
