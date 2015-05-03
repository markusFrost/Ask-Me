package com.example.netsocial_version_1;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.netsocial.helps_activities.AnswerAdapter;
import com.example.netsocial.helps_activities.ImageBitmaps;
import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.objects.UserPage;
import com.example.netsocial.questions.AnswerDto;
import com.example.netsocial.questions.QuestionDto;
import com.example.netsocial.utils.BitmapUtils;
import com.example.netsocial.utils.BsonUtils;
import com.example.netsocial.utils.HttpUtils;
import com.example.netsocial.utils.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Quiz_Page_Activity extends Activity
{
	
	public static final String QUESTION_DTO_INFO = "question_dto_info";
	public static final String TOPIC_THEME = "topic_theme";
	
	 Cookie cookie;
	TextView tvData, tvTopicName, tvTheme, tvText, tvName;
	QuestionDto qd;
	String topic_name;
	
	ScrollView scrollView;

	RelativeLayout rl;
	
	ImageView iv_like;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz__page_);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		rl = (RelativeLayout) findViewById(R.id.quiz_page_rl1);
		
		iv_like = (ImageView) findViewById(R.id.user_page_iv_avatar);
		
		scrollView = (ScrollView) findViewById(R.id.create_question_scrollView1);
		
		if (cookie == null)
			{
			   cookie = ( (AppContext) this.getApplicationContext()).getCokie();
			}
		
		 Intent intent = getIntent();
		 qd = (QuestionDto) intent.getExtras().get(QUESTION_DTO_INFO);
	 topic_name = intent.getStringExtra(TOPIC_THEME);
		
