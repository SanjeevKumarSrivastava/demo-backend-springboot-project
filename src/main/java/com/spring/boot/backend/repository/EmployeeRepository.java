package com.spring.boot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findByFirstName(String firstName);
}
