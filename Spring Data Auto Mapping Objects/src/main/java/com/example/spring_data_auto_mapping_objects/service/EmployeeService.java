package com.example.spring_data_auto_mapping_objects.service;

import com.example.spring_data_auto_mapping_objects.entity.Employee;
import com.example.spring_data_auto_mapping_objects.entity.dto.EmployeeSpringDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);

    void save(Employee employee);

    List<EmployeeSpringDTO> findEmployeesBornBefore(int year);

    List<Employee> findAll();
}
