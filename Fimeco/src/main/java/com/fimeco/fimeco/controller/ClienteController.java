package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.cliente.Cliente;
import com.fimeco.fimeco.domain.cliente.ClienteRepository;
import com.fimeco.fimeco.domain.cliente.DatosRegistroCliente;
import com.fimeco.fimeco.domain.cliente.DatosRespuestaCliente;
import com.fimeco.fimeco.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
