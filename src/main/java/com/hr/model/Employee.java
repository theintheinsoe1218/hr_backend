package com.hr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private String id; // Emp ID like EMP001

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String position;
    private String employmentType; // Full-time, Part-time, Contract, Intern
    private String status; // Active, On Leave, Terminated
    private LocalDate hireDate;
    private Double salary;
    private Integer leaveBalance;

    private String bankName;
    private String bankAccount;
    private String taxId;
    private String emergencyContact;
    private String emergencyPhone;
}
