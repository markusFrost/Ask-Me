package com.example.netsocial_version_1;

import java.util.ArrayList;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.netsocial.objects.AppContext;


import com.example.netsocial.objects.LoginModel;
import com.example.netsocial.objects.RequestState;

import com.example.netsocial.utils.HttpUtils;
import com.example.netsocial.utils.JsonUtil;
import com.example.netsocial.utils.ShPrefUtils;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.AdapterView.OnItemClickListener;

public class MainPageActivity extends Activity //implements onSetCookieEventListener 
{

	private static final String LOGIN = "abcd";
	private static final String PASSWORD = "1234";
	
	
	AutoCompleteTextView editLogin;
	EditText editPass;
	Button btnLogo;
	TextView tv_invailed;
	ArrayList<String> logins;
	ArrayList<String> passwords;
	
	//Cookie cookie = null;;

	RequestState requestState;
	boolean fl = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
	
		fillViews();
		
		
		//ShPrefUtils.saveLastUser(getApplicationContext(), "");
		
		LoginModel lm = ShPrefUtils.getUser(getApplicationContext());
		
		lm = new LoginModel();
//		lm.setPasswod("user3Pass");
//		lm.setUserName("user3Login");
		lm.setPasswod("123456");
		lm.setUserName("tst");
		if (lm != null)
		{
			new HttpAsyncTask(getHttpClient(), lm).execute(AppContext.MAIN_PAGE_URL);
		}
		
		

	}

	Button btnJoin;
	private void fillViews() 
	{
		
		editLogin = (AutoCompleteTextView) findViewById(R.id.main_page_editLogin);
		editPass = (EditText) findViewById(R.id.main_page_editPassword);
		btnLogo = (Button) findViewById(R.id.main_page_btn_logo);
		btnJoin = (Button) findViewById(R.id.main_page_btn_join);
		
		//editLogin.addTextChangedListener(new MyTextWatcher());
		//editPass.addTextChangedListener(new MyTextWatcher());
		tv_invailed = (TextView) findViewById(R.id.main_page_tv_invailed);
		
		
		logins = new ArrayList<String>();
		logins.add(LOGIN);
		
		passwords = new ArrayList<String>();
		passwords.add(PASSWORD);
		
		
		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, logins);
	        editLogin.setAdapter(adapter);
	        
	        editLogin.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					editPass.setText(passwords.get(position));
					
				}
			});
		
		
		
	}

	public void onClick ( View v)
	{
		switch ( v.getId())
		{
		case R.id.main_page_clear_login:
		   {
			   editLogin.setText("");
		   }break;
		case R.id.main_page_clear_password:
		   {
			   editPass.setText("");
		   }break;
		case R.id.main_page_btn_join:
		   {



				   if(isConnected() )
			    	 {
						
					   tv_invailed.setVisibility(View.INVISIBLE);
						startActivity(new Intent(MainPageActivity.this, RegistrationActivity.class));
						overridePendingTransition(R.anim.pull_in_right,
								R.anim.push_out_left);
						
						
			    	 }
			    	 else
			    	 {
			    		 tv_invailed.setVisibility(View.VISIBLE);
			    		 tv_invailed.setText("No Internet");
			    	 }

		   }break;
		   
		case R.id.main_page_btn_logo:
		   {


				   tv_invailed.setVisibility(View.INVISIBLE);

				   if(isConnected() )
			    	 {
						

						new HttpAsyncTask(getHttpClient()).execute(AppContext.MAIN_PAGE_URL);
			    	 }
			    	 else
			    	 {
			    		 tv_invailed.setVisibility(View.VISIBLE);
			    		 tv_invailed.setText("No Internet");
			    	 }

		   }break;
		}
	}
	
	 private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {
//		 private Cookie cookie;
//
//		 public HttpAsyncTask(Cookie c) 
//		 {
//
//				 this.cookie = c;
//		 }
		 
		 LoginModel lm;
		 DefaultHttpClient httpClient;
		 
		 public HttpAsyncTask(DefaultHttpClient httpClient, LoginModel lm)
		 {
			 this.lm = lm;
			 this.httpClient = httpClient;
		 }
		 
		 public HttpAsyncTask(DefaultHttpClient httpClient)
		 {
			 this.httpClient = httpClient;
			 lm = null;
		 }
		 private ProgressDialog dialog;

		 @Override
				protected void onPreExecute() 
		           {
			          this.dialog = new ProgressDialog(MainPageActivity.this);
			          this.dialog.setMessage("Авторизация");
			          
			          if (!this.dialog.isShowing()) {
							this.dialog.show();
						}
					
				}
	        @Override
	        protected RequestState doInBackground(String... urls) {
	 
	        	if ( lm == null)
	        	{
	        	  lm = new LoginModel();
				   lm.setUserName(editLogin.getText().toString());
				   lm.setPasswod(editPass.getText().toString());
				   lm.setRememberMe(true);
	        	}
	        	lm.setRememberMe(true);
	        	 return HttpUtils.POST(httpClient, urls[0], null,JsonUtil.serialize(lm));

	 
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(RequestState rs) 
	        {
	            
	            if ( rs.getRequest_code() == 200)
	            {
	               LoginModel lm = new LoginModel();
				   lm.setUserName(editLogin.getText().toString());
				   lm.setPasswod(editPass.getText().toString());
				   lm.setRememberMe(true);
				   
				   if (! TextUtils.isEmpty(lm.getUserName())
						   && !TextUtils.isEmpty(lm.getPasswod()))
				   {
				   
				   ShPrefUtils.saveUser(getApplicationContext(), lm);
				   ShPrefUtils.saveLastUser(getApplicationContext(), lm.getUserName());
				   }
				   
				   saveCookie(rs);
				   //Toast.makeText(getBaseContext(), "Авторизация прошла успешно", Toast.LENGTH_LONG).show();
				
				  startActivity(new Intent ( MainPageActivity.this, MainTopicActivity.class));
				  // startActivity(new Intent ( MainPageActivity.this, UserPageActivity.class));
	            }
	            else
	            {
	            	tv_invailed.setVisibility(View.VISIBLE);
	            	tv_invailed.setText(R.string.string_main_page_invailed_l_or_p);
	            }
				   
	            this.dialog.dismiss();
	            
	            

	        }

			
	    }
	 
	 private void saveCookie(RequestState rs) 
	 {
		 ArrayList<Object> objects = (ArrayList<Object>) rs.getObject();
                 ((AppContext) this.getApplicationContext()).setCokie(
                		(Cookie) objects.get(0));
                 String id = (String) objects.get(1);
                 System.out.println("id______ = " + id );
                ( (AppContext)this.getApplicationContext()).setUserId(id);
			
	 }

	 public boolean isConnected()
	 {
		
	        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
	            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	            if (networkInfo != null && networkInfo.isConnected()) 
	                return true;
	            else
	                return false;    
	 }
	 
	
	
	
//	private class MyTextWatcher implements TextWatcher
//	{
//		
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before, int count) 
//		{
//			// TODO Auto-generated method stub
//			
//			
//		}
//		
//		@Override
//		public void beforeTextChanged(CharSequence s, int start, int count,
//				int after) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void afterTextChanged(Editable s) 
//		{
//
//			if ( TextUtils.isEmpty(editLogin.getText().toString()) || 
//					TextUtils.isEmpty(editPass.getText().toString()) )
//			{
//				btnLogo.setVisibility(View.INVISIBLE);
//				if (fl)
//				{
//					tv_invailed.setText(R.string.string_main_page_invailed_l_or_p);
//				}
//				else
//				{
//					tv_invailed.setText("No Internet");
//				}
//			
//				tv_invailed.setVisibility(View.VISIBLE);
//			}
//			else
//			{
//				btnLogo.setVisibility(View.VISIBLE);
//				tv_invailed.setVisibility(View.INVISIBLE);
//			}
//			
//		}
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}


	private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}
	
	

}
