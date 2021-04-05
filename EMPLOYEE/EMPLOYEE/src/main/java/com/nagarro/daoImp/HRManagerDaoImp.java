package com.nagarro.daoImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.dao.HRManagerDao;
import com.nagarro.dto.HRManager;

@Component
public class HRManagerDaoImp implements HRManagerDao {

	Configuration con;
	SessionFactory sessionFactory;

	@Autowired
	public HRManagerDaoImp() {
		con = new Configuration().configure();
		sessionFactory = con.buildSessionFactory();
	}

	public HRManager getHRManager(String hrmId) {
		Session session = sessionFactory.openSession();

		HRManager hrmObject = session.get(HRManager.class, hrmId);

		session.close();

		return hrmObject;
	}

}
