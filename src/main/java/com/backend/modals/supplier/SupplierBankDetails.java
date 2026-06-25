package com.backend.modals.supplier;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SupplierBankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String bankAccountNumber;

    private String bankName;

    private String ifscCode;

    private String branchName;

    private String accountHolderName;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
