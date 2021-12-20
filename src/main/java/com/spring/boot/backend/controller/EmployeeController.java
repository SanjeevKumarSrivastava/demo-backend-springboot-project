package com.spring.boot.backend.controller;

import java.util.List;
import java.util.Map;

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
import com.spring.boot.backend.service.EmployeeService;

/**
 * @author Sanjeev Srivastava
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * This method can be accessed by user whose role is admin
	 * To retrieve the List of Employee we can use this API.
	 * @return Employee
	 */
	@GetMapping("/employees")
	//@RolesAllowed("admin")
	public List<Employee> getAllEmployees() {
		List<Employee> emps = employeeService.getAllEmp();
		if (emps != null) {
			return emps;
		} else {
			throw new ResourceNotFoundException("No Data are there");
		}
	}

	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmp(employee);
	}

	// get employee by id rest api
	//this method can be accessed by user whose role is user
	@GetMapping("/employees/{id}")
	//@RolesAllowed("user")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return employeeService.getsEmployeeById(id);
	}

	// update employee rest api
	@PutMapping("/employees/{id}")
	//@RolesAllowed("admin")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetail) {
		return employeeService.updateEmployee(id, employeeDetail);
	}

	// delete employee rest api
	@DeleteMapping("/employees/{firstName}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployee(id);
	}

	// search employee by employee name
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> employeeSearch(@PathVariable String firstName) {
		return employeeService.employeeSearchs(firstName);
	}
}
