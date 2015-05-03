package com.example.netsocial.objects;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.netsocial.user.UserFriend;

public class FriendsOfUser implements Serializable
{
	

		/**
	 * 
	 */
	private static final long serialVersionUID = 6214707402343296941L;
		public ArrayList<UserFriend> list_default;
	public ArrayList<UserFriend> list_invites;
	public ArrayList<UserFriend> list_prefriends;
	public ArrayList<ArrayList<UserFriend>> list_all_friends = new ArrayList<ArrayList<UserFriend>>();
	
	public FriendsOfUser (
			ArrayList<UserFriend> list_default,
			ArrayList<UserFriend> list_invites,
			ArrayList<UserFriend> list_prefriends
			)
	{
		this.list_default = list_default;
		this.list_invites = list_invites;
		this.list_prefriends = list_prefriends;
		list_all_friends.add(list_default);
		list_all_friends.add(list_prefriends);
		list_all_friends.add(list_invites);
	}

	
	
	
	
	
	

}
