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

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(purchaseService.getAll(page, size));
    }

    @GetMapping("/get-by-supplier")
    public ResponseEntity<?> getAllBySupplierId(@RequestParam UUID supplierId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(purchaseService.getAllBySupplierId(supplierId, page, size));
    }

    @GetMapping("/get-by-product")
    public ResponseEntity<?> getAllByProductId(@RequestParam UUID productId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(purchaseService.getAllByProductId(productId, page, size));
    }
}
