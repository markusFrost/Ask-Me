package com.example.netsocial.objects;

public class UserPage 
{
	public UserPage(){}
	
	private String Login;
	private String Name;
	private int Level;
	private String LevelName;
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		this.Login = login;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		this.Level = level;
	}
	public String getLevel_name() {
		return LevelName;
	}
	public void setLevel_name(String level_name) {
		this.LevelName = level_name;
	}
	
	

}
