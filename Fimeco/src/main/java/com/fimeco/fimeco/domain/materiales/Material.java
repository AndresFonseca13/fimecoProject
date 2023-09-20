package com.fimeco.fimeco.domain.materiales;

import com.fimeco.fimeco.domain.producto.Producto;
import com.fimeco.fimeco.domain.proveedor.Proveedor;
import jakarta.persistence.*;
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
    @Column(name = "codigo")
    private Integer cantidad;
    @Column(name = "unidad_medida")
    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;
    @Column(name = "precio_unitario")
    private Double preciounitario;
    @Column(name = "estado")
    private Estado estado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "material_proveedor",
            joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id", referencedColumnName = "id"))
    private List<Proveedor> proveedores;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "material_producto",
            joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"))
    private List<Producto> productos;

}
