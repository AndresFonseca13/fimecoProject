package com.fimeco.fimeco.domain.supplier;

import com.fimeco.fimeco.domain.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdateSupplier(@NotNull
                                       Long id,
                                 String name,
                                 String phone,
                                 String email,
                                 DataAddress address) {

}

