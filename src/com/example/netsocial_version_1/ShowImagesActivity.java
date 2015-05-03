package com.example.netsocial_version_1;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.example.netsocial.helps_activities.ImageBitmaps;
import com.example.netsocial_version_1.fragments.PageFragment;

public class ShowImagesActivity extends FragmentActivity 
{
	ViewPager pager;
	  PagerAdapter pagerAdapter;
	  
	  ArrayList<byte[]> bitmaps = new  ArrayList<byte[]>();
	  int width, height;
	  
	  int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_images);
		
		Intent intent = getIntent();
		
		ImageBitmaps ib =  (ImageBitmaps) intent.getExtras().get("ImageBitmaps");
		
		index = ib.getIndex();
		
		bitmaps = ib.getBitmArrayList();


		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		 height = displaymetrics.heightPixels;
		 width = displaymetrics.widthPixels;
		
		pager = (ViewPager) findViewById(R.id.pager);
		
		
	    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
	    pager.setAdapter(pagerAdapter);

	    pager.setCurrentItem(index);
	    pager.setOnPageChangeListener(new OnPageChangeListener() {

	      @Override
	      public void onPageSelected(int position) {

	    
	      }

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		



	    });
	    
	    
	    
	    
		getActionBar().setDisplayHomeAsUpEnabled(true);
		  
		
	}
	
	 private class MyFragmentPagerAdapter extends FragmentPagerAdapter 
	 {

		    public MyFragmentPagerAdapter(FragmentManager fm) {
		      super(fm);
		    }

		    @Override
		    public Fragment getItem(int position) {

		    	byte[] b = bitmaps.get(position);
		      return PageFragment.newInstance(width, height, position, b);
		    }

		    @Override
		    public int getCount() {
		      return bitmaps.size();
		    }

	  }
	 
	 @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if ( item.getItemId() == android.R.id.home)
		{
			finish();
			
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_images, menu);
		return true;
	}

}
