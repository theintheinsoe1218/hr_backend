package com.hr.config;

import com.hr.model.Role;
import com.hr.model.User;
import com.hr.model.Employee;
import com.hr.model.Department;
import com.hr.repository.RoleRepository;
import com.hr.repository.DepartmentRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            // Seed Roles
            Role adminRole = roleRepository.save(new Role(null, "ADMIN"));
            Role hrRole = roleRepository.save(new Role(null, "HR"));
            Role staffRole = roleRepository.save(new Role(null, "STAFF"));

            // Seed Departments
            Department engineering = departmentRepository.save(new Department(null, "Engineering"));
            Department hrDept = departmentRepository.save(new Department(null, "Human Resources"));

            // Seed Employees
            Employee emp1 = new Employee();
            emp1.setId("EMP001");
            emp1.setFirstName("Thein");
            emp1.setLastName("Thein");
            emp1.setEmail("thein@aura.com");
            emp1.setDepartment(engineering);
            emp1.setPosition("Junior Web Developer");
            emp1.setEmploymentType("Full-time");
            emp1.setStatus("Active");
            emp1.setHireDate(LocalDate.of(2026, 2, 5));
            emp1.setSalary(1200.0);
            emp1.setLeaveBalance(18);
            employeeRepository.save(emp1);

            Employee emp2 = new Employee();
            emp2.setId("EMP003");
            emp2.setFirstName("Michael");
            emp2.setLastName("Chen");
            emp2.setEmail("michael@aura.com");
            emp2.setDepartment(hrDept);
            emp2.setPosition("HR Manager");
            emp2.setEmploymentType("Full-time");
            emp2.setStatus("Active");
            emp2.setHireDate(LocalDate.of(2021, 8, 1));
            emp2.setSalary(3500.0);
            emp2.setLeaveBalance(10);
            employeeRepository.save(emp2);

            // Seed Users with encoded passwords and Roles
            userRepository.save(new User(null, "admin", passwordEncoder.encode("admin123"), adminRole, emp2));
            userRepository.save(new User(null, "thein", passwordEncoder.encode("password"), staffRole, emp1));
        }
    }
}
