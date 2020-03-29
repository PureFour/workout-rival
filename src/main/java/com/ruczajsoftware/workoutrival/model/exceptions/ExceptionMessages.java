package com.ruczajsoftware.workoutrival.model.exceptions;

import lombok.Getter;

public enum ExceptionMessages {
    USER_NOT_FOUND("User not found in database"),
    PASSWORD_CONFLICT("Password must be different"),
    INCORRECT_CREDENTIALS("Incorrect username or password"),
    EMAIL_CONFLICT("Email must be different"),
    EMAIL_INCORRECT("Incorrect email"),
    USER_CONFLICT("User exists in database"),
    PIN_INCORRECT("Incorrect PIN"),
    PIN_NOT_FOUND("Cannot find reset password PIN for given email");

    @Getter
    private String msg;

    ExceptionMessages(String msg) { this.msg = msg; }
}
