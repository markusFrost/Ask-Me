package com.example.netsocial_version_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AskCreateQuestionDetailsActivity extends Activity {

	RadioButton rbAll, rbFam, rbFrrends;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ask_create_question_details);
		
		fillViews();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
	}

	private void fillViews()
	{
		rbAll = (RadioButton) findViewById(R.id.ask_create_question_rbAll);
		rbFam = (RadioButton) findViewById(R.id.ask_create_question_rbFamily);
		rbFrrends = (RadioButton) findViewById(R.id.ask_create_question_rb_Friends);
		
		rbAll.setChecked(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask_create_question_details, menu);
		return true;
	}
	
	public void onCheched (View v)
	{
		switch ( v.getId())
		{
		   case R.id.ask_create_question_rbAll:
		     {
			      rbAll.setChecked(true);
			      rbFam.setChecked(false);
			      rbFrrends.setChecked(false);
		     }break;
		case R.id.ask_create_question_rbFamily:
		    {
		    	rbAll.setChecked(false);
			      rbFam.setChecked(true);
			      rbFrrends.setChecked(false);
		    }break;
		case R.id.ask_create_question_rb_Friends:
		    {
		    	  rbAll.setChecked(false);
			      rbFam.setChecked(false);
			      rbFrrends.setChecked(true);
		    }break;
		}
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		switch (item.getItemId())
    	{
    	   case android.R.id.home:
    	    {
    	    	finish();
    	    	overridePendingTransition(R.anim.pull_in_left,
    					R.anim.push_out_right);
    		     return true;
    	    }
    	    
    	   case R.id.menu_ask_create_question_ok:
    	   {
    		   Intent intent  = new Intent();
    		   
    		   return true;
    	   }
    	}
		return super.onMenuItemSelected(featureId, item);
	}

}
