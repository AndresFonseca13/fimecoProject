package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.Direccion;
import com.fimeco.fimeco.domain.pedido.Pedido;
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
    @Column(name = "usuario",unique = true)
    private String usuario;
    @Column(name = "clave")
    private String clave;
    @Column(name = "persona")
    private String nombrePersona;
    @Column(name = "telefono_persona")
    private String telefonoPersona;
    @Embedded
    private Direccion direccion;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Pedido> pedidos;

    public Cliente(DatosRegistroCliente datosResDatosRegistroCliente) {
        this.nombre = datosResDatosRegistroCliente.nombre();
        this.telefono = datosResDatosRegistroCliente.telefono();
        this.email = datosResDatosRegistroCliente.email();
        this.usuario = datosResDatosRegistroCliente.usuario();
        this.clave = datosResDatosRegistroCliente.clave();
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
