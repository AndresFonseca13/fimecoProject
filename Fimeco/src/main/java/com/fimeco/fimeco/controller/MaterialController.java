package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.materials.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;
    @PostMapping
    public ResponseEntity<DataResponseMaterial> registerMaterial(@RequestBody @Valid DataRegisterMaterial dataRegisterMaterial) {
        Material material = materialRepository.save(new Material(dataRegisterMaterial));
        DataResponseMaterial dataResponseMaterial = new DataResponseMaterial(material);
        return ResponseEntity.ok(dataResponseMaterial);
    }

    @GetMapping
    public ResponseEntity<Page<DataResponseMaterial>> listMaterials(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(materialRepository.findAll(pagination).map(DataResponseMaterial::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataListMaterial> getMaterial(@PathVariable Long id){
        Material material = materialRepository.getReferenceById(id);
        DataListMaterial dataListMaterial = new DataListMaterial(material);
        return ResponseEntity.ok(dataListMaterial);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseMaterial> updateMaterial(@RequestBody DataUpdateMaterial dataUpdateMaterial){
        Material material = materialRepository.getReferenceById(dataUpdateMaterial.id());
        material.updateData(dataUpdateMaterial);
        return ResponseEntity.ok(new DataResponseMaterial(material));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseMaterial> deleteMaterial(@PathVariable Long id){
        Material material = materialRepository.getReferenceById(id);
        materialRepository.delete(material);
        return ResponseEntity.noContent().build();
    }
}
