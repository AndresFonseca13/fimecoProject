package com.fimeco.fimeco.domain.materiales;

import com.fimeco.fimeco.domain.producto.Producto;
import com.fimeco.fimeco.domain.proveedor.Proveedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Material")
@Table(name = "materiales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "unidad_medida")
    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;
    @Column(name = "precio_unitario")
    private Double preciounitario;
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "material_proveedor",
            joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id", referencedColumnName = "id"))
    private List<Proveedor> proveedores;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "material_producto",
            joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"))
    private List<Producto> productos;

    public Material(DatosRegistroMaterial datosRegistroMaterial) {
        this.nombre = datosRegistroMaterial.nombre();
        this.descripcion = datosRegistroMaterial.descripcion();
        this.cantidad = datosRegistroMaterial.cantidad();
        this.unidadMedida = datosRegistroMaterial.unidadMedida();
        this.preciounitario = datosRegistroMaterial.precioUnitario();
        this.estado = datosRegistroMaterial.estado();
    }

    public void actualizarDatos(DatosActualizarMaterial datosActualizarMaterial) {
        if (datosActualizarMaterial.cantidad() != null) {
            this.cantidad = datosActualizarMaterial.cantidad();
        }
        if (datosActualizarMaterial.precioUnitario() != null) {
            this.preciounitario = datosActualizarMaterial.precioUnitario();
        }
        if (datosActualizarMaterial.estado() != null) {
            this.estado = datosActualizarMaterial.estado();
        }
    }
}
