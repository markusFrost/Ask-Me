package com.example.netsocial_version_1;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;

import com.example.netsocial.objects.FriendsOfUser;
import com.example.netsocial.user.FriendGroup;
import com.example.netsocial.user.UserFriend;
import com.example.netsocial_version_1.fragments.FriedsFragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;


public class FriendsActivity extends FragmentActivity implements ActionBar.TabListener 
{

	ViewPager pager;
	  PagerAdapter pagerAdapter;
	  ActionBar bar;
	  ArrayList<FriendGroup> fr = new ArrayList<FriendGroup>();
	  FriendsOfUser friendsOfUser;
	  
	  public static final String FRIENDS = "friends";
	  ArrayList<ArrayList<UserFriend>> userFriends = new ArrayList<ArrayList<UserFriend>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		
		Intent intent = getIntent();
		
		String jsonArrayStr = intent.getStringExtra(FRIENDS);
		
         getUserFriends(jsonArrayStr);
		
		fillBar();

		 pager = (ViewPager) findViewById(R.id.pager);
		    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		    pager.setAdapter(pagerAdapter);

		    pager.setOnPageChangeListener(new OnPageChangeListener() {

		      @Override
		      public void onPageSelected(int position) {
		    	  
		    	  bar.setSelectedNavigationItem(position);
		     
		      }

		      @Override
		      public void onPageScrolled(int position, float positionOffset,
		          int positionOffsetPixels) {
		      }

		      @Override
		      public void onPageScrollStateChanged(int state) {
		      }
		    });
		
		
	}
	
	
	
	
	private void fillBar()
	{
		bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    Tab tab = bar.newTab();
	    tab.setText("default");
	    tab.setTag(0);
	    tab.setTabListener(this);
	    bar.addTab(tab);
	    
	     tab = bar.newTab();
	    tab.setText("prefriends");
	    tab.setTabListener(this);
	    tab.setTag(1);
	    bar.addTab(tab);
	    
	    tab = bar.newTab();
	    tab.setText("invites");
	    tab.setTabListener(this);
	    tab.setTag(2);
	    bar.addTab(tab);
	    
		
	}




	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	    public MyFragmentPagerAdapter(FragmentManager fm) {
	      super(fm);
	    }

	    @Override
	    public Fragment getItem(int position) {

	      return FriedsFragment.newInstanse( userFriends.get(position));
	    	

	    }

	    @Override
	    public int getCount() {
	      return 3;
	    }

	  }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
		return true;
	}



	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
		
		
	}



	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		int index = (Integer) tab.getTag();
		System.out.println("index = " + index);
		if ( pager != null)
		 pager.setCurrentItem(index);
		
	}



	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private void getUserFriends(String jsonArrayStr)
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class,
				new JsonDeserializer<Date>() {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssZ");

					@Override
					public Date deserialize(JsonElement json, Type arg1,
							JsonDeserializationContext arg2)
							throws JsonParseException {
						// TODO Auto-generated method stub
						try {
							return df.parse(json.getAsString());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
						
					}
					
					

				});
		
Gson gson = gsonBuilder.create();
		
		fr = gson.fromJson(jsonArrayStr, 
				new TypeToken<ArrayList<FriendGroup>>() {
		              }.getType()
				);
		
		ArrayList<UserFriend> list_def = (ArrayList<UserFriend>) fr.get(0).getFriends();
		ArrayList<UserFriend> list_p = (ArrayList<UserFriend>) fr.get(1).getFriends();
		ArrayList<UserFriend> list_inv = (ArrayList<UserFriend>) fr.get(2).getFriends();
		
		friendsOfUser = new FriendsOfUser
				(
			
						   list_def, list_p, list_inv
						);
		
		
		userFriends = friendsOfUser.list_all_friends;
	}

}
