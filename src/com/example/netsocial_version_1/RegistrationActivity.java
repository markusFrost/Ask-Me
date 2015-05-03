package com.example.netsocial_version_1;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.RegisterModel;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.utils.HttpUtils;
import com.example.netsocial.utils.JsonUtil;

public class RegistrationActivity extends Activity {

	EditText editLogin, editPassword, editEmail, editRepeatPassword;
	Button btnSubmit;
	TextView tvError;
	boolean fl = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		fillView();
		
		//btnSubmit.setVisibility(View.VISIBLE);
		
		if  (!isConnected())
		{
			fl = false;
			tvError.setVisibility(View.VISIBLE);
			tvError.setText("No Internet");
			
		}
		else
		{
			fl = true;
		}
		
		
		
		
		
		
	}
	
//	private void sendJsonToServer(RegisterModel rm)
//	{
//		System.out.println("sendJsontoServer");
//		HttpUtils.POST(AppContext.REGISTRATION_URL, JsonUtil.toJSon(rm));
//	}
	
	String password;
	String repeatPassword;

	private void fillView() 
	{
		tvError = (TextView) findViewById(R.id.registration_tv_pass_others);
		btnSubmit = (Button) findViewById(R.id.registration_btn_submit);
		editEmail = (EditText) findViewById(R.id.registration_edit_email);
		editLogin = (EditText) findViewById(R.id.registration_edit_login);
		editPassword = (EditText) findViewById(R.id.registration_editPassword);
		editRepeatPassword = (EditText) findViewById(R.id.registration_repeat_password);
		
		
//		editEmail.addTextChangedListener(new MyTextWatcher());
//		editLogin.addTextChangedListener(new MyTextWatcher());
		//editPassword.addTextChangedListener(new MyTextWatcher());
		//editRepeatPassword.addTextChangedListener(new MyTextWatcher());
		
		
		//------------------------------------------------------------
		
		editPassword.addTextChangedListener(new TextWatcher() 
		{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
//				password = editPassword.getText().toString();
//				if ( password.isEmpty() || password.length() < 6)
//				{
//					btnSubmit.setVisibility(View.INVISIBLE);
//					return;
//				}
//				else
//				{
//					btnSubmit.setVisibility(View.VISIBLE);
//				}
//				
//				if ( password.equals( repeatPassword) )
//				{
//					tvError.setVisibility(View.INVISIBLE);	
//					btnSubmit.setVisibility(View.VISIBLE);
//				}
//				else {
//					tvError.setVisibility(View.VISIBLE);
//					btnSubmit.setVisibility(View.INVISIBLE);
//				}
				
			}
		});
		
		//----------------------------------------------------------------
		
		editRepeatPassword.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) 
			{
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
//				repeatPassword = editRepeatPassword.getText().toString();
//				
//				if ( repeatPassword.isEmpty() || repeatPassword.length() < 6)
//				{
//					btnSubmit.setVisibility(View.INVISIBLE);
//					return;
//				}
//				else
//				{
//					btnSubmit.setVisibility(View.VISIBLE);
//				}
//				if ( repeatPassword.equals(password))
//				{
//					tvError.setVisibility(View.INVISIBLE);	
//					btnSubmit.setVisibility(View.VISIBLE);
//				}
//				else {
//					tvError.setVisibility(View.VISIBLE);
//					btnSubmit.setVisibility(View.INVISIBLE);
//				}
				
			}
		});
		
		
		
		
	}
	
	
	
	 private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {
		 DefaultHttpClient httpClient;
		 
		 public HttpAsyncTask (DefaultHttpClient httpClient)
		 {
			 this.httpClient = httpClient;
		 }
	        @Override
	        protected RequestState doInBackground(String... urls) {
	 
	        	RegisterModel rm = new RegisterModel();
//		    	rm.setEmail(editEmail.getText().toString());
//		    	rm.setLogin(editLogin.getText().toString());
//		    	rm.setPassword(editPassword.getText().toString());
//		    	rm.setConfirmPassword(editRepeatPassword.getText().toString());
		    	
		    	rm.setEmail("email@user5.ru");
		    	rm.setLogin("user5Login");
		    	rm.setPassword("user5Pass");
		    	rm.setConfirmPassword("user5Pass");
		    	
	 
	            return HttpUtils.POST(httpClient, urls[0], null, JsonUtil.serialize(rm));
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(RequestState rs)
	        {
	        	if ( rs.getRequest_code() == 201)
	        	{
	            Toast.makeText(getBaseContext(), "Регистрация прошла успешно", Toast.LENGTH_LONG).show();
	            startActivity(new Intent ( RegistrationActivity.this, MainPageActivity.class));
	        	}
	        	else
	        	{
	        		//Toast.makeText(getBaseContext(), "При регистрации возникли ошибки", Toast.LENGTH_LONG).show();
	        	
	        		tvError.setVisibility(View.VISIBLE);
	        		tvError.setText("При регистрации возникли ошибки");
	        	}
	       }
	    }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}
	
	public void onClick ( View v)
	{
		
		System.out.println("onCLick");
		switch ( v.getId())
		{
		case R.id.registration_clear_email:
		    {
			   editEmail.setText("");
		    }break;
		case R.id.registration_clear_login:
	        {
	        	editLogin.setText("");
	         }break;
		case R.id.registration_clear_password:
	         {
	        	 editPassword.setText("");
	        }break;
		case R.id.registration_clear_repeat_pass:
	       {
	    	   editRepeatPassword.setText("");
	        }break;
	        
		case R.id.registration_btn_submit:
		    {
		    	 if(isConnected())
		    	 {
		    	new HttpAsyncTask(getHttpClient()).execute(AppContext.REGISTRATION_URL);
		    	 }
		    	 else
		    	 {
		    		 tvError.setVisibility(View.VISIBLE);
		 			tvError.setText("No Internet");
		    	 }
		    }break;
	        
		}
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
	
	
	//------------------------------------------------------------------------
	
	private class MyTextWatcher implements TextWatcher
	{
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) 
		{
			
			// TODO Auto-generated method stub
			if ( TextUtils.isEmpty(editEmail.getText().toString()) || 
					TextUtils.isEmpty(editLogin.getText().toString()) ||	
					TextUtils.isEmpty(editPassword.getText().toString()) ||
					TextUtils.isEmpty(editRepeatPassword.getText().toString()) 

					)
			{
				tvError.setVisibility(View.VISIBLE);
				tvError.setText(R.string.string_registration_otherPoassw);
				btnSubmit.setVisibility(View.INVISIBLE);
			}
			else
			{
				tvError.setVisibility(View.GONE);
				btnSubmit.setVisibility(View.VISIBLE);
			}
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) 
		{
			
			
		}
	}
	
	private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}

}
