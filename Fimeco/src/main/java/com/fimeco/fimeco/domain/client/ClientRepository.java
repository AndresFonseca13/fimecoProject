package com.fimeco.fimeco.domain.client;

import com.fimeco.fimeco.domain.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM clients c WHERE c.address.country = :country")
    List<Client> getAllByCountry(Country country);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
