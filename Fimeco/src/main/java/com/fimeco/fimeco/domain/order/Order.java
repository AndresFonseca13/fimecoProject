package com.fimeco.fimeco.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.client.Client;
import com.fimeco.fimeco.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "orders")
@Entity(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
    @Column(name = "pay_way")
    private PayWay payWay;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "clients_id", referencedColumnName = "id")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Product> products;


    public Order(DataRegisterOrder dataRegisterOrder, Client client) {
        this.orderDate = LocalDate.now();
        this.deliveryDate = dataRegisterOrder.fechaEntrega();
        this.state = dataRegisterOrder.state();
        this.description = dataRegisterOrder.descripcion();
        this.quantity = dataRegisterOrder.cantidad();
        this.price = dataRegisterOrder.precio();
        this.payWay = dataRegisterOrder.payWay();
        this.client = client;
    }

    public void updateOrder(DataUpdateOrder dataUpdateOrder){
        if(dataUpdateOrder.fechaEntrega() != null){
            this.deliveryDate = dataUpdateOrder.fechaEntrega();
        }
        if(dataUpdateOrder.state() != null){
            this.state = dataUpdateOrder.state();
        }
        if(dataUpdateOrder.descripcion() != null){
            this.description = dataUpdateOrder.descripcion();
        }
        if(dataUpdateOrder.cantidad() != null){
            this.quantity = dataUpdateOrder.cantidad();
        }
        if(dataUpdateOrder.precio() != null){
            this.price = dataUpdateOrder.precio();
        }
        if(dataUpdateOrder.payWay() != null){
            this.payWay = dataUpdateOrder.payWay();
        }
    }
}
