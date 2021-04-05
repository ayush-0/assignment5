package com.nagarro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.HRManagerDao;
import com.nagarro.dto.HRManager;

@Service
public class SignService {

	@Autowired
	private HRManagerDao hrmDao;

	public boolean validateHRManager(String hrmId, String hrmPswd) {

		HRManager hrmObject = hrmDao.getHRManager(hrmId);

		return hrmObject != null && hrmObject.getHrPswd().equals(hrmPswd);
	}

}
