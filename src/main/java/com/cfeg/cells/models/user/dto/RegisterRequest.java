package com.cfeg.cells.models.user.dto;

import com.cfeg.cells.models.user.enums.Role;

import java.time.LocalDate;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String phone,
        Role role,
        LocalDate birthDate
) {}
