package com.example.netsocial_version_1;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netsocial.helps_activities.ImageBitmaps;
import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.user.AskMeUser;
import com.example.netsocial.user.MyUser;
import com.example.netsocial.user.UserFullyDto;
import com.example.netsocial.user.UserProfile;
import com.example.netsocial.utils.BitmapUtils;
import com.example.netsocial.utils.BsonUtils;
import com.example.netsocial.utils.ShPrefUtils;

public class UserProfileActivity extends Activity {

	protected static final int GO_TO_GALLETY = 1;
	private static Cookie cookie;
	Date date;
	MyUser myUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (cookie == null)
		{
		  cookie = ( (AppContext) this.getApplicationContext()).getCokie();
		}
		
		Intent intent = getIntent();
		 myUser = (MyUser) intent.getExtras().get(MyUser.MU_USER);
		
//		UserFullyDto ufd = new UserFullyDto();
//		
//		AskMeUser askMeUser = new AskMeUser();
//		
//		UserProfile userProfile = new UserProfile();
//		userProfile.setAboutMe("Lalala");
//		
//		askMeUser.setProfile(userProfile);
//		ufd.setAskMeUser(askMeUser);
//		ufd.setImage(null);
		
//		ufd.setAskMeUser(null);
//		ufd.setImage(null);
		
