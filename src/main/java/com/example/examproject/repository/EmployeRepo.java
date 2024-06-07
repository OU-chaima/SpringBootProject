package com.example.examproject.repository;

import com.example.examproject.model.Employee;
import com.example.examproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepo extends JpaRepository<Employee, Long> {
}
