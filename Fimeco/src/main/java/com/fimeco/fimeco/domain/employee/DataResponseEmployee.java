package com.fimeco.fimeco.domain.employee;

import java.util.UUID;

public record DataResponseEmployee(UUID id, String nombre, String apellido, String telefono, String email, Position position) {

        public DataResponseEmployee(Employee employee) {
            this(employee.getId(), employee.getName(), employee.getLastName(), employee.getPhone(), employee.getEmail(), employee.getPosition());
        }
}
