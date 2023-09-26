package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.materiales.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materiales")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaMaterial> registrarMaterial(@RequestBody @Valid DatosRegistroMaterial datosRegistroMaterial) {
        Material material = materialRepository.save(new Material(datosRegistroMaterial));
        DatosRespuestaMaterial datosRespuestaMaterial = new DatosRespuestaMaterial(material);
        return ResponseEntity.ok(datosRespuestaMaterial);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaMaterial>> listarMateriales(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(materialRepository.findAll(paginacion).map(DatosRespuestaMaterial::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListarMateriales> obtenerMaterial(@PathVariable Long id){
        Material material = materialRepository.getReferenceById(id);
        DatosListarMateriales datosListarMateriales = new DatosListarMateriales(material);
        return ResponseEntity.ok(datosListarMateriales);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMaterial> actualizarMaterial(@RequestBody DatosActualizarMaterial datosActualizarMaterial){
        Material material = materialRepository.getReferenceById(datosActualizarMaterial.id());
        material.actualizarDatos(datosActualizarMaterial);
        return ResponseEntity.ok(new DatosRespuestaMaterial(material));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaMaterial> eliminarMaterial(@PathVariable Long id){
        Material material = materialRepository.getReferenceById(id);
        materialRepository.delete(material);
        return ResponseEntity.noContent().build();
    }
}
