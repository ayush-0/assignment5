package com.nagarro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.daoImp.EmployeeDaoImp;
import com.nagarro.dto.Employee;

@Service("empervice")
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeDaoImp employeeDao; 
	
	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return this.employeeDao.getEmployees();
	}

	public Employee getEmployee(int empCode) {
		return this.employeeDao.getEmployee(empCode);
	}

	public void uploadEmployee(Employee emp) {
		this.employeeDao.addEmployee(emp);
	}

	public void updateEmployee(Employee emp) {
		
		this.employeeDao.updateEmployee(emp);
	}

	public void deleteEmployee(int empCode) {
		
		Employee emp = this.employeeDao.getEmployee(empCode);

		if (emp != null) {
			this.employeeDao.deleteEmployee(emp);
		} else {
			return;
		}

	}

}
