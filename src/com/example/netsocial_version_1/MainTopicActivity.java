package com.example.netsocial_version_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Type;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.objects.Topic;
import com.example.netsocial.questions.AnswerDto;
import com.example.netsocial.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainTopicActivity extends Activity 
{
	
	private static final String ATTRIBUTE_TEXT = "at_text";
	private static final String ATTRIBUTE_NOTIV = "at_notiv";
	private static final String ATTRIBUTE_IMG = "at_img";


	 Cookie cookie;
	
	 ArrayList<Topic> topics;
	
	Button btnNew, btnAnswer, btnPopular;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		topics = new ArrayList<Topic>();
		
		if ( cookie == null) 
			{
			  cookie = ( (AppContext) this.getApplicationContext()).getCokie();
			}
		
		
		if ( topics.isEmpty())
		{
			new HttpAsyncTask(getHttpClient(), cookie).execute(AppContext.TOPIC_ACTIVITY_URI);
		}
		else
		{
			fillListView();
		}
		
		
		
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		
		
	}
	
	TextView tv_topics_count;

	private void fillViews() 
	{
		tv_topics_count = (TextView) findViewById(R.id.topic_tv_text_all);
		tv_topics_count.setText("All " + (topics.size() + 1) + " topics");
		
	}
	
	
	
	public void onClick(View v)
	{
		//Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		//startActivityForResult(cameraIntent, 1); 
		//startActivity(new Intent ( MainTopicActivity.this,  CreateQuizActivity.class));
		startActivity(new Intent ( MainTopicActivity.this,  UserPageActivity.class));
		//startActivity(new Intent ( MainTopicActivity.this,  UserProfileActivity.class));
		

		overridePendingTransition(R.anim.pull_in_right,
				R.anim.push_out_left);
	
	}
	
		
		
	
	
	

	private void fillListView() 
	{
		
		btnAnswer = (Button) findViewById(R.id.topic_btn_answer);
		btnNew = (Button) findViewById(R.id.topic_btn_new);
		btnPopular = (Button) findViewById(R.id.topic_btn_popular);
		
		ArrayList<String> texts = new ArrayList<String>();
		texts.add( "Friends Asking");
		for ( Topic t : topics)
		{
			texts.add(t.getName());
		}
		
		int img_arr = R.drawable.right_arrow;
		int img_new = R.drawable.ic_notiv_new;
		
	
		
		
		ListView lv = (ListView) findViewById(R.id.topic_listView);
		
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
				texts.size());
				Map<String, Object> m;

				for (int i = 0; i < texts.size(); i++) {
				m = new HashMap<String, Object>();
				m.put(ATTRIBUTE_TEXT, texts.get(i));
				m.put(ATTRIBUTE_NOTIV, img_new);
				m.put(ATTRIBUTE_IMG, img_arr);
				data.add(m);
				}
				
				String [] from = {ATTRIBUTE_TEXT, ATTRIBUTE_NOTIV, ATTRIBUTE_IMG};
				int [] to = {R.id.topic_tv_name, R.id.topic_iv_notivication, R.id.topic_iv_arrow};
				SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.topic_listview_row1,
						from, to);
				
				lv.setAdapter(sAdapter);
				
				lv.setOnItemClickListener(new OnItemClickListener() 
				{

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int pos, long arg3) 
					{
						pos--;

						Intent intent =  new  Intent(MainTopicActivity.this, TopicDetailsActivity.class); 

						intent.putExtra(AppContext.TOPIC_DETAILS_ACTIVITY, topics.get(pos));
						startActivity(intent);
						
						overridePendingTransition(R.anim.pull_in_right,
								R.anim.push_out_left);
					}
					
				});
			
				fillViews();
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_topic, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		switch (item.getItemId()) {
		case android.R.id.home: {
			onBackPressed();

		}
			break;
		}
		return true;
	}
	
	@Override
	public void onBackPressed()
	{
	     moveTaskToBack(true);
	}
	
	
	 private class HttpAsyncTask extends AsyncTask<String, Void, RequestState> 
	 {

		 Cookie cok;
		 DefaultHttpClient httpClient;

		 public HttpAsyncTask(DefaultHttpClient httpClient, Cookie cok)
		 {

			 this.httpClient = httpClient;
			 this.cok = cok;
		 }

		 private ProgressDialog dialog;

		 @Override
				protected void onPreExecute() 
		           {
			          this.dialog = new ProgressDialog(MainTopicActivity.this);

			          this.dialog.setMessage("Загрузка топиков");

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
				
				saveTopics(json);
				
	
               
//				try 
//				{
//					
//
					//saveTopics( JsonUtil.fromJson((JSONArray) rs.getObject() , AppContext.TOPIC_ACTIVITY_ACTION ));
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
	 
	 public void saveTopics(String json) 
		{
		 topics = new Gson().fromJson(json, 
					new TypeToken<ArrayList<Topic>>() {
			              }.getType()
					);

			               ((AppContext)this.getApplicationContext()).setTopics(topics);
			               System.out.println(topics.get(0).getId());
			      		 
			      		 fillListView();
			               
			              

			
		}
	 
	 private DefaultHttpClient getHttpClient()
		{
			return ((AppContext) this.getApplicationContext()).getHttpClient();
		}
	 
	 
	
	 
	 
	 


}
