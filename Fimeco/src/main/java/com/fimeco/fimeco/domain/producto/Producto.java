package com.fimeco.fimeco.domain.producto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.cliente.Cliente;
import com.fimeco.fimeco.domain.empleado.Empleado;
import com.fimeco.fimeco.domain.materiales.Material;
import com.fimeco.fimeco.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Producto")
@Table(name = "productos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private Double precio;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida")
    private UnidadMedida unidadMedida;
    @Column(name = "tiempo_construccion")
    private String tiempoConstruccion;
    @Column(name = "tipo_tanque")
    private String tipoTanque;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    @JsonBackReference
    private Pedido pedido;

    @ManyToMany(mappedBy = "productos",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Material> materiales;



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "productos_empleados",
            joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id", referencedColumnName = "id"))
    private Set<Empleado> empleados  = new HashSet<>();

    public Producto(DatosRegistroProducto datosRegistroProducto, Pedido pedido) {
        this.nombre = datosRegistroProducto.nombre();
        this.descripcion = datosRegistroProducto.descripcion();
        this.precio = datosRegistroProducto.precio();
        this.estado = datosRegistroProducto.estado();
        this.unidadMedida = datosRegistroProducto.unidadMedida();
        this.tiempoConstruccion = datosRegistroProducto.tiempoConstruccion();
        this.tipoTanque = datosRegistroProducto.tipoTanque();
        this.pedido = pedido;
    }

    public void actualizarDatos(DatosActualizarProducto datosActualizarProducto) {
        if (datosActualizarProducto.nombre() != null) {
            this.nombre = datosActualizarProducto.nombre();
        }
        if (datosActualizarProducto.descripcion() != null) {
            this.descripcion = datosActualizarProducto.descripcion();
        }
        if (datosActualizarProducto.precio() != null) {
            this.precio = datosActualizarProducto.precio();
        }
        if (datosActualizarProducto.estado() != null) {
            this.estado = datosActualizarProducto.estado();
        }
        if (datosActualizarProducto.tiempoConstruccion() != null) {
            this.tiempoConstruccion = datosActualizarProducto.tiempoConstruccion();
        }
        if (datosActualizarProducto.tipoTanque() != null) {
            this.tipoTanque = datosActualizarProducto.tipoTanque();
        }
    }
}
