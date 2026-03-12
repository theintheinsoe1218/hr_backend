package com.hr.repository;

import com.hr.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    // Find department by exact name (Returns Optional for better null handling)
    Optional<Department> findByName(String name);

    // Check if a department exists by name (Useful for validation)
    boolean existsByName(String name);

    // Filter departments by name (Fuzzy search, case-insensitive)
    List<Department> findByNameContainingIgnoreCase(String name);
}
