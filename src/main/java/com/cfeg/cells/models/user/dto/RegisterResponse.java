package com.cfeg.cells.models.user.dto;

import com.cfeg.cells.models.user.enums.Role;

import java.time.LocalDate;

public record RegisterResponse(
        String name,
        String email,
        String phone,
        Role role,
        LocalDate birthDate
) {
}
