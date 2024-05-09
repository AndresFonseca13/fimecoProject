package com.fimeco.fimeco.domain.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.product.Product;
import com.fimeco.fimeco.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Employee")
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document", unique = true)
    private String document;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone")
    private String phone;

    @Column(name = "emergency_phone")
    private String emergencyPhone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "date_entry")
    private LocalDate dateEntry;

    @Column(name = "service_time")
    private Integer serviceTime;

    @Column(name = "active")
    private boolean active = true;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinTable(name = "products_employees",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products = new HashSet<>();


    public Employee(DataRegisterEmployee dataRegisterEmployee, UserEntity userEntity) {
        this.document = dataRegisterEmployee.document();
        this.name = dataRegisterEmployee.name();
        this.lastName = dataRegisterEmployee.lastName();
        this.birthday = dataRegisterEmployee.birthdate();
        this.age = Period.between(this.birthday, LocalDate.now()).getYears();
        this.phone = dataRegisterEmployee.phone();
        this.emergencyPhone = dataRegisterEmployee.emergencyPhone();
        this.email = dataRegisterEmployee.email();
        this.position = dataRegisterEmployee.position();
        this.dateEntry = dataRegisterEmployee.dateEntry();
        this.serviceTime = Period.between(this.dateEntry, LocalDate.now()).getMonths();
        this.userEntity = userEntity;
    }

    public void actualizarDatos(DataUpdateEmployee dataUpdateEmployee) {
        if (dataUpdateEmployee.nombre() != null) {
            this.name = dataUpdateEmployee.nombre();
        }
        if (dataUpdateEmployee.apellido() != null) {
            this.lastName = dataUpdateEmployee.apellido();
        }
        if (dataUpdateEmployee.telefono() != null) {
            this.phone = dataUpdateEmployee.telefono();
        }
        if (dataUpdateEmployee.telefonoEmergencia() != null) {
            this.emergencyPhone = dataUpdateEmployee.telefonoEmergencia();
        }
        if (dataUpdateEmployee.email() != null) {
            this.email = dataUpdateEmployee.email();
        }
        if (dataUpdateEmployee.position() != null) {
            this.position = dataUpdateEmployee.position();
        }
    }
    public void deactivateEmployee() {
        this.active = false;
    }

}