//		System.out.println(qd.getDaraCreation());
//		System.out.println(qd.getTheme());
//		System.out.println(qd.getText());
	//	System.out.println(qd.getId());
		
		//fillViews();
		
		
		
		String url = AppContext.QUIZ_PAGE_ACTIVITY_URL + qd.getId();
		new HttpAsyncTask(getHttpClient(), cookie).execute(url);
	 
	// Здесь начинаем грузить фотки
	 
	// fillImageViews();
	 
		// url = "http://103.16.228.174:33232/api/image?q_id=" +qd.getId() + "&index=" + "0";
	//	new HttpAsyncTask1(getHttpClient(), cookie).execute(url);
	}
	
	private void fillViews()
	{

		rl.setVisibility(View.VISIBLE);
		
		
		tvData = (TextView) findViewById(R.id.quiz_page_tv_data);
		tvTopicName = (TextView) findViewById(R.id.quiz_page_tv_topic_name);
		
		tvTheme = (TextView) findViewById(R.id.quiz_page_tv_theme);
		tvText = (TextView) findViewById(R.id.quiz_page_tv_text);
		
		tvName = (TextView) findViewById(R.id.quiz_page_tv_name);
		

		tvName.setOnClickListener(new onTvClick());
		
		
		
		tv_like_count = (TextView) findViewById(R.id.quiz_page_tv_like_count);
		
		tv_like_count.setText(qd.getLikes() + "");
		// считать эту хрень
		
	//	tvData.setText(qd.getStringDataCreation()); //*****************************
		tvTopicName.setText(topic_name);
		tvTheme.setText(qd.getTheme());
		tvText.setText(qd.getText());
		tvName.setText(qd.getCreatorLogin());
		
		String myId = getUserID();
		String userId = qd.getCreatorId();
		
		System.out.println("myId = " + myId);
		System.out.println("userId = " + userId);
		
		if ( myId.equals(userId))
		{
			//System.out.println("here");
			Button btn = (Button) findViewById(R.id.quiz_page_btn_answer);
			btn.setText(R.string.str_btn_Close);
			
		}
		
		
		
		
		
		// ************************************************************
//				
		
		fillListView();
	}
	
	onImageViewClick onImageViewClick = new onImageViewClick();
	AnswerAdapter answerAdapter;
	ListView lv;
	private void fillListView() 
	{
		lv = (ListView) findViewById(R.id.quiz_page_list_view);
		
		
		
		 answerAdapter = new AnswerAdapter(getApplicationContext(), adt, onImageViewClick);
		
		lv.setAdapter(answerAdapter);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz__page_, menu);
		return true;
	}
	
	
	EditText edit;
	public void onClick (View v)
	{
		switch ( v.getId())
		{
		    case R.id.quiz_page_btn_answer:
		       {
		    	        String answer = this.getResources().getString(R.string.str_btn_answer);
		    	       
		    	        String btnName = ((Button) v).getText().toString();
		    	   
		    	        if ( btnName == answer)
		    	        {
			                createAlertDialog();
		    	        }
		    	        else
		    	        {
		    	        	finish();
		    	    		overridePendingTransition(R.anim.pull_in_left,
		    	    				R.anim.push_out_right);
		    	        }
		       }break;
		}
	}

	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		finish();
		overridePendingTransition(R.anim.pull_in_left,
				R.anim.push_out_right);
		return true;
	}
	
	EditText editAnswer;
	private void createAlertDialog()
	{
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle(R.string.str_edit_answer);
		
		 editAnswer = new EditText(getApplicationContext());
		editAnswer.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		editAnswer.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_show));
		editAnswer.setTextColor(getApplicationContext().getResources().getColor(R.color.color_black));		
		adb.setView(editAnswer);
		
		adb.setPositiveButton(android.R.string.ok, myOnClickListener);
		adb.setNegativeButton(android.R.string.cancel,  myOnClickListener);
		
		adb.show();

	}
	
    OnClickListener myOnClickListener = new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case Dialog.BUTTON_POSITIVE: 
			{
		
				
				
				AnswerDto adt = new AnswerDto();
				adt.setQuestionId(qd.getId());
				adt.setText(editAnswer.getText().toString());
				//System.out.println("id = " + adt.getQuestionId());
				//System.out.println("text = " + adt.getText());
				
				
				Gson gson = new Gson();
				
				String json = gson.toJson(adt);
				
				byte[] data =json.getBytes(Charset.forName("UTF-8"));
				
				String url = AppContext.QUIZ_PAGE_ACTIVITY_ANSWER_URL;
				
				new HttpAsyncTask(getHttpClient(), cookie, data).execute(url);
	
			}
				break;
			case Dialog.BUTTON_NEGATIVE: {
				
			}
				break;

			}

		}
	};


	 private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {

		 Cookie cok;

		 DefaultHttpClient httpClient;
		 int numb = 0;

		private byte[] json = null;
		

		 public HttpAsyncTask(DefaultHttpClient httpClient, Cookie cok )
		 {

			 this.httpClient = httpClient;
			 this.cok = cok;
			
		 }
		 

		 public HttpAsyncTask(DefaultHttpClient httpClient, Cookie cok, int numb )
		 {

			 this.httpClient = httpClient;
			 this.cok = cok;
			 this.numb = numb;
			
		 }

		 
		 
		 
		 public HttpAsyncTask(DefaultHttpClient httpClient,Cookie cok, byte[] json )
		 {

			 this.cok = cok;
			 this.json  = json;
			 this.httpClient = httpClient;
		 }
		 
		

		 private ProgressDialog dialog;

		 @Override
				protected void onPreExecute() 
		           {
if ( numb == 0)
{
			          this.dialog = new ProgressDialog(Quiz_Page_Activity.this);

			          if ( json == null)
			          {
			              this.dialog.setMessage("Загрузка вопросa");
			          } else 
			          {
			        	  this.dialog.setMessage("Отправка ответa");
			          }
			        

			          if (!this.dialog.isShowing()) {
							this.dialog.show();
						}
}
			 
			          
					
				}
	        @Override
	        protected RequestState doInBackground(String... urls) {

	        	if ( json == null )
	        	{
	        	 return HttpUtils.GET1(httpClient, urls[0], cok);
	        	}
	        	else 
	        	{
	        		return HttpUtils.POST(httpClient, urls[0], cok, json);
	        	}
	        	
	        	
	        	

	 
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
		protected void onPostExecute(RequestState rs) {

			if (rs != null && rs.getRequest_code() == 200)
			{

				if ( json == null  && numb == 0 )
				{
				
					
                   System.out.println("Here");
                   String json = (String) rs.getObject();
                		   
                   saveAnswers (json);

				}
				
				
				if ( numb == 1 )
				{
					updateListView();
//					Toast.makeText(getBaseContext(), "Лайк поставлен!", Toast.LENGTH_SHORT)
//					.show();
				}
				else if ( numb == 0)
				{

//					Toast.makeText(getBaseContext(), "Все норм", Toast.LENGTH_SHORT)
//							.show();
				}
				
				
				if ( json != null)
				{
					String json = (String) rs.getObject();
					saveAnswers(json);

				}
				
				


			} else 
			{
//				Toast.makeText(
//						getBaseContext(),
//						"Ошибка : " + rs.getRequest_code()
//								+ ". Не удалось авторизоваться!",
//						Toast.LENGTH_LONG).show();
			}

	if ( numb == 0) {	this.dialog.dismiss(); }

		}

	}
	 
	 
ArrayList<AnswerDto> adt = new ArrayList<AnswerDto>();;


	
	

	public void saveAnswersAfter(String json)
	{
		adt.clear();
		adt = new Gson().fromJson(json, 
				new TypeToken<ArrayList<AnswerDto>>() {
		              }.getType()
				);
		System.out.println("update adt count = " + adt.size());
		fillViews();
		
	}

	public void saveAnswers(String json) 
	{
		
		try
		{
		JSONObject j = new JSONObject(json);
		
		JSONArray array = j.getJSONArray("Answers");
		
		String jsonArrayStr = array.toString();
		
		
		
		adt = new Gson().fromJson(jsonArrayStr, 
				new TypeToken<ArrayList<AnswerDto>>() {
		              }.getType()
				);
	
		} catch ( Exception e)
		{
			
		}
		//adt.clear();
		
		
		//System.out.println(json);
		fillViews();
		
		
	}

	
	
	private void updateListView() 
	{
		
       answerAdapter = new AnswerAdapter(getApplicationContext(), adt, onImageViewClick, answer_lv_position);
		
		lv.setAdapter(answerAdapter);
		lv.setSelection(answer_lv_position);
		
	}

	int count = 0;
	
	ArrayList<byte[]> bitmaps_bytes = new ArrayList<byte[]>();
	
	private void saveBitmap(byte[] byteArray, int req_code) {
		if (req_code != 404) 
		{

			if (count == 0 ) {
				scrollView.setVisibility(View.VISIBLE);
			}

			bitmaps_bytes.add(byteArray);
			Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0,
					byteArray.length);

			int index = BitmapUtils.getLastIngexOfVisibleImageView(imageViews) + 1;

			imageViews.get(index).setImageBitmap(bmp);
			imageViews.get(index).setVisibility(View.VISIBLE);
			count++;

			if (count <= 6) {
				String url = "http://103.16.228.174:33232/api/image?q_id="
						+ qd.getId() + "&index=" + count;
				new HttpAsyncTask1(getHttpClient(), cookie).execute(url);
			}
		}
		else
		{
			//Toast.makeText(getApplicationContext(), "No more!", Toast.LENGTH_SHORT).show();
		}

	}
	
	int [] array = {R.id.create_question_imageView_1,
			R.id.create_question_imageView2,
			R.id.create_question_imageView3,
			R.id.create_question_imageView4,
			R.id.create_question_imageView5,
			R.id.create_question_imageView_6

	};
	
	ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
	private void fillImageViews()
	{
		for ( int id : array)
		{
			
			imageViews.add(
					(ImageView) findViewById(id)
					);
		}
	}

	private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}
	
	
	 private class HttpAsyncTask1 extends AsyncTask<String, Void, RequestState> 
	 {

	
		 Cookie cok;
		
		 DefaultHttpClient httpClient;
		 

		 public HttpAsyncTask1( DefaultHttpClient httpClient, Cookie cok)
		 {
			this.httpClient = httpClient;
			 this.cok = cok;
			
		 }

	        @Override
	        protected RequestState doInBackground(String... urls) {

	        	
	        	
	        		// return HttpUtils.GET(urls[0], cok);
	        	return BsonUtils.GET(httpClient, urls[0], cok);
	        	

	 
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(RequestState rs) 
	        {
	            
	            if (rs != null )
	            {

	            	saveBitmap((byte [] )rs.getObject() , rs.getRequest_code());
	            		//Toast.makeText(getBaseContext(),"Отправка прошла успешно", Toast.LENGTH_SHORT).show();
	
	            }
	          

	        }

			
	    }
	 
	 
	 
	 public void onImageClick(View v)
	 {
		 ImageView iv = (ImageView) v;
		 int index = Integer.parseInt(iv.getContentDescription().toString());
		 
		 ImageBitmaps ib = new ImageBitmaps(index, bitmaps_bytes);
		 
		 Intent intent = new Intent(Quiz_Page_Activity.this, ShowImagesActivity.class);
		 
		 intent.putExtra("ImageBitmaps", ib);
		 
		 startActivity(intent);
	 }
	 
	 int answer_lv_position = -1;
	 
	 private class onImageViewClick implements View.OnClickListener 
	 {

		@Override
		public void onClick(View v) 
		{
			ImageView iv = (ImageView) v;
			
			AnswerDto adt = (AnswerDto) iv.getTag();
			answer_lv_position = adt.getId() - 1;
			
			int answer_id = adt.getId();
			String question_id = qd.getId();
			
			String url = AnswerDto.URL_SET_LIKE_1  + question_id + 
					AnswerDto.URL_SET_LIKE_2 + answer_id;
			
			System.out.println("url = " + url);
			
			new HttpAsyncTask(getHttpClient(),cookie,1).execute(url);
			
		}
		 
	 }
	 
	 TextView tv_like_count;
	 boolean fl = true; // ещё ни разу не нажимали
	 
	 public void onQuestionImageViewClick (View v)
	 {
		 if ( fl)
		 {
			 int like_count = Integer.parseInt(tv_like_count.getText().toString());
			 tv_like_count.setText(++like_count + "");
			 
			 String url = "http://103.16.228.174:33232/api/questions/" + qd.getId() + "/like";
			 new HttpAsyncTask(getHttpClient(),cookie,2).execute(url);
			 fl = false;
		 }
	 }
	 
	 private String getUserID()
	 {
		 return ((AppContext)this.getApplicationContext()).getUserId();
	
	 }

	 
	 private class onTvClick implements TextView.OnClickListener
	 {

		@Override
		public void onClick(View v) 
		{
			System.out.println("onTVClick");
			 Intent intent = new Intent(Quiz_Page_Activity.this, UserPageActivity.class);
			 
			 intent.putExtra(UserPageActivity.ID, qd.getCreatorId());
			 
			 startActivity(intent);
			
		}
		 
	 }

	 

}
