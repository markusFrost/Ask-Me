package com.example.netsocial.user;

public class UserFullyDto 
{ 
	public AskMeUser usr;
	public byte[] Image;
	public AskMeUser getAskMeUser() {
		return usr;
	}
	public void setAskMeUser(AskMeUser askMeUser) {
		this.usr = askMeUser;
	}
	public byte[] getImage() {
		return Image;
	}
	public void setImage(byte[] image) {
		this.Image = image;
	}
	
	

}
