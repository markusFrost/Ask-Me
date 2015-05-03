package com.example.netsocial_version_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class MainActivity extends Activity implements ActionBar.TabListener
{

	

	private static final String ATTRIBUTE_NAME = "name";
	private static final String ATTRIBUTE_DATA_TIME = "datatime";
	private static final String ATTRIBUTE_ANSWER = "answer";
	private static final String ATTRIBUTE_COUNT = "count";
	private static final String ATTRIBUTE_CONTENT = "content";
	
	private static final String ATTRIBUTE_AVATAR = "avatar";
	private static final String ATTRIBUTE_NOTIF= "notif";
	
	
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page2);
		
		
	//	startActivity(new Intent ( MainActivity.this, RegistrationActivity.class));
	//	startActivity(new Intent ( MainActivity.this, FriendsActivity.class));
		
		startActivity(new Intent ( MainActivity.this, MainPageActivity.class));
		
		//startActivity(new Intent ( MainActivity.this,  CreateQuizActivity.class));
		//startActivity(new Intent ( MainActivity.this,  UserPageActivity.class));
		//numb4();
		//numb5();
				
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	void numb5()
//	{
//		
//		ActionBar bar = getActionBar();
//
//	    bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//	    Tab tab = bar.newTab();
//	    tab.setText("tab1");
//	    tab.setTabListener(this);
//	    bar.addTab(tab);
//
//	    tab = bar.newTab();
//	    tab.setText("tab2");
//	    tab.setTabListener(this);
//	    bar.addTab(tab);
//	    
//		String name = "name";
//		String data_time = "data";
//		String answer = "answer : ";
//		String count = "" + 34;
//		String content = "Обычный текст...";
//		int img = R.drawable.right_arrow;
//		int avat = R.drawable.avatar;
//		int img_not = R.drawable.ic_notiv_new;
//
//		ListView lv = (ListView) findViewById(R.id.topic_det_listView);
//		
//		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
//				10);
//				Map<String, Object> m;
//				for (int i = 1; i <= 10; i++)
//				{
//					m = new HashMap<String, Object>();
//					m.put(ATTRIBUTE_NAME, name);
//					m.put(ATTRIBUTE_DATA_TIME, data_time);
//					m.put(ATTRIBUTE_ANSWER, answer);
//					m.put(ATTRIBUTE_COUNT, count);
//					m.put(ATTRIBUTE_CONTENT, content);
//					m.put(ATTRIBUTE_IMG, img);
//					m.put(ATTRIBUTE_AVATAR, avat);
//					m.put(ATTRIBUTE_NOTIF, img_not);
//					
//					data.add(m);
//					
//				}
//				
//				String [] from = {ATTRIBUTE_NAME, ATTRIBUTE_DATA_TIME, ATTRIBUTE_ANSWER, ATTRIBUTE_COUNT,
//						ATTRIBUTE_CONTENT, ATTRIBUTE_NOTIF, ATTRIBUTE_AVATAR };
//						
//		        int [] to =   {R.id.topic_details_tv_name,R.id.topic_details_tv_data,
//						R.id.topic_details_tv_answers, R.id.topic_details_answers_count,
//						R.id.topic_details_tv_content, R.id.topic_details_iv_new_notivic,
//						R.id.topic_details_iv_man
//				};
//		        
//		        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.topic_details_listview_row,
//						from, to);
//				
//				lv.setAdapter(sAdapter);
//						
//
//	}
//	
	void numb4()
	{
		
		
	}



	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
