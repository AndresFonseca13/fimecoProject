package com.fimeco.fimeco.domain.pedido;

import com.fimeco.fimeco.domain.cliente.Cliente;
import com.fimeco.fimeco.domain.producto.Producto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "forma_pago")
    private FormaPago formaPago;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Producto> productos;
}
