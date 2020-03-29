package com.ruczajsoftware.workoutrival.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityConflictException extends Exception {

	public EntityConflictException(ExceptionMessages message) {
		super(message.getMsg());
	}
}
