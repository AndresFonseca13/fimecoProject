package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.cliente.*;
import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import com.fimeco.fimeco.domain.direccion.Pais;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCliente> registrarCliente(@RequestBody @Valid DatosRegistroCliente datosResDatosRegistroCliente){
        Cliente cliente = clienteRepository.save(new Cliente(datosResDatosRegistroCliente));
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getEmail(),
                cliente.getTelefono(), cliente.getNombrePersona(),
                new DatosDireccion(cliente.getDireccion().getCalle(), cliente.getDireccion().getCarrera(), cliente.getDireccion().getNumero(),
                cliente.getDireccion().getDepartamento(), cliente.getDireccion().getCiudad(), cliente.getDireccion().getComplemento(), cliente.getDireccion().getPais(),
                cliente.getDireccion().getDireccionCompleta()));
        return ResponseEntity.ok(datosRespuestaCliente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoClientes>> listadoClientes(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(clienteRepository.findAll(paginacion).map(DatosListadoClientes::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCliente> obtenerCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente);
        return ResponseEntity.ok(datosRespuestaCliente);
    }

    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<Cliente>> obtenerClientePorPais(@PathVariable Pais pais){
        List<Cliente> clientes = clienteRepository.getAllByPais(pais);
        return ResponseEntity.ok(clientes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> actualizarCliente(@RequestBody @Valid DatosActualizarCliente datosActualizarCliente){
        Cliente cliente = clienteRepository.getReferenceById(datosActualizarCliente.id());
        cliente.actualizarDatos(datosActualizarCliente);
        return ResponseEntity.ok(new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getEmail(),
                cliente.getTelefono(), cliente.getNombrePersona(),
                new DatosDireccion(cliente.getDireccion().getCalle(), cliente.getDireccion().getCarrera(), cliente.getDireccion().getNumero(),
                        cliente.getDireccion().getDepartamento(), cliente.getDireccion().getCiudad(), cliente.getDireccion().getComplemento(), cliente.getDireccion().getPais(),
                        cliente.getDireccion().getDireccionCompleta())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> eliminarCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
