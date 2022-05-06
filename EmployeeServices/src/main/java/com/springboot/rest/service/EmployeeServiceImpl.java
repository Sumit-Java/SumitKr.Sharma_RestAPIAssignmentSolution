package com.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.rest.entity.Employee;
import com.springboot.rest.entity.Role;
import com.springboot.rest.entity.User;
import com.springboot.rest.repository.EmployeeRepository;
import com.springboot.rest.repository.RoleRepository;
import com.springboot.rest.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	// Auto Injecting Bean for Employee Repository
	@Autowired
	private EmployeeRepository employeeRepository;
	// Auto Injecting Bean for Role Repository
	@Autowired
	RoleRepository roleRepository;
	// Auto Injecting Bean for User Repository
	@Autowired
	UserRepository userRepository;
	// Auto Injecting Bean to Encrypt the Password
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	// Function to find all Employee List
	public List<Employee> findAll() {
		return employeeRepository.findAll();

	}

	// Function to find an Employee record using Employee id
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		Employee theEmployee = null;

		// Checking if the Record Exists or not
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// Records couldn't be found
			throw new RuntimeException("Didn't find Employee Id" + id);
		}
		return theEmployee;
	}

	// Function to Save a new Employee Record
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	// Function to delete an Employee record using the Employee Id
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

	// Function to Search an Employee Using First Name
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);

	}

	// Function to Sort all Employee List
	public List<Employee> sortByFirstName(String order) {
		if (order.equals("desc")) {
			return employeeRepository.findAllByOrderByFirstNameDesc();
		} else {
			return employeeRepository.findAllByOrderByFirstNameAsc();
		}

	}

	//Function to Save a New User in Database 
	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);

	}
	//Function to Save a New Role in Database 
	public Role saveRole(Role role) {
		return roleRepository.save(role);

	}

}
