package com.hr.repository;

import com.hr.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByPeriod(String period);
    List<Payroll> findByEmployeeId(String employeeId);
}
