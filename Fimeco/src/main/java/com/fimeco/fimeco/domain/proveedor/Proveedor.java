package com.fimeco.fimeco.domain.proveedor;

import com.fimeco.fimeco.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "proveedores")
@Entity(name = "Proveedor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(unique = true, name = "telefono")
    private String telefono;
    @Column(unique = true, name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Embedded
    private Direccion direccion;
}
