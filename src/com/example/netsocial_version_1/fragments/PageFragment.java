package com.example.netsocial_version_1.fragments;



import com.example.netsocial_version_1.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PageFragment extends Fragment 
{
	static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
	static final String ARGUMENT_WIDTH = "arg_width";
	static final String ARGUMENT_HEIGHT = "arg_height";
	static final String ARGUMENT_PATH = "arg_path";
	  
	  int pageNumber;

	   int w_screen, h_screen;
	   byte[] byteArray;
	  
	  public static PageFragment newInstance(int w, int h, int page, byte[] path) {
	    PageFragment pageFragment = new PageFragment();

	    Bundle arguments = new Bundle();
	    arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
	    arguments.putInt(ARGUMENT_HEIGHT, h);
	    arguments.putInt(ARGUMENT_WIDTH, w);
	    arguments.putByteArray(ARGUMENT_PATH, path) ;
	    pageFragment.setArguments(arguments);
	    return pageFragment;
	  }
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
	    w_screen = getArguments().getInt(ARGUMENT_WIDTH);
	    h_screen = getArguments().getInt(ARGUMENT_HEIGHT);
	    byteArray = getArguments().getByteArray (ARGUMENT_PATH);
	    

	  }
	  
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.show_fragment, null);
	    
	    ImageView iv = (ImageView) view.findViewById(R.id.user_page_iv_avatar);
  
	    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
				byteArray.length);
	   calculateWidthAndHeight(bitmap);
		iv.getLayoutParams().width = w_image;
		iv.getLayoutParams().height = h_image;
		iv.setImageBitmap(bitmap);
	   
	   return view;
	  }
	  
	  int w_image, h_image;
		
		private void calculateWidthAndHeight(Bitmap b)
		{

			
			int w_image = b.getWidth();
			int h_image = b.getHeight();
			
			int w = w_screen;
			int h = (w * h_image) / w_image;
			
			if ( h > h_screen)
			{
				h = h_screen;
				w = (w_image * h) / h_image;
			}
			this.w_image = w;
			this.h_image = h;
			
			
			
			
			
		}
	
}
