package com.example.netsocial.helps_activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class Message 
{
	
	
	public static void alert_GET_INg(Activity context, String title, String message) 
	{
		 AlertDialog.Builder builder = new AlertDialog.Builder(context);
		 
		
		 builder.setTitle(title)
			.setMessage(message)

			.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) 
						{
							dialog.cancel();
						}
					})
					.setPositiveButton("OK", new OnClickListener() 
					{
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							someEventListener.onClickOk();
							
						}
					});
		 
	AlertDialog alert = builder.create();
	alert.show();


	    
	 }

 
 public static void alert_msg(Activity context, String title, String message) {
	 AlertDialog.Builder builder = new AlertDialog.Builder(context);
	 
	 someEventListener = (onMsgClickOk) context;
	 builder.setTitle(title)
		.setMessage(message)

		.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) 
					{
						dialog.cancel();
					}
				})
				.setPositiveButton("OK", new OnClickListener() 
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						someEventListener.onClickOk();
						
					}
				});
	 
AlertDialog alert = builder.create();
alert.show();


    
 }
 
 public interface onMsgClickOk {
	    public void onClickOk();
	  }
	  
	  static onMsgClickOk someEventListener;
 
 
}