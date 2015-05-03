package com.example.netsocial.user;

public class UserSettings 
{
	public boolean notice ; //получать уведомления
	public String language ;

	public boolean visibleProfile;

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean notice) {
		this.notice = notice;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isVisibleProfile() {
		return visibleProfile;
	}

	public void setVisibleProfile(boolean visibleProfile) {
		this.visibleProfile = visibleProfile;
	}
	
	
	
}
