package com.backend.modals.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierContactDetailsRequest {
    private UUID id;

    private String primaryContactNumber;

    private String secondaryContactNumber;

    private String landlineNumber;

    private String email;
}