		fillViews();
		
		
		String url = "http://103.16.228.174:33232/api/user";
		//new HttpAsyncTask(getHttpClient(), cookie, BsonUtils.serialize(ufd)).execute(url);
	}
	
	 private void saveDataAndSendToServer() 
	 {
		 String name = tvName.getText().toString();
		 String status = tvStatus.getText().toString();
		 
		
			UserFullyDto ufd = new UserFullyDto();
			
			AskMeUser askMeUser = new AskMeUser();
			
			UserProfile userProfile = new UserProfile();
			
			if (!TextUtils.isEmpty(status))
			{
				userProfile.setStatus(status);
				
			}
			
			if ( date != null)
			{
				userProfile.setBirthday(date);
				
			}
			
			
			if (!TextUtils.isEmpty(name))
			{
			     askMeUser.setName(name);
			}
			else
			{
				askMeUser.setName(myUser.getName());
			}
			
			if ( iv_photo.getVisibility() == View.VISIBLE)
			{
				Bitmap bmp = ((BitmapDrawable) iv_photo.getDrawable()).getBitmap();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();
				ufd.setImage(byteArray);
			}
			else
			{
				ufd.setImage(null);
			}
			
			askMeUser.setProfile(userProfile);
			ufd.setAskMeUser(askMeUser);
			
			
			String url = "http://103.16.228.174:33232/api/user";
			new HttpAsyncTask(getHttpClient(), cookie, BsonUtils.serialize(ufd)).execute(url);
	 }
	
	RelativeLayout rlGetPhoto,rlGetName, rlGetStatus, rlGetBirthDay;
	ImageView iv_photo;
	TextView tvName, tvStatus, tvData;
	
	private void fillViews() 
	{
		tvName = (TextView) findViewById(R.id.user_settings_profile_tv_chosed_name);
		iv_photo = (ImageView) findViewById(R.id.user_settings_profile_iv_user_photo);
		tvStatus = (TextView) findViewById(R.id.user_settings_profile_tv_status_content);
		tvData = (TextView) findViewById(R.id.user_settings_profile_tv_birthday_data);
		
		iv_photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				ImageBitmaps ib = new ImageBitmaps(0, bitmaps);
				 
				 Intent intent = new Intent(UserProfileActivity.this, ShowImagesActivity.class);
				 
				 intent.putExtra("ImageBitmaps", ib);
				 
				 startActivity(intent);
				
			}
		});
		
		
		rlGetPhoto = (RelativeLayout) findViewById(R.id.user_settings_rl_getPhoto);
		rlGetPhoto.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				final Intent galleryIntent = new Intent(
			            Intent.ACTION_GET_CONTENT);

	            galleryIntent.setType("*/*");
	            startActivityForResult(galleryIntent, GO_TO_GALLETY);
				
			}
		});
		
		rlGetName = (RelativeLayout) findViewById(R.id. user_settings_rl_getName);
		rlGetName.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				createAlertDialog("Input Name", ACTION_SAVE_NAME);
				
			}
		});
		
		rlGetStatus = (RelativeLayout) findViewById(R.id. user_settings_rl_getStatus);
		
		rlGetStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createAlertDialog("Input Status", ACTION_SAVE_STATUS);
				
			}
		});
		
		rlGetBirthDay = (RelativeLayout) findViewById(R.id. user_settings_rl_getBirthday);
		rlGetBirthDay.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_DATE);
				
			}
		});
		
	}
	


	final int ACTION_SAVE_NAME = 1;
	final int ACTION_SAVE_STATUS = 2;

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		if ( item.getItemId() == R.id.menu_user_profile__ok)
		{
			saveDataAndSendToServer();
			return true;
		}
		else
		{
		finish();
		overridePendingTransition(R.anim.pull_in_left,
				R.anim.push_out_right);
		}
		return true;
	}
	
	

	private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {

		 byte[] arr;
		 Cookie cok;
		 DefaultHttpClient httpClient;
		
		 

		 public HttpAsyncTask( DefaultHttpClient httpClient, Cookie cok, byte[] arr)
		 {
			this.httpClient = httpClient;
			 this.cok = cok;
			 this.arr = arr;
		 }
		 


		 private ProgressDialog dialog;

		 @Override
				protected void onPreExecute() 
		           {
			          this.dialog = new ProgressDialog(UserProfileActivity.this);

			        	  this.dialog.setMessage("Отправка вопроса");
			          
			          
			          if (!this.dialog.isShowing()) {
							this.dialog.show();
						}
					
				}
	        @Override
	        protected RequestState doInBackground(String... urls) {

	        		 return BsonUtils.PUT(httpClient, urls[0], cok, arr);

	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(RequestState rs) 
	        {
	        	  this.dialog.dismiss();
	            if (rs != null && rs.getRequest_code() == 200)
	            {
	            	
	            		Toast.makeText(getBaseContext(),"Отправка прошла успешно", Toast.LENGTH_SHORT).show();
	            		finish();
	            	
	            	
	            	
	            }
	            else
	            {
	            	Toast.makeText(getBaseContext(), "Ошибка : " + rs.getRequest_code() + ". Не удалось авторизоваться!", Toast.LENGTH_LONG).show();
	            }
 

	        }

			
	    }
	 
	// android.content.DialogInterface.OnClickListener
	 EditText editAnswer;
	 


	 private void createAlertDialog(String strTitle, final int action)
		{
			final AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setTitle(strTitle);
			
			 editAnswer = new EditText(getApplicationContext());
			editAnswer.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
			editAnswer.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_show));
			editAnswer.setTextColor(getApplicationContext().getResources().getColor(R.color.color_black));		
			adb.setView(editAnswer);
			
			adb.setPositiveButton( "Ok", new DialogInterface.OnClickListener() 
			{
                public void onClick(DialogInterface dialog, int which) 
                {
                	if ( action == ACTION_SAVE_NAME)
                	{
                       tvName.setText(editAnswer.getText().toString());
                	}
                	else
                	{
                		tvStatus.setText(editAnswer.getText().toString());
                	}
                   
                }
            });
			
			adb.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            } );
			
			adb.show();

		}
	 
   
 	public void onClick (View v)
 	{
 		ShPrefUtils.saveLastUser(getApplicationContext(), "");
 		startActivity(new Intent(UserProfileActivity.this, MainPageActivity.class));

		overridePendingTransition(R.anim.pull_in_left,
				R.anim.push_out_right);
 	}

	 
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}
	
	private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if ( requestCode == GO_TO_GALLETY && resultCode == RESULT_OK && data != null)
		{
			// получаем адрес фотографии
			Uri _uri = data.getData();

			// User had pick an image.
			Cursor cursor = getContentResolver()
					.query(_uri,
							new String[] { android.provider.MediaStore.Images.ImageColumns.DATA },
							null, null, null);
			cursor.moveToFirst();

			// Link to the image
			final String imageFilePath = cursor.getString(0);

			cursor.close();
			
			addBitmap(imageFilePath);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	 ArrayList<byte[]> bitmaps = new ArrayList<byte[]>();
	private void addBitmap(String imageFilePath) 
	{
		Bitmap bmp = BitmapUtils.showImage(imageFilePath);
		BitmapFactory.decodeFile(imageFilePath);
		
		iv_photo.setImageBitmap(bmp);
		iv_photo.setVisibility(View.VISIBLE);
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		
		
		if ( bitmaps.isEmpty() ) {bitmaps.add(byteArray);}
		else
		{
			bitmaps.clear();
			bitmaps.add(byteArray);
		}
		
	
		
	}
	
	//----------------------------------------------------------------------------
	
	int DIALOG_DATE = 1;
	  int myYear = 1990;
	  int myMonth = 01;
	  int myDay = 01;
	
	protected Dialog onCreateDialog(int id) {
	      if (id == DIALOG_DATE) {
	        DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
	        return tpd;
	      }
	      return super.onCreateDialog(id);
	    }
	    
	    OnDateSetListener myCallBack = new OnDateSetListener() {

	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	        int dayOfMonth) {
	      myYear = year;
	      myMonth = monthOfYear;
	      myDay = dayOfMonth;
	      
	     // String dataString = myDay + "." + myMonth + "." + myYear;

	       date = new Date(myYear, myMonth, myDay);
	      
	      SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("dd. MMM");
	      StringBuilder dateTime = new StringBuilder( dateformatMMDDYYYY.format( date ) );
	      tvData.setText(dateTime + "." + myYear);
	    }
	    };
	    
	  //----------------------------------------------------------------------------
	
	
	
	

}
