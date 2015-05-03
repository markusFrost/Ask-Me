package com.example.netsocial.user;

import java.util.Date;
import java.util.List;

public class UserProfile
{ 
	public String _id;

	public String photo;

	public UserSettings settings; //Пока класс UserSettings пустой

   // public LocationAm location ;

   // public UserSex sex ;

	public Date birthday;
    
	public Date dtCreation;

	public String specialization;

   // public List<TopicRating> TopicsRating;

	public String AboutMe;

	public String status;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public UserSettings getSettings() {
		return settings;
	}

	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDtCreation() {
		return dtCreation;
	}

	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAboutMe() {
		return AboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.AboutMe = aboutMe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    // public Answer[] answer ;
	
	
    
    

}
