package com.fimeco.fimeco.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.employee.Employee;
import com.fimeco.fimeco.domain.materials.Material;
import com.fimeco.fimeco.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Product")
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @Enumerated(EnumType.STRING)
    @Column(name = "unit_measurement")
    private UnidadMedida unitMeasurement;
    @Column(name = "time_construction")
    private String timeConstruction;
    @Column(name = "tank_type")
    private String tankType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonBackReference
    private Order order;

    @ManyToMany(mappedBy = "products",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Material> materials;



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "products_employees",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Set<Employee> employees = new HashSet<>();

    public Product(DataRegisterProduct dataRegisterProduct, Order order) {
        this.name = dataRegisterProduct.name();
        this.description = dataRegisterProduct.description();
        this.price = dataRegisterProduct.price();
        this.state = dataRegisterProduct.state();
        this.unitMeasurement = dataRegisterProduct.unitMeasurement();
        this.timeConstruction = dataRegisterProduct.timeConstruction();
        this.tankType = dataRegisterProduct.tankType();
        this.order = order;
    }

    public void dataUpdate(DataUpdateProduct dataUpdateProduct) {
        if (dataUpdateProduct.name() != null) {
            this.name = dataUpdateProduct.name();
        }
        if (dataUpdateProduct.description() != null) {
            this.description = dataUpdateProduct.description();
        }
        if (dataUpdateProduct.price() != null) {
            this.price = dataUpdateProduct.price();
        }
        if (dataUpdateProduct.state() != null) {
            this.state = dataUpdateProduct.state();
        }
        if (dataUpdateProduct.timeConstruction() != null) {
            this.timeConstruction = dataUpdateProduct.timeConstruction();
        }
        if (dataUpdateProduct.tankType() != null) {
            this.tankType = dataUpdateProduct.tankType();
        }
    }
}
