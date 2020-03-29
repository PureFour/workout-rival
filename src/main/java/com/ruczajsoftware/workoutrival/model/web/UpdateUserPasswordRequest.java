package com.ruczajsoftware.workoutrival.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordRequest {
    private String email;
    private String newPassword;
    private String pin;
}
