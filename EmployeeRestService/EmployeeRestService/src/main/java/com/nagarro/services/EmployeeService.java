package com.nagarro.services;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.dto.Employee;

@Repository("empservice")
@EnableTransactionManagement
public interface EmployeeService {

	public List<Employee> getEmployees();
	
	public Employee getEmployee(int empCode);

	public void uploadEmployee(Employee emp);

	public void deleteEmployee(int empCode);

	public void updateEmployee(Employee emp);
}
