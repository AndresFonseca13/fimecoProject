package com.fimeco.fimeco.domain.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.estado = :estado")
    List<Pedido> getAllByEstado(Estado estado);
}
