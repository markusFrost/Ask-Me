package com.example.netsocial.objects;

public class RegisterModel 
{ 
	private String email;
	private String login;
	private String password;
	private String confirmPassword;
	
	public RegisterModel(){}
	public void setEmail (String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setLogin (String login)
	{
		this.login = login;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setPassword (String pas)
	{
		this.password = pas;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setConfirmPassword (String pas)
	{
		this.confirmPassword = pas;
	}
	
	public String getConfirmPassword()
	{
		return confirmPassword;
	}
	
	

}
