package com.fimeco.fimeco.domain.cliente;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.direccion.Direccion;
import com.fimeco.fimeco.domain.pedido.Pedido;
import com.fimeco.fimeco.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "telefono",unique = true)
    private String telefono;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "persona")
    private String nombrePersona;
    @Column(name = "telefono_persona")
    private String telefonoPersona;
    @Embedded
    private Direccion direccion;
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Pedido> pedidos;

    public Cliente(DatosRegistroCliente datosResDatosRegistroCliente, User user) {
        this.nombre = datosResDatosRegistroCliente.nombre();
        this.telefono = datosResDatosRegistroCliente.telefono();
        this.email = datosResDatosRegistroCliente.email();
        this.nombrePersona = datosResDatosRegistroCliente.nombrePersona();
        this.telefonoPersona = datosResDatosRegistroCliente.telefonoPersona();
        this.direccion = new Direccion(datosResDatosRegistroCliente.direccion());
    }

    public void actualizarDatos(DatosActualizarCliente datosActualizarCliente) {
        if (datosActualizarCliente.nombre() != null) {
            this.nombre = datosActualizarCliente.nombre();
        }
        if (datosActualizarCliente.telefono() != null) {
            this.telefono = datosActualizarCliente.telefono();
        }
        if (datosActualizarCliente.nombrePersona() != null) {
            this.nombrePersona = datosActualizarCliente.nombrePersona();
        }
        if (datosActualizarCliente.telefonoPersona() != null) {
            this.telefonoPersona = datosActualizarCliente.telefonoPersona();
        }
        if (datosActualizarCliente.direccion() != null) {
            this.direccion = new Direccion(datosActualizarCliente.direccion());
        }
    }
}
