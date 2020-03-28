package com.ruczajsoftware.workoutrival.model.email;

import lombok.Getter;

public enum EmailTemplates {
    RESET_PASSWORD("reset_password");

    @Getter
    private String key;

    EmailTemplates(String key) {
        this.key = key;
    }
}
