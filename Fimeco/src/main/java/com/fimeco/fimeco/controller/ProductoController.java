package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.producto.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaProducto> crearProducto(@RequestBody DatosRegistroProducto datosRegistroProducto){
        return productoService.crearProducto(datosRegistroProducto);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaProducto>> listarProductos(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(productoRepository.findAll(paginacion).map(DatosRespuestaProducto::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaProducto> actualizarProducto(@RequestBody DatosActualizarProducto datosActualizarProducto){
        Producto producto = productoRepository.getReferenceById(datosActualizarProducto.id());
        producto.actualizarDatos(datosActualizarProducto);
        return ResponseEntity.ok(new DatosRespuestaProducto(producto.getId(), producto.getNombre(), producto.getDescripcion(),
                producto.getPrecio(), producto.getEstado(), producto.getUnidadMedida(), producto.getTiempoConstruccion(),producto.getTipoTanque(), producto.getPedido().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaProducto> eliminarProducto(@PathVariable Long id){
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
