package com.fimeco.fimeco.domain.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByActiveTrue(Pageable paginacion);

    Page<Employee> findByOrderByAgeAsc(Pageable paginacion);

    Page<Employee> findByOrderByNameAsc(Pageable paginacion);

}
