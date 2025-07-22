package com.cfeg.cells.models.user.dto;

public record LoginRequest (
        String email,
        String password
) {}
