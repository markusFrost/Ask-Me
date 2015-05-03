package com.example.netsocial.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

public class BitmapUtils 
{
	 public static Bitmap showImage(String path)   {
      	 
		    BitmapFactory.Options bfOptions=new BitmapFactory.Options();
		    bfOptions.inDither=false;                     //Disable Dithering mode
		    bfOptions.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
		    bfOptions.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
		    bfOptions.inTempStorage=new byte[32 * 1024]; 


		    Bitmap bm = null;
		    File file=new File(path);
		    FileInputStream fs=null;
		    try {
		        fs = new FileInputStream(file);
		    } catch (FileNotFoundException e) {
		        //TODO do something intelligent
		        e.printStackTrace();
		    }

		    try {
		        if(fs!=null) bm=BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
		    } catch (IOException e) {
		        //TODO do something intelligent
		        e.printStackTrace();
		    } finally{ 
		        if(fs!=null) {
		            try {
		                fs.close();
		            } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		    }
		    //bm=BitmapFactory.decodeFile(path, bfOptions); This one causes error: java.lang.OutOfMemoryError: bitmap size exceeds VM budget

	 return bm;



		}
	 
	 public static  int getViewPosition( View v, ArrayList<ImageView> imageViews)
		{
			for ( int i = 0; i < imageViews.size(); i++)
			{
				if ( imageViews.get(i) == v)
				{
					return i;
				}
			}
			return 0;
				
		}
	 
	 public static int getLastIngexOfVisibleImageView(ArrayList<ImageView> imageViews)
	 {
		 for ( int i = 0; i < imageViews.size(); i++)
		 {
			 if ( imageViews.get(i).getVisibility() == View.GONE)
			 {
				 return --i;
			 }
		 }
		 
		 return imageViews.size() - 1;
	 }
	 
	
}
