package com.example.netsocial.helps_activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.Topic;
import com.example.netsocial_version_1.R;

public class ChoseTopicActivity extends Activity 
{
	ListView lv;

	public static final String CHOSE_TOPIC = "chose_topic";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_topic);
		
		ArrayList<Topic> topics = ((AppContext) this.getApplicationContext()).getTopics();
		
		ArrayList<String> topics_name = new ArrayList<String>();
		
		for (Topic t : topics)
		{
			topics_name.add(t.getName());
		}
		
		lv = (ListView) findViewById(R.id.chose_topic_listView);
		lv.setOnItemClickListener(new OnListViewItemClickListner());
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.chose_topic_row,
		       topics_name
		        );
		lv.setBackgroundColor(getResources().getColor(R.color.color_show));
		
		
		
			      // lv.getFirstVisiblePosition());
		 
		
		
		lv.setAdapter(adapter);
		
//		 View v = lv.getChildAt(0);
//		 v.setPadding(0, 20, 0, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chose_topic, menu);
		return true;
	}
	
	private class OnListViewItemClickListner implements ListView.OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) 
		{
			
			Intent intent = new Intent();
			intent.putExtra(CHOSE_TOPIC, arg2);
			setResult(RESULT_OK, intent);
			finish();
			overridePendingTransition(R.anim.pull_in_left,
					R.anim.push_out_right);
			
		}
		
	}

}
