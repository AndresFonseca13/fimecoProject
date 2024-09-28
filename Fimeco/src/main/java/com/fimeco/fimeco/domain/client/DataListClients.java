package com.fimeco.fimeco.domain.client;

import com.fimeco.fimeco.domain.address.DataAddress;

import java.util.UUID;

public record DataListClients(UUID id, String name, String phone, String email, String namePerson, DataAddress address) {

    public DataListClients(Client client) {
        this(client.getId(), client.getName(), client.getPhone(), client.getEmail(), client.getNamePerson(),
                new DataAddress(client.getAddress().getStreet(), client.getAddress().getRace(), client.getAddress().getNumber(),
                        client.getAddress().getDepartment(), client.getAddress().getCity(), client.getAddress().getComplement(), client.getAddress().getCountry(),
                        client.getAddress().getAddressComplete()));
    }
}
