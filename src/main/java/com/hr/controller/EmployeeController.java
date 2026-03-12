package com.hr.controller;

import com.hr.model.Employee;
import com.hr.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Management", description = "APIs for managing employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping
    @Operation(summary = "Get all employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee details) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(details.getFirstName());
                    employee.setLastName(details.getLastName());
                    employee.setEmail(details.getEmail());
                    employee.setPhone(details.getPhone());
                    employee.setAddress(details.getAddress());
                    employee.setDepartment(details.getDepartment());
                    employee.setPosition(details.getPosition());
                    employee.setEmploymentType(details.getEmploymentType());
                    employee.setStatus(details.getStatus());
                    employee.setHireDate(details.getHireDate());
                    employee.setSalary(details.getSalary());
                    employee.setLeaveBalance(details.getLeaveBalance());
                    return ResponseEntity.ok(employeeRepository.save(employee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
