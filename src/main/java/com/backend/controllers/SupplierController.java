package com.backend.controllers;

import com.backend.modals.dto.request.SupplierRequest;
import com.backend.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SupplierController {

    private final SupplierService supplierService;


    @PostMapping("/create")
    public ResponseEntity<Boolean> createSupplier(@RequestBody SupplierRequest request) {
        return supplierService.createSupplier(request) ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateSupplier(@RequestBody SupplierRequest request) {
        return supplierService.updateSupplier(request) ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getSuppliers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(supplierService.getSuppliers(page, size));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getSupplierById(@RequestParam UUID id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteSupplier(@RequestParam UUID id) {
        return supplierService.deleteSupplier(id) ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }
}