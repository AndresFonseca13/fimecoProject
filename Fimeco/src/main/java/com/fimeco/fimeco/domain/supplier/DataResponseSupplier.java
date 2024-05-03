package com.fimeco.fimeco.domain.supplier;

import com.fimeco.fimeco.domain.address.DataAddress;

public record DataResponseSupplier(Long id,
                                   String name,
                                   String phone,
                                   String email,
                                   DataAddress address) {
    public DataResponseSupplier(Supplier supplier) {
        this(supplier.getId(), supplier.getName(), supplier.getPhone(), supplier.getEmail(),
                new DataAddress(supplier.getAddress().getStreet(), supplier.getAddress().getRace(), supplier.getAddress().getNumber(),
                        supplier.getAddress().getDepartment(), supplier.getAddress().getCity(), supplier.getAddress().getComplement(), supplier.getAddress().getCountry(),
                        supplier.getAddress().getAddressComplete()));
    }
}
