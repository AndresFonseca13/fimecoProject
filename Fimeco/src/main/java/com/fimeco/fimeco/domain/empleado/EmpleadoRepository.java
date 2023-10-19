package com.fimeco.fimeco.domain.empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Page<Empleado> findByActivoTrue(Pageable paginacion);

    Page<Empleado> findByOrderByEdadAsc(Pageable paginacion);

    Page<Empleado> findByOrderByNombreAsc(Pageable paginacion);

}
