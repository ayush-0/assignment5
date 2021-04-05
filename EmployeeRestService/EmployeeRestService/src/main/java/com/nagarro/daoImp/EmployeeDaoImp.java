package com.nagarro.daoImp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.dao.EmployeeDao;
import com.nagarro.dto.Employee;

@Repository
public class EmployeeDaoImp implements EmployeeDao{
	
	final static Logger LOG = Logger.getLogger(EmployeeDaoImp.class);
	
	//private Configuration con;
	
	@Autowired
	private SessionFactory sessionFactory;

	public EmployeeDaoImp() {
		
		
	}
     
    public EmployeeDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
//        LOG.info("Configuring hibernate and building session factory. ");
//		
//		con = new Configuration().configure();
//		sessionFactory = con.buildSessionFactory();
//		
//		LOG.info("Configuration and building Succesfull");
    }

	
	@Override
	@Transactional
	public List<Employee> getEmployees(){
		LOG.info("Retrieving employees from DB.");

		Session session = sessionFactory.openSession();
		
		CriteriaBuilder criteriaBuider = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuider.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		
		criteriaQuery.select(root);
		Query<Employee> query = session.createQuery(criteriaQuery);
		List<Employee> employees = query.getResultList();
		
		session.close();

		LOG.info("Successfully retrieved employees from DB.");
		return employees;
	}
	
	@Override
	public Employee getEmployee(int empCode) {
		LOG.info("Retrieving employee : " + empCode + " from DB.");

		Session session = sessionFactory.openSession();
		
		Employee emp = session.get(Employee.class, empCode);
		
		session.close();
		
		LOG.info("Successfully retrieved employee "+ empCode +" from DB.");
		return emp;
	}
	
	public void addEmployee(Employee emp) {
		LOG.info("Adding employee : " + emp.getEmpCode() + " to DB.");

		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
		
		LOG.info("Successfully added employee "+ emp.getEmpCode() +" to DB.");

	}
	
	public void deleteEmployee(Employee emp) {
		LOG.info("Deleting employee : " + emp.getEmpCode() + " from DB.");
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.remove(emp);
		
		session.getTransaction().commit();
		session.close();
		
		LOG.info("Successfully deleted employee "+ emp.getEmpCode() +" from DB.");
	}
	
	public void updateEmployee(Employee emp) {
		LOG.info("Updating employee : " + emp.getEmpCode() + " to DB.");
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.update(emp);
		session.getTransaction().commit();
		session.close();
		
		LOG.info("Successfully updated employee "+ emp.getEmpCode() +" from DB.");
	}
}
