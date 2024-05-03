package com.fimeco.fimeco.domain.supplier;

import com.fimeco.fimeco.domain.address.Address;
import com.fimeco.fimeco.domain.materials.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "suppliers")
@Entity(name = "Supplier")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(unique = true, name = "phone")
    private String phone;
    @Column(unique = true, name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Embedded
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "material_supplier",
            joinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"))
    private List<Material> materials;

    public Supplier(DataRegisterSupplier dataRegisterSupplier) {
        this.name = dataRegisterSupplier.name();
        this.phone = dataRegisterSupplier.phone();
        this.email = dataRegisterSupplier.email();
        this.type = dataRegisterSupplier.type();
        this.address = new Address(dataRegisterSupplier.address());
    }

    public void updateData(DataUpdateSupplier dataUpdateSupplier) {
        if (dataUpdateSupplier.name() != null) {
            this.name = dataUpdateSupplier.name();
        }
        if (dataUpdateSupplier.phone() != null) {
            this.phone = dataUpdateSupplier.phone();
        }
        if (dataUpdateSupplier.email() != null) {
            this.email = dataUpdateSupplier.email();
        }
        if (dataUpdateSupplier.address() != null) {
            this.address = address.updateData(dataUpdateSupplier.address());
        }
    }
}
