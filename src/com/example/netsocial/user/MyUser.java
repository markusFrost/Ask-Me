package com.example.netsocial.user;

import java.io.Serializable;
import java.util.Date;

public class MyUser implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2987579973175753700L;
	
	public static final String MU_USER = "myUser";
	
	public MyUser() {
		// TODO Auto-generated constructor stub
	}
	
	public String name;
	public String status;
	
	public Date birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	

}
