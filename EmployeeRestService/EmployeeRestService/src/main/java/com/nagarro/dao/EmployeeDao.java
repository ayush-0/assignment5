package com.nagarro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.dto.Employee;

public interface EmployeeDao {

	public List<Employee> getEmployees();
	
	public Employee getEmployee(int empCode);

	public void addEmployee(Employee emp);

	public void deleteEmployee(Employee emp);

	public void updateEmployee(Employee emp);
}
