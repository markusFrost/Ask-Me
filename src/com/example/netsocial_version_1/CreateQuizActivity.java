package com.example.netsocial_version_1;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netsocial.helps_activities.ChoseTopicActivity;
import com.example.netsocial.helps_activities.ImageBitmaps;
import com.example.netsocial.helps_activities.Message;
import com.example.netsocial.helps_activities.Message.onMsgClickOk;
import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.objects.Topic;
import com.example.netsocial.objects.TopicDto;
import com.example.netsocial.questions.QuestionDto;
import com.example.netsocial.questions.QuestionPostPacket;
import com.example.netsocial.utils.BitmapUtils;
import com.example.netsocial.utils.BsonUtils;

public class CreateQuizActivity extends Activity implements onMsgClickOk
{
	
	protected static final int CREATE_QUIZ_ACTIVITY_GET_TOPIC = 1;
	private static final int GO_TO_GALLETY = 2;
	protected static final int CREATE_QUIZ_ACTIVITY_GET_ASK_CATEGORY = 2;
	static Cookie cookie;
	
	ArrayList<Topic> topics;
	EditText editAbout, editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_question);
		 topics = ((AppContext) this.getApplicationContext()).getTopics();
		if ( cookie == null) 
			{
			   cookie = ( (AppContext) this.getApplicationContext()).getCokie();
			}
