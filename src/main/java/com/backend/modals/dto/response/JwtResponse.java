package com.backend.modals.dto.response;

import com.backend.modals.dto.request.RoleDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class JwtResponse {
    private UUID userId;
    private String username;
    private String name;
    private String jwtToken;
    private RoleDto role;
    private Boolean isLoggedIn;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}