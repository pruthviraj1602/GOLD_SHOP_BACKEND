package com.backend.controllers;

import com.backend.modals.purchase.dto.PurchaseRequest;
import com.backend.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PurchaseController {

    private final PurchaseService purchaseService;


    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequest request) {
        return ResponseEntity.ok(purchaseService.createPurchase(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePurchase(@RequestBody PurchaseRequest request) {
        return ResponseEntity.ok(purchaseService.updatePurchase(request));
    }


    @GetMapping("/get-by-id")
    public ResponseEntity<?> getPurchase(@RequestParam UUID id){
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }
}
