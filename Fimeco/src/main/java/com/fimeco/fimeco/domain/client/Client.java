package com.fimeco.fimeco.domain.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.address.Address;
import com.fimeco.fimeco.domain.order.Order;
import com.fimeco.fimeco.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "clients")
@Entity(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone",unique = true)
    private String phone;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "person_name")
    private String namePerson;
    @Column(name = "person_phone")
    private String phonePerson;
    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Order> orders;

    public Client(DataRegisterClient dataRegisterClient, User user) {
        this.name = dataRegisterClient.name();
        this.phone = dataRegisterClient.phone();
        this.email = dataRegisterClient.email();
        this.namePerson = dataRegisterClient.namePerson();
        this.phonePerson = dataRegisterClient.phonePerson();
        this.address = new Address(dataRegisterClient.address());
        this.user = user;
    }

    public void dataUpdate(DataUpdateClient dataUpdateClient) {
        if (dataUpdateClient.name() != null) {
            this.name = dataUpdateClient.name();
        }
        if (dataUpdateClient.phone() != null) {
            this.phone = dataUpdateClient.phone();
        }
        if (dataUpdateClient.namePerson() != null) {
            this.namePerson = dataUpdateClient.namePerson();
        }
        if (dataUpdateClient.phonePerson() != null) {
            this.phonePerson = dataUpdateClient.phonePerson();
        }
        if (dataUpdateClient.address() != null) {
            this.address = new Address(dataUpdateClient.address());
        }
    }
}
