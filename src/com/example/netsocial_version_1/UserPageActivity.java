package com.example.netsocial_version_1;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netsocial.helps_activities.ImageBitmaps;
import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.FriendsOfUser;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.objects.UserPage;
import com.example.netsocial.user.FriendGroup;
import com.example.netsocial.user.MyUser;
import com.example.netsocial.user.UserFriend;
import com.example.netsocial.utils.BsonUtils;
import com.example.netsocial.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class UserPageActivity extends Activity {

	public static final String ID = "ID";
	
	final int ACTION_GET_USER_PROFILE = 1;
	final int ACTION_GO_TO_DEATAILS = 2;
	final int ACTION_ADD_TO_FRIEND = 3;
	 final int ACTION_GET_USER_IMAGE = 4;
	 Cookie cookie;
	String userId = "";
	FriendsOfUser friendsOfUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_page);
	
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		Intent intent = getIntent();
		userId = intent.getStringExtra(ID);
		if (cookie == null)
			{
			  cookie = ( (AppContext) this.getApplicationContext()).getCokie();
			}
		
		
		
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		finish();
		overridePendingTransition(R.anim.pull_in_left,
				R.anim.push_out_right);
		return true;
	}
	
	 private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {

	
		 Cookie cok;
		
		 DefaultHttpClient httpClient;
		 int numb = 0;
		 

		 public HttpAsyncTask( DefaultHttpClient httpClient, Cookie cok)
		 {
			this.httpClient = httpClient;
			 this.cok = cok;
			
		 }
		 
		 public HttpAsyncTask( DefaultHttpClient httpClient, Cookie cok, int numb)
		 {
			this.httpClient = httpClient;
			 this.cok = cok;
			 this.numb = numb;
			 
			 
			
		 }
		 


		 private ProgressDialog dialog;

		 @Override
				protected void onPreExecute() 
		           {
			 if ( numb == ACTION_GET_USER_PROFILE)
			 {
			          this.dialog = new ProgressDialog(UserPageActivity.this);

			        	  this.dialog.setMessage("Загрузка профиля");
			          
			          
			          if (!this.dialog.isShowing()) {
							this.dialog.show();
						}
			 }
					
				}
	        @Override
	        protected RequestState doInBackground(String... urls) {

	        	
	        	if ( numb == ACTION_GET_USER_PROFILE || numb == ACTION_ADD_TO_FRIEND)
	        	{
	        		 return HttpUtils.GET1(httpClient, urls[0], cok);
	        	}
	        	else
	        	{
	        		return BsonUtils.GET(httpClient, urls[0], cok);
	        	}
	        	
	        	

	 
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(RequestState rs) 
	        {
	            
	            if (rs != null && rs.getRequest_code() == 200)
	            {
	            	if ( numb == ACTION_GET_USER_PROFILE)
	            	{
	            	
	            		String json = (String) rs.getObject();
	            		
	            		System.out.println("user = \n" +  json);
	            				
	            		
	            		saveUserProfile(json);

	            	} else if ( numb == ACTION_GET_USER_IMAGE)
	            	{
	            	saveBitmap((byte [] )rs.getObject());
	            		//Toast.makeText(getBaseContext(),"Отправка прошла успешно", Toast.LENGTH_SHORT).show();
	            	}
	            	else if ( numb == ACTION_ADD_TO_FRIEND)
	            	{
	            		Toast.makeText(getBaseContext(),"В друзья добавлен!", Toast.LENGTH_SHORT).show();
	            	}
	            	
	            	
	            	
	            }
	            else
	            {
	            	//Toast.makeText(getBaseContext(), "Ошибка : " + rs.getRequest_code() + ". Не удалось авторизоваться!", Toast.LENGTH_LONG).show();
	            }
				   
	          if ( numb == ACTION_GET_USER_PROFILE) {  this.dialog.dismiss();}
	            
	            

	        }

			
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_page, menu);
		return true;
	}

	public void saveUserProfile(String json)
	{
		ArrayList<UserPage> list = new ArrayList<UserPage>();
		try
		{
		JSONObject js = new JSONObject(json);
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(js);
		
		list = new Gson().fromJson(jsonArray.toString(), 
				new TypeToken<ArrayList<UserPage>>() {
		              }.getType()
				);
		
		userPage = list.get(0);
		
		
		fillViews();
		System.out.println("user = " + userPage.getName());
		
		
		
       
		
		
		
		
		
		}catch (Exception e  )
		{
			System.out.println(e.getMessage());
		}
		
		
      JSONObject j = null;
	try {
		j = new JSONObject(json);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		JSONArray array = null;
		try {
			array = j.getJSONArray("FriendsGroups");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 jsonArrayStr = array.toString();
		
		System.out.println("count = " + array.length());
		System.out.println("array = " + jsonArrayStr);
		ArrayList<FriendGroup> fr = new ArrayList<FriendGroup>();
		
		//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:sssZ").create();
		
		
		
		
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
////						( fr.size() < 1 && (ArrayList<UserFriend>) fr.get(0).getFriends() == null ? null : (ArrayList<UserFriend>) fr.get(0).getFriends()),
////						(fr.size() < 2 && (ArrayList<UserFriend>) fr.get(1).getFriends() == null ? null : (ArrayList<UserFriend>) fr.get(1).getFriends()),
////						( fr.size() < 3 && (ArrayList<UserFriend>) fr.get(2).getFriends() == null ? null : (ArrayList<UserFriend>) fr.get(2).getFriends())
				
						   list_def, list_p, list_inv
						);
		
		
//		System.out.println("userId = " + fr.get(1).getFriends().get(0).getUserId());
		
	//	System.out.println("userId = " + fr_default.get(0).getUserId());
		
		
	}
	
	String jsonArrayStr;

	UserPage userPage ;

	
	TextView tvName, tvLevel_count, tvLevelName, tvStatusName;
	Button btnAdd, btnDetails;

	private void fillViews()
	{
		tvName = (TextView) findViewById(R.id.user_page_tv_name);
		if (!TextUtils.isEmpty( userPage.getName()) && userPage.getName() != null )
				{
			           tvName.setText(userPage.getName());
				}
		else
		{
			 tvName.setText(userPage.getLogin());
		}
		tvLevel_count = (TextView) findViewById(R.id.user_page_tv_level_count);
		tvLevel_count.setText(userPage.getLevel() + "");
		
		tvLevelName = (TextView) findViewById(R.id.user_page_tv_level_name);
		tvStatusName = (TextView) findViewById(R.id.user_page_tv_status);
		if ( !TextUtils.isEmpty( userPage.getLevel_name() ))
		{
		   tvLevelName.setText(userPage.getLevel_name());
		   tvStatusName.setText(userPage.getLevel_name());
		}
		
		btnAdd = (Button) findViewById(R.id.user_page_tv_btn_add_friend);
		btnDetails = (Button) findViewById(R.id.user_page_details);
		
		String myId = getUserID();
		
		
		if ( myId.equals(userId))
		{
			
			btnAdd.setVisibility(View.INVISIBLE);
			
		}
		else
		{
			btnDetails.setVisibility(View.INVISIBLE);
		}
		
		
		
		
		
		String url = AppContext.USER_PAGE_IMAGE_URL + userId;
		
//			
			new HttpAsyncTask(getHttpClient(), cookie, ACTION_GET_USER_IMAGE).execute(url);
		
	}
	


	private void saveBitmap(byte[] byteArray) 
	{
		

		
		final ArrayList<byte[]> bitmaps = new ArrayList<byte[]>();
		bitmaps.add(byteArray);
		Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
		
		ImageView iv = (ImageView) findViewById(R.id.user_page_iv_avatar);
		iv.setVisibility(View.VISIBLE);

		iv.setImageBitmap(bmp);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				if ( !bitmaps.isEmpty())
				{
				ImageBitmaps ib = new ImageBitmaps(0, bitmaps);
				 
				 Intent intent = new Intent(UserPageActivity.this, ShowImagesActivity.class);
				 
				 intent.putExtra("ImageBitmaps", ib);
				 
				 fl = true;
				 
				 startActivity(intent);
				}
				
			}
		});
	}
	
	private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}
	
	private String getUserID()
	 {
		 return ((AppContext)this.getApplicationContext()).getUserId();
	 }
	
	public void onClick (View v)
	{
		if ( v.getId() == R.id.user_page_tv_btn_add_friend)
		{
			String url = AppContext.FRIEND_ADD_URL + userId;
			new HttpAsyncTask(getHttpClient(), cookie,ACTION_ADD_TO_FRIEND).execute(url);
			
		}
		else if ( v.getId() == R.id.user_page_friends)
		{
			if ( friendsOfUser == null)
			{
				System.out.println("iSnUll");
			}
			else
			{
				//System.out.println("size = " + friendsOfUser.getList_all_friends().size());
			}
			Intent intent = new Intent(UserPageActivity.this, FriendsActivity.class);
			intent.putExtra(FriendsActivity.FRIENDS, jsonArrayStr);
			startActivity(intent);
			
			overridePendingTransition(R.anim.pull_in_left,
    				R.anim.push_out_right);
		}
		else
		{
		Intent intent = new Intent(UserPageActivity.this, UserProfileActivity.class);
		
		MyUser myUser = new MyUser();
		myUser.setName(tvName.getText().toString());
		
		intent.putExtra(MyUser.MU_USER, myUser);
		startActivity(intent);
	    
		overridePendingTransition(R.anim.pull_in_right,
				R.anim.push_out_left);
		}
	}
	
	static boolean fl = false;
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String url = AppContext.USER_PAGE_URL;
		if ( userId == "")
		{
			url += getUserID();
		}
		else
		{
			url += userId;
		}
		//url = "http://103.16.228.174:33232/api/image?userId=" + getUserID();
	//	url = "http://103.16.228.174:33232/api/image?q_id=" +"53072117cea1c30ec0ee189a" + "&index=" + "0";
//		
		if ( fl == false)
		{
		new HttpAsyncTask(getHttpClient(), cookie, ACTION_GET_USER_PROFILE).execute(url);
		}
		else
		{
			fl = false;
		}
		
	}
	

}
