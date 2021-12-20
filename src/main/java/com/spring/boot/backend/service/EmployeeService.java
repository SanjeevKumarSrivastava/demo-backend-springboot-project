package com.spring.boot.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.boot.backend.exception.ResourceNotFoundException;
import com.spring.boot.backend.model.Employee;
import com.spring.boot.backend.repository.EmployeeRepository;

/**
 * @author Sanjeev Srivastava
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmp() {
		List<Employee> emps = employeeRepository.findAll();
		if (emps != null) {
			return emps;
		} else {
			throw new ResourceNotFoundException("No Data are there");
		}
	}

	public Employee createEmp(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	public ResponseEntity<Employee> getsEmployeeById(Long id) {
		Optional<Employee> employee = Optional.of(employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id: " + id)));
		return ResponseEntity.ok(employee.get());
	}

	public ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetail) {
		Optional<Employee> employee = Optional.of(employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id: " + id)));
		Employee updatedEmployee = employee.get();
		updatedEmployee.setId(id);
		updatedEmployee.setFirstName(employeeDetail.getFirstName());
		updatedEmployee.setLastName(employeeDetail.getLastName());
		updatedEmployee.setEmailId(employeeDetail.getEmailId());
		employeeRepository.save(updatedEmployee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
		Optional<Employee> employee = Optional.of(employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id: " + id)));
		employeeRepository.delete(employee.get());
		Map<String, Boolean> reponse = new HashMap<>();
		reponse.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(reponse);
	}
	
	public List<Employee> employeeSearchs(String firstName){
		List<Employee> employees=employeeRepository.findByFirstName(firstName);
		return employees;
	}
}
