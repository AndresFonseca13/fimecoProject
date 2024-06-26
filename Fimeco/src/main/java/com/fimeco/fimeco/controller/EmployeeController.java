package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.employee.*;
import com.fimeco.fimeco.domain.user.UserEntity;
import com.fimeco.fimeco.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    public EmployeeController(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseEmployee> registerEmployee(@RequestBody @Valid DataRegisterEmployee dataRegisterEmployee){
        UserEntity userEntity = userRepository.findByUsername(dataRegisterEmployee.username()).orElseThrow();
        Employee employee = employeeRepository.save(new Employee(dataRegisterEmployee, userEntity));
        DataResponseEmployee dataResponseEmployee = new DataResponseEmployee(employee);
        return ResponseEntity.ok(dataResponseEmployee);
    }

    @GetMapping
    public ResponseEntity<Page<DataResponseEmployee>> listEmployees(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(employeeRepository.findByActiveTrue(pagination).map(DataResponseEmployee::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataEmployee> getEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.getReferenceById(id);
        DataEmployee dataEmployee = new DataEmployee(employee);
        return ResponseEntity.ok(dataEmployee);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseEmployee> updateEmployee(@RequestBody DataUpdateEmployee dataUpdateEmployee){
        Employee employee = employeeRepository.getReferenceById(dataUpdateEmployee.id());
        employee.actualizarDatos(dataUpdateEmployee);
        DataResponseEmployee dataResponseEmployee = new DataResponseEmployee(employee);
        return ResponseEntity.ok(dataResponseEmployee);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.getReferenceById(id);
        employee.deactivateEmployee();
        return ResponseEntity.noContent().build();
    }

}
