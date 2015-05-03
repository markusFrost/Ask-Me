package com.example.netsocial.user;

import java.util.List;

public class AskMeUser 
{
	public String _Id;

	public String Login;

	public String Email ;
	public String Name;
	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public List<FriendGroup> FriendsGroups;


	public UserProfile Profile;

	public int Points;

	public int Level ;

	public String get_id() {
		return _Id;
	}

	public void set_id(String _id) {
		this._Id = _id;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		this.Login = login;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public List<FriendGroup> getFriendsGroups() {
		return FriendsGroups;
	}

	public void setFriendsGroups(List<FriendGroup> friendsGroups) {
		this.FriendsGroups = friendsGroups;
	}

	public UserProfile getProfile() {
		return Profile;
	}

	public void setProfile(UserProfile profile) {
		this.Profile = profile;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		this.Points = points;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		this.Level = level;
	}
	
	
}


