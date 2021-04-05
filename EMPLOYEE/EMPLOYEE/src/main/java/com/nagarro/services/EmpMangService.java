package com.nagarro.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.Constants.URLType;
import com.nagarro.dto.Employee;

@Component
public class EmpMangService {
	
	final static Logger LOG = Logger.getLogger(EmpMangService.class);
	
	
	public List<Employee> getEmployees() {
		LOG.info("Requesting EmployeeRestService for list of employees.");

		String url = URLType.GET_EMPS_URL;
		
		//ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Employee>> response = restTemplate.exchange(
				url , HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Employee>>() {
				});
		
		List<Employee> employees = response.getBody();

		LOG.info("Request successful. Recieved list of employees.");
		return employees;
	}

	public Employee getEmployee(int empCode) {
		LOG.info("Requesting EmployeeRestService for employee : " + empCode);
		
		String url = URLType.GET_EMP_URL + empCode;
		
		RestTemplate restTemplate = new RestTemplate();
		
		LOG.info("Request successful. Recieved employee : " + empCode);
		return restTemplate.getForObject(url, Employee.class);
	}

	public void addEmployee(Employee emp) {
		LOG.info("Requesting EmployeeRestService to add new employee : " + emp.getEmpCode());
		
		String url = URLType.UPLD_EMP_URL;

		RestTemplate restTemplate = new RestTemplate();
		 
		HttpEntity<Employee> request = new HttpEntity<Employee>(emp);
		restTemplate.postForObject(url, request, Employee.class);
		
		LOG.info("Request successful. Added employee : " + emp.getEmpCode());
	}

	public void updateEmployee(Employee emp) {
		LOG.info("Requesting EmployeeRestService to update employee : " + emp.getEmpCode());
		
		String url = URLType.UPDT_EMP_URL ;

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(emp);
		
		restTemplate.put(url, request);
		LOG.info("Request successful. Updated employee : " + emp.getEmpCode());
	}
	
	public void deleteEmployee(int empCode) {
		LOG.info("Requesting EmployeeRestService to delete employee : "+ empCode);
		
		String url = URLType.DLT_EMP_URL + empCode;
		 
	    RestTemplate restTemplate = new RestTemplate();
	    
	    restTemplate.delete(url);
	    
	    LOG.info("Request successful. Deleted employee : " + empCode);
	}

}
