package com.backend.modals.purchase;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PurchaseProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Float weight;

    private Float stoneWeight;

    private Float netWeight;

    private BigDecimal rate;

    private BigDecimal sellingPrice;

    private BigDecimal makingCharges;

    private BigDecimal stoneValue;

    private String gstSlab;

    private Integer purchaseQuantity;


    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
