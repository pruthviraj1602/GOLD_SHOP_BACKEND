package com.backend.modals.supplier;

import com.backend.modals.BaseAuditableEntity;
import com.backend.modals.leadger.Ledger;
import com.backend.modals.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Supplier extends BaseAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String companyName;

    private String supplierType;

    private String taxAddress;

    private String registeredBillingAddress;

    private String shippingWarehouseAddress;

    private String paymentTerms;

    private String status;

    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ledger ledger;

    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private SupplierContactDetails supplierContactDetails;

    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private SupplierBankDetails supplierBankDetails;

    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private SupplierGstInformation supplierGstInformation;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases;
}
