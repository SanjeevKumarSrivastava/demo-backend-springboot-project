package com.spring.boot.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.backend.exception.ResourceNotFoundException;
import com.spring.boot.backend.model.Employee;
import com.spring.boot.backend.repository.EmployeeRepository;

/**
 * @author Sanjeev Srivastava
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * To retrieve the List of Employee we can use this API.
	 * @return Employee
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		List<Employee> emps = employeeRepository.findAll();
		if (emps != null) {
			return emps;
		} else {
			throw new ResourceNotFoundException("No Data are there");
		}
	}

	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee = Optional.of(employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id: " + id)));
		return ResponseEntity.ok(employee.get());
	}

	// update employee rest api

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetail) {
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

	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Optional<Employee> employee = Optional.of(employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Id: " + id)));

		employeeRepository.delete(employee.get());
		Map<String, Boolean> reponse = new HashMap<>();
		reponse.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(reponse);
	}
}
