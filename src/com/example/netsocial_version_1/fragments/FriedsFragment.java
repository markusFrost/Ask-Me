package com.example.netsocial_version_1.fragments;

import java.util.ArrayList;

import com.example.netsocial.user.UserFriend;
import com.example.netsocial_version_1.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FriedsFragment extends Fragment
{
	static final String ARG_FRIENDS = "arg_friends";
	
	ArrayList<String> usersName = null;
	
	public static FriedsFragment newInstanse (  ArrayList<UserFriend> userFriends)
	{
		FriedsFragment ff = new FriedsFragment();
		 
		 Bundle args = new Bundle();

			ArrayList<String> usersName = new ArrayList<String>();
			
			for ( UserFriend uf : userFriends)
			{
				usersName.add(uf.getUserId());
			}
			
			args.putStringArrayList(ARG_FRIENDS, usersName);
		
		
		 
		 ff.setArguments(args);
		 return ff;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		usersName = getArguments().getStringArrayList(ARG_FRIENDS);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{

		View view = inflater.inflate(R.layout.fragment_friends, null);
		
	
		
		ListView lv = (ListView) view.findViewById(R.id.friends_lv);
		
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, usersName);
		
		lv.setAdapter(adapter);
		
		
		return view;
	}
}
