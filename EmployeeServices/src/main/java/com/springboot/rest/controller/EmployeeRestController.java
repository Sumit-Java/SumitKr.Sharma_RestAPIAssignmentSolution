package com.springboot.rest.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.springboot.rest.entity.Employee;
import com.springboot.rest.entity.Role;
import com.springboot.rest.entity.User;
import com.springboot.rest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	//Auto-injecting Bean for EmployeeService Class
	@Autowired
	private EmployeeService employeeService;

	//Mapping url to Save new User in database	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) 
	{
		return employeeService.saveUser(user);
	}
	//Mapping url to Save new Role in database
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) 
	{
		return employeeService.saveRole(role);
	}

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	// Add mapping for GET /employees using Employee Id
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee (@PathVariable int employeeId) 
	{
		Employee theEmployee = employeeService. findById(employeeId);
		if (theEmployee == null) 
		{
			throw new RuntimeException("Employee id not found-"	+ employeeId);
		}
	return theEmployee;
	}
	
	// Add mapping for POST /employee to add a new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) 
	{
	//also just in case user pass an id in JSON ... setting id to
	// this is to force a save of new item ... instead of update
	
	theEmployee.setId(0);
	employeeService.save(theEmployee);
	return theEmployee;
	}
	
	//Add mapping for PUT /employees to update existing employee record
	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee theEmployee) {
		Employee tempEmployee = employeeService.findById(employeeId);
		employeeService.save(theEmployee);
	return tempEmployee;
	}
	
	// Add mapping for DELETE /employees using Employee id to delete employee record
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee (@PathVariable int employeeId) 
	{
	Employee tempEmployee = employeeService.findById(employeeId);
	// throw exception if null
	if (tempEmployee == null) 
	{
	throw new RuntimeException("Employee id not found -"+ employeeId);
	}
	employeeService.deleteById(employeeId);
	return "Deleted employee id -"+ employeeId;
	}
	
	//Employee Search API using FIrstName.
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName (@PathVariable String firstName)
	{
	return employeeService.searchByFirstName(firstName);
	}
	
	//Sorting the Result, API using Order
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam(name = "order") String order) {
	return employeeService.sortByFirstName(order);
	}
	

}
