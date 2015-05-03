package com.example.netsocial.user;

import java.util.Date;

public class UserFriend 
{
	public String UserId ;//ID пользователя друга

	public Date DtAdded;

	public String getUserId() {
		return UserId;
	}
	
	public String GroupName;

	public void setUserId(String userId) {
		this.UserId = userId;
	}

	public Date getDtAdded() {
		return DtAdded;
	}

	public void setDtAdded(Date dtAdded) {
		this.DtAdded = dtAdded;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	
	
    
    
}
