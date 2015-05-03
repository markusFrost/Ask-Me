package com.example.netsocial_version_1;

import java.util.ArrayList;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.netsocial.helps_activities.TodoAdapter;
import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.objects.Topic;
import com.example.netsocial.questions.QuestionDto;
import com.example.netsocial.utils.HttpUtils;
import com.example.netsocial.utils.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TopicDetailsActivity extends Activity
{
	
	 Cookie cookie;
	
	ArrayList<QuestionDto> qd = new ArrayList<QuestionDto>();
	
	Topic topic;
	
	LinearLayout ll_Main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic_details);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		
		topic = (Topic) intent.getExtras().get(AppContext.TOPIC_DETAILS_ACTIVITY);
		
		TextView tv = (TextView) findViewById(R.id.create_question_tv_topic_name);
		tv.setText(topic.getName());
		
		//System.out.println("topic_name = " + topic.getName());
		
         if ( cookie == null)
         {
        	 cookie = ( (AppContext) this.getApplicationContext()).getCokie();
         }
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.topic_details, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		finish();
		overridePendingTransition(R.anim.pull_in_left,
				R.anim.push_out_right);
		return true;
	}
	
	 private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {

		 Cookie cok;
		 DefaultHttpClient httpClient;

		 public HttpAsyncTask(DefaultHttpClient httpClient, Cookie cok)
		 {

			 this.cok = cok;
			 this.httpClient = httpClient;
		 }

		 private ProgressDialog dialog;

		 @Override
				protected void onPreExecute() 
		           {
			          this.dialog = new ProgressDialog(TopicDetailsActivity.this);

			          this.dialog.setMessage("Загрузка вопросов");

			          if (!this.dialog.isShowing()) {
							this.dialog.show();
						}
					
				}
	        @Override
	        protected RequestState doInBackground(String... urls) {

	        	 return HttpUtils.GET1(httpClient, urls[0], cok);
	        	

	 
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
		protected void onPostExecute(RequestState rs) {

			if (rs != null && rs.getRequest_code() == 200)
			{
				
				String json = (String) rs.getObject();
				
				
				saveQuestions(json);
//
//				try 
//				{
//
//					saveQuestions( JsonUtil.fromJson((JSONArray) rs.getObject(),AppContext.TOPIC_DETAILS_ACTIVITY_ACTION));
//					
//				} catch (JSONException e) 
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

//				Toast.makeText(getBaseContext(), "Все норм", Toast.LENGTH_SHORT)
//						.show();

			} else 
			{
//				Toast.makeText(
//						getBaseContext(),
//						"Ошибка : " + rs.getRequest_code()
//								+ ". Не удалось авторизоваться!",
//						Toast.LENGTH_LONG).show();
			}

			this.dialog.dismiss();

		}

	}
	 
	 
	 private void saveQuestions(String json) 
		{
		 qd = new Gson().fromJson(json, 
					new TypeToken<ArrayList<QuestionDto>>() {
			              }.getType()
					);

			              
			               System.out.println("data = " + qd.get(0).getDataCreation());
			      		 
			               fillViews();
			               
			              

			
		}
	 private void saveQuestions(ArrayList<Object> arrayList) 
		{
			
		 //System.out.println("questions_count = " +arrayList.size());
		 for (Object obj : arrayList)
		 {
			 qd.add((QuestionDto) obj);
		 }
		 
		 System.out.println("questions_count = " +qd.size());
		 fillViews();

		}
	 
	 ListView lv;
	 Button btnNew, btnAnswer, btnPopular;

	private void fillViews() 
	{
		
		btnAnswer = (Button) findViewById(R.id.topic_btn_answer);
		btnNew = (Button) findViewById(R.id.topic_btn_new);
		btnPopular = (Button) findViewById(R.id.topic_btn_popular);
		lv = (ListView) findViewById(R.id.topic_det_listView);
		
		lv.setOnItemClickListener(new OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int pos, long arg3) 
					{
						// TODO Auto-generated method stub
						
//						System.out.println("gd ID = " + qd.get(pos).getId());
//						System.out.println("Topic Name = " + topic.getName());
						
						String id = qd.get(pos).getId();
						String theme = qd.get(pos).getTheme();
						String text = qd.get(pos).getText();
						String creatorLogin = qd.get(pos).getCreatorLogin();
						int like = qd.get(pos).getLikes();
						
						QuestionDto questionDto = new QuestionDto();
						questionDto.setId(id);
						questionDto.setTheme(theme);
						questionDto.setText(text);
						questionDto.setCreatorLogin(creatorLogin);
						questionDto.setLikes(like);
						questionDto.setCreatorId(qd.get(pos).getCreatorId());
						
						Intent intent = new Intent(TopicDetailsActivity.this, Quiz_Page_Activity.class);
						intent.putExtra(Quiz_Page_Activity.QUESTION_DTO_INFO, questionDto);
						intent.putExtra(Quiz_Page_Activity.TOPIC_THEME, topic.getName());
						
						startActivity(intent);
						
						overridePendingTransition(R.anim.pull_in_right,
								R.anim.push_out_left);
						
						
					}
			
		        });
		TodoAdapter ta = new TodoAdapter(getApplicationContext(), qd);
		lv.setAdapter(ta);
						

	}
	
   public void onClick(View v)
   {
	   startActivity(new Intent(TopicDetailsActivity.this, CreateQuizActivity.class));
   }
	
   
   private DefaultHttpClient getHttpClient()
	{
		return ((AppContext) this.getApplicationContext()).getHttpClient();
	}
   
   @Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	
	
	qd.clear();
	
	String url = AppContext.TOPIC_DETAILS_ACTIVITY_URI + topic.getId();
    System.out.println(url);
	new HttpAsyncTask(getHttpClient(), cookie).execute(url);
}
	
   public void onRBViewClick(View v)
	 {
		 switch ( v.getId())
		 {
		 case R.id.topic_btn_new:
		 {
			 btnNew.setTextColor(getResources().getColor(R.color.color_show));
			 btnNew.setBackgroundColor(getResources().getColor(R.color.colors_dark_red));
			 
			 btnAnswer.setTextColor(getResources().getColor(R.color.color_black));
			 btnAnswer.setBackgroundColor(getResources().getColor(R.color.color_show));
			 
			 btnPopular.setTextColor(getResources().getColor(R.color.color_black));
			 btnPopular.setBackgroundColor(getResources().getColor(R.color.color_show));
		 }break;
		 case R.id.topic_btn_answer:
		 {
			 btnAnswer.setTextColor(getResources().getColor(R.color.color_show));
			 btnAnswer.setBackgroundColor(getResources().getColor(R.color.colors_dark_red));
			 
			 btnPopular.setTextColor(getResources().getColor(R.color.color_black));
			 btnPopular.setBackgroundColor(getResources().getColor(R.color.color_show));
			 
			 btnNew.setTextColor(getResources().getColor(R.color.color_black));
			 btnNew.setBackgroundColor(getResources().getColor(R.color.color_show));
			 
		 }break;
		 case R.id.topic_btn_popular:
		 {
			 btnPopular.setTextColor(getResources().getColor(R.color.color_show));
			 btnPopular.setBackgroundColor(getResources().getColor(R.color.colors_dark_red));
			 
			 btnNew.setTextColor(getResources().getColor(R.color.color_black));
			 btnNew.setBackgroundColor(getResources().getColor(R.color.color_show));
			 
			 btnAnswer.setTextColor(getResources().getColor(R.color.color_black));
			 btnAnswer.setBackgroundColor(getResources().getColor(R.color.color_show));
		 }break;
		 }
	 }
		
	

}
