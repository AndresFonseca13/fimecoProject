package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;

public record DatosRespuestaCliente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String nombrePersona,
        DatosDireccion direccion
) {
    public DatosRespuestaCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNombre(), cliente.getTelefono(), cliente.getEmail(), cliente.getNombrePersona(),
                new DatosDireccion(cliente.getDireccion().getCalle(), cliente.getDireccion().getCarrera(), cliente.getDireccion().getNumero(),
                        cliente.getDireccion().getDepartamento(), cliente.getDireccion().getCiudad(), cliente.getDireccion().getComplemento(), cliente.getDireccion().getPais(),
                        cliente.getDireccion().getDireccionCompleta()));
    }
}
