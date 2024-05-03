package com.fimeco.fimeco.domain.address;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String race;
    private String department;
    private String city;
    private String number;
    private String complement;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String addressComplete;



    public Address(DataAddress address) {
        this.street = address.street();
        this.race = address.race();
        this.department = address.department();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        this.country = address.country();
        this.addressComplete = address.addressComplete();



    }

    public Address updateData(DataAddress address) {
        this.street = address.street();
        this.race = address.race();
        this.department = address.department();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        this.country = address.country();
        this.addressComplete = address.addressComplete();
        return this;
    }


}
