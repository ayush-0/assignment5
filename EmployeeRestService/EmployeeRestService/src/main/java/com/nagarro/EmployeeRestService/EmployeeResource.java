package com.nagarro.EmployeeRestService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nagarro.dto.Employee;
import com.nagarro.services.EmployeeService;

@Path("employees")
public class EmployeeResource {

	final static Logger LOG = Logger.getLogger(EmployeeResource.class);
	
	@Autowired(required=true)
	private EmployeeService empservice;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {

		LOG.info("Recieved request for employees. ");

		return empservice.getEmployees();
	}

	@POST
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	public void uploadEmployee(Employee emp) {
		LOG.info("Recieved request for employee upload : " + emp.getEmpName());

		empservice.uploadEmployee(emp);
	}

	@DELETE
	@Path("employee/{empCode}")
	public void deleteEmployee(@PathParam("empCode") int empCode) {
		LOG.info("Recieved request for delete employee : " + empCode);

		empservice.deleteEmployee(empCode);
	}

	@GET
	@Path("employee/{empCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("empCode") int empCode) {
		LOG.info("Recieved request for employee : " + empCode);

		return empservice.getEmployee(empCode);
	}
	
	@PUT
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmployee(Employee emp) {
		LOG.info("Recieved request for update employee : " + emp.getEmpCode());
		
		empservice.updateEmployee(emp);
	}
}
