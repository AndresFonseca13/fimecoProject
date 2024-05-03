package com.fimeco.fimeco.domain.employee;

import java.time.LocalDate;

public record DataEmployee(Long id,
                           String name,
                           String lastName,
                           String document,
                           String phone,
                           String emergencyPhone,
                           String email,
                           LocalDate birthdate,
                           Integer age,
                           Position rol,
                           LocalDate dateEntry,
                           Integer timeService
) {
    public DataEmployee(Employee employee){
        this(employee.getId(), employee.getName(), employee.getLastName(), employee.getDocument(), employee.getPhone(), employee.getEmergencyPhone(),
                employee.getEmail(), employee.getBirthday(), employee.getAge(), employee.getPosition(), employee.getDateEntry(), employee.getServiceTime());
    }
}
