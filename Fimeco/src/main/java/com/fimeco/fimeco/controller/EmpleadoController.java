package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.empleado.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaEmpleado> registrarEmpleado(@RequestBody @Valid DatosRegistroEmpleado datosRegistroEmpleado){
        Empleado empleado = empleadoRepository.save(new Empleado(datosRegistroEmpleado));
        DatosRespuestaEmpleado datosRespuestaEmpleado = new DatosRespuestaEmpleado(empleado.getId(), empleado.getNombre(),
                empleado.getApellido(), empleado.getTelefono(), empleado.getEmail(), empleado.getRol());
        return ResponseEntity.ok(datosRespuestaEmpleado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaEmpleado>> listadoEmpleados(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(empleadoRepository.findByActivoTrue(paginacion).map(DatosRespuestaEmpleado::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosEmpleado> obtenerEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoRepository.getReferenceById(id);
        DatosEmpleado datosEmpleado = new DatosEmpleado(empleado);
        return ResponseEntity.ok(datosEmpleado);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaEmpleado> actualizarEmpleado(@RequestBody DatosActualizarEmpleado datosActualizarEmpleado){
        Empleado empleado = empleadoRepository.getReferenceById(datosActualizarEmpleado.id());
        empleado.actualizarDatos(datosActualizarEmpleado);
        DatosRespuestaEmpleado datosRespuestaEmpleado = new DatosRespuestaEmpleado(empleado.getId(), empleado.getNombre(),
                empleado.getApellido(), empleado.getTelefono(), empleado.getEmail(), empleado.getRol());
        return ResponseEntity.ok(datosRespuestaEmpleado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoRepository.getReferenceById(id);
        empleado.desactivarEmpleado();
        return ResponseEntity.noContent().build();
    }

}
