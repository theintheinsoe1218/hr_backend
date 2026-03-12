package com.hr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private String period; // e.g., "March 2026"
    private Double baseSalary;
    private Double overtime;
    private Double bonuses;
    private Double deductions;
    private Double netSalary;
    private String status; // Draft, Paid
    private String paymentMethod;
}
