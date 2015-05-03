package com.example.netsocial.objects;

public class LoginModel 
{
    private String userName;
    public String  password;
    private boolean rememberMe;
    
    public LoginModel(){}
    public LoginModel(String login, String password)
    {
    	this.userName = login;
    	this.password = password;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswod() {
		return password;
	}
	public void setPasswod(String passwod) {
		this.password = passwod;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
    
    
}
