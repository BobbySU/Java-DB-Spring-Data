package com.example.spring_data_auto_mapping_objects.repository;

import com.example.spring_data_auto_mapping_objects.entity.Employee;
import com.example.spring_data_auto_mapping_objects.entity.dto.EmployeeSpringDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<EmployeeSpringDTO> findByBirthdayBeforeOrderBySalaryDesc(LocalDate beforeYear);
}
