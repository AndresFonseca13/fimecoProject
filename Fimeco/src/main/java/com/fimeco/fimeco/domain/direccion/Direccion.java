package com.fimeco.fimeco.domain.direccion;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String carrera;
    private String departamento;
    private String ciudad;
    private String numero;
    private String complemento;
    @Enumerated(EnumType.STRING)
    private Pais pais;
    private String direccionCompleta;



    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.carrera = direccion.carrera();
        this.departamento = direccion.departamento();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.pais = direccion.pais();
        this.direccionCompleta = direccion.direccionCompleta();



    }

    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.carrera = direccion.carrera();
        this.departamento = direccion.departamento();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.pais = direccion.pais();
        this.direccionCompleta = direccion.direccionCompleta();
        return this;
    }


}
