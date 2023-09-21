package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import com.fimeco.fimeco.domain.proveedor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaProveedor> registrarProveedor(@RequestBody @Valid DatosRegistroProveedor datosRegistroProveedor){
        Proveedor proveedor = proveedorRepository.save(new Proveedor(datosRegistroProveedor));
        DatosRespuestaProveedor datosRespuestaProveedor = new DatosRespuestaProveedor(proveedor.getId(), proveedor.getNombre(), proveedor.getEmail(),
                proveedor.getTelefono(), new DatosDireccion(proveedor.getDireccion().getCalle(), proveedor.getDireccion().getCarrera(), proveedor.getDireccion().getNumero(),
                proveedor.getDireccion().getDepartamento(), proveedor.getDireccion().getCiudad(), proveedor.getDireccion().getComplemento(), proveedor.getDireccion().getPais(),
                proveedor.getDireccion().getDireccionCompleta()));
        return ResponseEntity.ok(datosRespuestaProveedor);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoProveedor>> listadoProveedores(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(proveedorRepository.findAll(paginacion).map(DatosListadoProveedor::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaProveedor> obtenerProveedor(@PathVariable Long id){
        Proveedor proveedor = proveedorRepository.getReferenceById(id);
        DatosRespuestaProveedor datosRespuestaProveedor = new DatosRespuestaProveedor(proveedor);
        return ResponseEntity.ok(datosRespuestaProveedor);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaProveedor> actualizarProveedor(@RequestBody @Valid DatosActualizarProveedor datosActualizarProveedor){
        Proveedor proveedor = proveedorRepository.getReferenceById(datosActualizarProveedor.id());
        System.out.println(proveedor.getNombre());
        proveedor.actualizarDatos(datosActualizarProveedor);
        return ResponseEntity.ok(new DatosRespuestaProveedor(proveedor.getId(), proveedor.getNombre(), proveedor.getEmail(),
                proveedor.getTelefono(), new DatosDireccion(proveedor.getDireccion().getCalle(), proveedor.getDireccion().getCarrera(), proveedor.getDireccion().getNumero(),
                proveedor.getDireccion().getDepartamento(), proveedor.getDireccion().getCiudad(), proveedor.getDireccion().getComplemento(), proveedor.getDireccion().getPais(),
                proveedor.getDireccion().getDireccionCompleta())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaProveedor> eliminarProveedor(@PathVariable Long id){
        proveedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
