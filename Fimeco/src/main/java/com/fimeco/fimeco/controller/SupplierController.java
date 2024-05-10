package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.address.DataAddress;
import com.fimeco.fimeco.domain.supplier.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseSupplier> registerSupplier(@RequestBody @Valid DataRegisterSupplier dataRegisterSupplier){
        Supplier supplier = supplierRepository.save(new Supplier(dataRegisterSupplier));
        DataResponseSupplier dataResponseSupplier = new DataResponseSupplier(supplier.getId(), supplier.getName(), supplier.getEmail(),
                supplier.getPhone(), new DataAddress(supplier.getAddress().getStreet(), supplier.getAddress().getRace(), supplier.getAddress().getNumber(),
                supplier.getAddress().getDepartment(), supplier.getAddress().getCity(), supplier.getAddress().getComplement(), supplier.getAddress().getCountry(),
                supplier.getAddress().getAddressComplete()));
        return ResponseEntity.ok(dataResponseSupplier);
    }

    @GetMapping
    public ResponseEntity<Page<DataListSupplier>> listSuppliers(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(supplierRepository.findAll(pagination).map(DataListSupplier::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseSupplier> getSupplier(@PathVariable Long id){
        Supplier supplier = supplierRepository.getReferenceById(id);
        DataResponseSupplier dataResponseSupplier = new DataResponseSupplier(supplier);
        return ResponseEntity.ok(dataResponseSupplier);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseSupplier> updateSupplier(@RequestBody @Valid DataUpdateSupplier dataUpdateSupplier){
        Supplier supplier = supplierRepository.getReferenceById(dataUpdateSupplier.id());
        System.out.println(supplier.getName());
        supplier.updateData(dataUpdateSupplier);
        return ResponseEntity.ok(new DataResponseSupplier(supplier.getId(), supplier.getName(), supplier.getEmail(),
                supplier.getPhone(), new DataAddress(supplier.getAddress().getStreet(), supplier.getAddress().getRace(), supplier.getAddress().getNumber(),
                supplier.getAddress().getDepartment(), supplier.getAddress().getCity(), supplier.getAddress().getComplement(), supplier.getAddress().getCountry(),
                supplier.getAddress().getAddressComplete())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseSupplier> deleteSupplier(@PathVariable Long id){
        supplierRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