// 
		fillViews();

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	TextView tv_chosed_topic;
	RelativeLayout rl_chose_topic, rl_chose_ask;
	
	private void fillViews()
	{
		btnAdd = (Button) findViewById(R.id.create_question_btn_AddPhotos);
		
		editAbout = (EditText) findViewById(R.id.create_question_edit_about);
		 editText = (EditText) findViewById(R.id.create_quiz_clear_edit_ask_sms);
		 rl_chose_topic = (RelativeLayout) findViewById(R.id.create_question_rl_chose_topic);
			
			rl_chose_topic.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) 
				{
					startActivityForResult(new Intent (
							CreateQuizActivity.this, ChoseTopicActivity.class), 
							CREATE_QUIZ_ACTIVITY_GET_TOPIC);
					overridePendingTransition(R.anim.pull_in_right,
							R.anim.push_out_left);
				}
			});
			
			rl_chose_ask = (RelativeLayout) findViewById(R.id.create_question_rl_ask_chose_category);
		
			rl_chose_ask.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v) 
				{
					startActivityForResult(new Intent (
							CreateQuizActivity.this, AskCreateQuestionDetailsActivity.class), 
							CREATE_QUIZ_ACTIVITY_GET_ASK_CATEGORY);
					overridePendingTransition(R.anim.pull_in_right,
							R.anim.push_out_left);
					
				}
			});
		
		tv_chosed_topic = (TextView) findViewById(R.id.create_question_chose_topic_tv_chosed_topic);
	
	    ImageView iv = (ImageView) findViewById(R.id.create_question_imageView1);
	    imageViews.add(iv);
	    
	     iv = (ImageView) findViewById(R.id.create_question_imageView2);
	    imageViews.add(iv);
	    
	     iv = (ImageView) findViewById(R.id.create_question_imageView3);
	    
	    imageViews.add(iv);
	    
	    iv = (ImageView) findViewById(R.id.create_question_imageView4);
	    imageViews.add(iv);
	    
	     iv = (ImageView) findViewById(R.id.create_question_imageView5);
	    imageViews.add(iv);
	    
	     iv = (ImageView) findViewById(R.id.create_question_imageView6);
	    imageViews.add(iv);
	    
	    for (int i = 0; i < imageViews.size(); i++)
	    {
	    	imageViews.get(i).setOnLongClickListener( new onImageViewLongClick());
	    }
	}
	
	int numb;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if ( requestCode == CREATE_QUIZ_ACTIVITY_GET_TOPIC && resultCode == RESULT_OK && data != null)
		{
			  numb = data.getIntExtra(ChoseTopicActivity.CHOSE_TOPIC, 0);
			  tv_chosed_topic.setText(topics.get(numb).getName());
			
			
			
		}
		else if ( requestCode == GO_TO_GALLETY && resultCode == RESULT_OK)
		{
			if (data != null && resultCode != RESULT_CANCELED
					&& data.getData() != null) 
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
				
				addBitmaps(imageFilePath);

			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	


	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_quiz, menu);
		return true;
	}
	
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	// TODO Auto-generated method stub
    	
    	switch (item.getItemId())
    	{
    	   case android.R.id.home:
    	    {
    	    	finish();
    	    	overridePendingTransition(R.anim.pull_in_left,
    					R.anim.push_out_right);
    		     return true;
    	    }
    	   case R.id.menu_create_quiz_ok:
   	       {
   	    	 createQuestionAndPostToServer();
   		     return true;
   	       }
   	   
    	}
    	
    	return true;
    	
    }
	
	
	private void createQuestionAndPostToServer()
	{

		TopicDto td = new TopicDto();
		
		td.setId(topics.get(numb).getId());
		td.setName(topics.get(numb).getName());

		QuestionDto qd = new QuestionDto();
		qd.setId(null);
		qd.setCreatorId(null);
		qd.setTheme(editAbout.getText().toString());
		qd.setText(editText.getText().toString());
		qd.setTopic(td);
		qd.setLocation(null);
		qd.setLikes(0);
		qd.setAnswersCount(0);
		qd.setDataCreation(null);

		qd.setUsersTo(null);
		qd.setToAll(true);

		QuestionPostPacket p = new QuestionPostPacket();

		p.setQuest(qd);
		p.setImgs(bitmaps);
		
		
		new HttpAsyncTask(getHttpClient(), cookie, BsonUtils.serialize(p)).execute(AppContext.QUIZ_ACTIVITY_URL);
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
			          this.dialog = new ProgressDialog(CreateQuizActivity.this);

			        	  this.dialog.setMessage("Отправка вопроса");
			          
			          
			          if (!this.dialog.isShowing()) {
							this.dialog.show();
						}
					
				}
	        @Override
	        protected RequestState doInBackground(String... urls) {

	        		 return BsonUtils.POST(httpClient, urls[0], cok, arr);

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
	 
	 public void onClick (View v)
	 {
		 switch ( v.getId())
		 {
		     case R.id.create_question_btn_AddPhotos:
		       {
		    	   if ( bitmaps.size() < 6)
		    	   {
		    		 
		    	   final Intent galleryIntent = new Intent(
				            Intent.ACTION_GET_CONTENT);

		            galleryIntent.setType("*/*");
		            startActivityForResult(galleryIntent, GO_TO_GALLETY);
		    	   }
		    	   
		       }break;
		       
		     case R.id.create_question_clear_about:
		     {
		    	 editAbout.setText("");
		     }break;
		     
		     case R.id.create_quiz_clear_ask_sms:
		     {
		    	 editText.setText("");
		     }break;
		 }
		 

	 }
	 
	 ArrayList<byte[]> bitmaps = new ArrayList<byte[]>();
	 ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
	 private void addBitmaps(String imageFilePath) 
	 {
		Bitmap bmp = BitmapUtils.showImage(imageFilePath);
				BitmapFactory.decodeFile(imageFilePath);
		 
		 
		
		imageViews.get(bitmaps.size()).setVisibility(View.VISIBLE);
		imageViews.get(bitmaps.size()).setImageBitmap(bmp);
		
		

		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		
		
		
	   
		
		bitmaps.add(byteArray);
		
		if ( bitmaps.size() == 6)
		{
			btnAdd.setVisibility(View.GONE);
		}
		
			
	  }
	 int position = 0;
	 
	 private class onImageViewLongClick implements ImageView.OnLongClickListener
	 {

		@Override
		public boolean onLongClick(View v) 
		{
			 position = BitmapUtils.getViewPosition(v, imageViews);
			
			System.out.println("onlongClick");
			PopupMenu popup = new PopupMenu(CreateQuizActivity.this, v); 
			getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
			
			 popup.setOnMenuItemClickListener( new OnMenuItemClickListener() {
				 
				
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{
					// TODO Auto-generated method stub
					
				 	Message.alert_msg(CreateQuizActivity.this, 
							getResources().getString(R.string.str_ad_delete_title),
							getResources().getString(R.string.str_ad_delete_message));

					return true;
				}
			});
			 
			 popup.show();//showing popup menu
			return true;
		}
		 
	 }
	 
	 Button btnAdd;

	@Override
	public void onClickOk() 
	{
		
		btnAdd.setVisibility(View.VISIBLE);
		System.out.println("pos = " + position);
	
		bitmaps.remove(position);
		
		if ( position == 0 && BitmapUtils.getLastIngexOfVisibleImageView(imageViews) == 0 )
		{
			imageViews.get(0).setVisibility(View.GONE);
		}
		else
		{

		updateImageViewsList();
		}
		
		System.out.println("iv count = " + BitmapUtils.getLastIngexOfVisibleImageView(imageViews) );
	
		System.out.println("bitmap count = " + bitmaps.size());
	
	}
	
	private void updateImageViewsList()
	 {
		 
		 if ( position < imageViews.size() - 1)
		 {
			 
			 int length = BitmapUtils.getLastIngexOfVisibleImageView(imageViews);
			 for ( int i = (position + 1) ; i <= length; i++)
			 {
				 imageViews.get(i - 1).setImageBitmap(
						 ((BitmapDrawable)imageViews.get(i).getDrawable()).getBitmap()
						 );
				 imageViews.get(i).setVisibility(View.GONE);
				 imageViews.get(i - 1).setVisibility(View.VISIBLE);
			 }
		 }

	 }
	
	private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}

	
	
	
	public void onImageClick (View v)
	{
		
		System.out.println("onImageClick");
		ImageView iv = (ImageView) v;
		try
		{
		 int index = Integer.parseInt(iv.getContentDescription().toString());
		 
		 ImageBitmaps ib = new ImageBitmaps(index, bitmaps);
		 
		 Intent intent = new Intent(CreateQuizActivity.this, ShowImagesActivity.class);
		 
		 intent.putExtra("ImageBitmaps", ib);
		 
		 startActivity(intent);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	 



	 

}
