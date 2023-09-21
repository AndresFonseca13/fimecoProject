package com.fimeco.fimeco.domain.proveedor;

import com.fimeco.fimeco.domain.direccion.DatosDireccion;

public record DatosRespuestaProveedor(Long id,
                                      String nombre,
                                      String telefono,
                                      String email,
                                      DatosDireccion direccion) {
    public DatosRespuestaProveedor(Proveedor proveedor) {
        this(proveedor.getId(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail(),
                new DatosDireccion(proveedor.getDireccion().getCalle(), proveedor.getDireccion().getCarrera(), proveedor.getDireccion().getNumero(),
                        proveedor.getDireccion().getDepartamento(), proveedor.getDireccion().getCiudad(), proveedor.getDireccion().getComplemento(), proveedor.getDireccion().getPais(),
                        proveedor.getDireccion().getDireccionCompleta()));
    }
}
