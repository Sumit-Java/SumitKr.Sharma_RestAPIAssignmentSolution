package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.entity.Employee;
import com.springboot.rest.entity.Role;
import com.springboot.rest.entity.User;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int id);

	public void save(Employee employee);

	public void deleteById(int id);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstName(String order);

	public User saveUser(User user);

	public Role saveRole(Role role);

}
