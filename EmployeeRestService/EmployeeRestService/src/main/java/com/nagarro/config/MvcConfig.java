package com.nagarro.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.nagarro.dao.EmployeeDao;
import com.nagarro.daoImp.EmployeeDaoImp;
import com.nagarro.dto.Employee;
import com.nagarro.services.EmployeeService;
import com.nagarro.services.EmployeeServiceImp;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.nagarro"})
public class MvcConfig extends WebMvcConfigurerAdapter
{
	
	@Bean(name = "viewResolver")
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        //resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/employee");
	    dataSource.setUsername("root");
	    dataSource.setPassword("root");
	 
	    return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.addAnnotatedClasses(Employee.class);
	    sessionBuilder.addProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    properties.put("hibernate.c3p0.min_size", "5");
	    properties.put("hibernate.c3p0.max_size", "20");
	    properties.put("hibernate.c3p0.timeout", "500");
	    return properties;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	 
	    return transactionManager;
	}	
	@Bean
	public PlatformTransactionManager transactionManager() {
	    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
	    return transactionManager;
	}
	
	@Autowired
	@Bean(name = "employeeDao")
	public EmployeeDao getEmployeeDao(SessionFactory sessionFactory) {
	    return new EmployeeDaoImp(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "empservice")
	public EmployeeService getEmpService(SessionFactory sessionFactory) {
	    return  new EmployeeServiceImp();
	}	
}
