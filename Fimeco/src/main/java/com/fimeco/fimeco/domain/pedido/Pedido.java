package com.fimeco.fimeco.domain.pedido;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fimeco.fimeco.domain.cliente.Cliente;
import com.fimeco.fimeco.domain.producto.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Producto> productos;


    public Pedido(DatosRegistroPedido datosRegistroPedido, Cliente cliente) {
        this.fechaPedido = LocalDate.now();
        this.fechaEntrega = datosRegistroPedido.fechaEntrega();
        this.estado = datosRegistroPedido.estado();
        this.descripcion = datosRegistroPedido.descripcion();
        this.cantidad = datosRegistroPedido.cantidad();
        this.precio = datosRegistroPedido.precio();
        this.formaPago = datosRegistroPedido.formaPago();
        this.cliente = cliente;
    }

    public void actualizarPedido(DatosActualizarPedido datosActualizarPedido){
        if(datosActualizarPedido.fechaEntrega() != null){
            this.fechaEntrega = datosActualizarPedido.fechaEntrega();
        }
        if(datosActualizarPedido.estado() != null){
            this.estado = datosActualizarPedido.estado();
        }
        if(datosActualizarPedido.descripcion() != null){
            this.descripcion = datosActualizarPedido.descripcion();
        }
        if(datosActualizarPedido.cantidad() != null){
            this.cantidad = datosActualizarPedido.cantidad();
        }
        if(datosActualizarPedido.precio() != null){
            this.precio = datosActualizarPedido.precio();
        }
        if(datosActualizarPedido.formaPago() != null){
            this.formaPago = datosActualizarPedido.formaPago();
        }
    }
}
