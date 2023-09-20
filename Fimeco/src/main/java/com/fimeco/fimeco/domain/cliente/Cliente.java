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
}
