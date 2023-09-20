package com.fimeco.fimeco.domain.producto;

import com.fimeco.fimeco.domain.cliente.Cliente;
import com.fimeco.fimeco.domain.empleado.Empleado;
import com.fimeco.fimeco.domain.materiales.Material;
import com.fimeco.fimeco.domain.pedido.Pedido;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Producto")
@Table(name = "productos")
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
    @Column(name = "unidad_medida")
    private UnidadMedida unidadMedida;
    @Column(name = "tiempo_construccion")
    private String tiempoConstruccion;
    @Column(name = "tipo_tanque")
    private String tipoTanque;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToMany(mappedBy = "productos",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Material> materiales;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "producto_empleado",
            joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id", referencedColumnName = "id"))
    private List<Empleado> empleados;
}
