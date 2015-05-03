package com.example.netsocial.user;

import java.util.Date;
import java.util.List;


public class FriendGroup
{
	public String Name;

	public List<UserFriend> Friends;
	   
	public boolean visible;

	public Date DtCreation;
	//public String DtCreation;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public List<UserFriend> getFriends() {
		return Friends;
	}

	public void setFriends(List<UserFriend> friends) {
		this.Friends = friends;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

//	public String getDtCration() {
//		return DtCreation;
//	}
//
//	public void setDtCration(String dtCration) {
//		this.DtCreation = dtCration;
//	}
	
	public Date getDtCration() {
	return DtCreation;
}

public void setDtCration(Date dtCration) {
	this.DtCreation = dtCration;
}
	    
	    
}
