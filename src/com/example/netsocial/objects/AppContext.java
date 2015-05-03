package com.example.netsocial.objects;

import java.util.ArrayList;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Application;



public class AppContext extends Application 
{
	

	public static final String REGISTRATION_URL = "http://103.16.228.174:33232/api/account";

	
	public static final String MAIN_PAGE_LOGIN = "UserName";
	public static final String MAIN_PAGE_PASSWORD = "Password";

	public static final String MAIN_PAGE_URL = "http://103.16.228.174:33232/api/account/login";

	
	public static final String LAST_USER_FILE_NAME = "last_user";
	public static final String LAST_USER_LOGIN = "lastUserLogin";
	

	

	public static final String QUIZ_ACTIVITY_URL = "http://103.16.228.174:33232/api/questions";

	public static final String TOPIC_ACTIVITY_URI = "http://103.16.228.174:33232/api/topic";
	
	
	public static final String TOPIC_DETAILS_ACTIVITY = "topic_detailt_item";
	public static final String  TOPIC_DETAILS_ACTIVITY_URI = "http://103.16.228.174:33232/api/questions?topicid=";

	public static final String USER_PAGE_URL = "http://103.16.228.174:33232/api/user/";
	
	public static final String USER_PAGE_IMAGE_URL = "http://103.16.228.174:33232/api/image?userId=";
	
	
	public static final String QUIZ_PAGE_ACTIVITY_URL = "http://103.16.228.174:33232/api/questions?q_id=";

	public static final String QUIZ_PAGE_ACTIVITY_ANSWER_URL = "http://103.16.228.174:33232/api/answers";
   
	public static final String FRIEND_ADD_URL = "http://103.16.228.174:33232/api/user?frienduserid=";

	private  DefaultHttpClient httpClient;
	
	private String userId;
	
	



@Override
public void onCreate() 
{
	// TODO Auto-generated method stub
	super.onCreate();
	
	 httpClient = new DefaultHttpClient();

	
}



public DefaultHttpClient getHttpClient() 
{
	return httpClient;
}


public void setHttpClient(DefaultHttpClient httpClient) 
{
	this.httpClient = httpClient;
}



//-------------------------------------------------------
private Cookie cokie;


public Cookie getCokie() 
{

	   return cokie;
		
	}


	public void setCokie(Cookie cokie) 
	{

		this.cokie = cokie;

	}
	
	ArrayList<Topic> topics;

	public ArrayList<Topic> getTopics() {
		return topics;
	}


	public void setTopics(ArrayList<Topic> topics) {
		this.topics = topics;
	}
	
	

public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		
		int length = userId.length();
		userId = userId.substring(1, length - 1);
		this.userId = userId;
	}


	
	



}
