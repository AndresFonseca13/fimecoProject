package com.fimeco.fimeco.domain.materials;

import com.fimeco.fimeco.domain.product.Product;
import com.fimeco.fimeco.domain.supplier.Supplier;
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
    @Column(name = "name")
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
    private State state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "material_supplier",
            joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private List<Supplier> proveedores;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "material_product",
            joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;

    public Material(DataRegisterMaterial dataRegisterMaterial) {
        this.nombre = dataRegisterMaterial.nombre();
        this.descripcion = dataRegisterMaterial.descripcion();
        this.cantidad = dataRegisterMaterial.cantidad();
        this.unidadMedida = dataRegisterMaterial.unidadMedida();
        this.preciounitario = dataRegisterMaterial.precioUnitario();
        this.state = dataRegisterMaterial.state();
    }

    public void updateData(DataUpdateMaterial dataUpdateMaterial) {
        if (dataUpdateMaterial.cantidad() != null) {
            this.cantidad = dataUpdateMaterial.cantidad();
        }
        if (dataUpdateMaterial.precioUnitario() != null) {
            this.preciounitario = dataUpdateMaterial.precioUnitario();
        }
        if (dataUpdateMaterial.state() != null) {
            this.state = dataUpdateMaterial.state();
        }
    }
}
