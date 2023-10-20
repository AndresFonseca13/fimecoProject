package com.fimeco.fimeco.domain.cliente;

import com.fimeco.fimeco.domain.direccion.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.direccion.pais = :pais")
    List<Cliente> getAllByPais(Pais pais);

    Boolean existsByEmail(String email);

    Boolean existsByTelefono(String telefono);
}
