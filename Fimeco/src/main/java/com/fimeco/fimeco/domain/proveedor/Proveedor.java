package com.fimeco.fimeco.domain.proveedor;

import com.fimeco.fimeco.domain.direccion.Direccion;
import com.fimeco.fimeco.domain.materiales.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(mappedBy = "proveedores", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Material> materiales;

    public Proveedor(DatosRegistroProveedor datosRegistroProveedor) {
        this.nombre = datosRegistroProveedor.nombre();
        this.telefono = datosRegistroProveedor.telefono();
        this.email = datosRegistroProveedor.email();
        this.tipo = datosRegistroProveedor.tipo();
        this.direccion = new Direccion(datosRegistroProveedor.direccion());
    }

    public void actualizarDatos(DatosActualizarProveedor datosActualizarProveedor) {
        if (datosActualizarProveedor.nombre() != null) {
            this.nombre = datosActualizarProveedor.nombre();
        }
        if (datosActualizarProveedor.telefono() != null) {
            this.telefono = datosActualizarProveedor.telefono();
        }
        if (datosActualizarProveedor.email() != null) {
            this.email = datosActualizarProveedor.email();
        }
        if (datosActualizarProveedor.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarProveedor.direccion());
        }
    }
}
