package com.nagarro.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hrmanager")
public class HRManager {

	@Id
	@Column(name = "id")
	private String hrId;

	@Column(name = "pswd")
	private String hrPswd;

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public String getHrPswd() {
		return hrPswd;
	}

	public void setHrPswd(String hrPswd) {
		this.hrPswd = hrPswd;
	}

}
