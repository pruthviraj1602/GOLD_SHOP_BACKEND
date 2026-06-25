package com.backend.modals.dto.request;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
