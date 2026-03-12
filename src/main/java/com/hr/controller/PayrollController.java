package com.hr.controller;

import com.hr.model.Payroll;
import com.hr.repository.PayrollRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
@Tag(name = "Payroll Management", description = "APIs for managing employee payroll")
@CrossOrigin(origins = "*")
public class PayrollController {

    private final PayrollRepository payrollRepository;

    @GetMapping
    @Operation(summary = "Get payroll records for a specific period")
    public List<Payroll> getPayrollByPeriod(@RequestParam String period) {
        return payrollRepository.findByPeriod(period);
    }

    @PostMapping
    @Operation(summary = "Create a new payroll entry")
    public Payroll createPayroll(@RequestBody Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update payroll payment status")
    public ResponseEntity<Payroll> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return payrollRepository.findById(id)
                .map(payroll -> {
                    payroll.setStatus(status);
                    return ResponseEntity.ok(payrollRepository.save(payroll));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
