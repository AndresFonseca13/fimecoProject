package com.fimeco.fimeco.domain.client;

import com.fimeco.fimeco.domain.address.DataAddress;

public record DataResponseClient(
        Long id,
        String name,
        String email,
        String phone,
        String namePerson,
        DataAddress address
) {
    public DataResponseClient(Client client) {
        this(client.getId(), client.getName(), client.getPhone(), client.getEmail(), client.getNamePerson(),
                new DataAddress(client.getAddress().getStreet(), client.getAddress().getRace(), client.getAddress().getNumber(),
                        client.getAddress().getDepartment(), client.getAddress().getCity(), client.getAddress().getComplement(), client.getAddress().getCountry(),
                        client.getAddress().getAddressComplete()));
    }
}
