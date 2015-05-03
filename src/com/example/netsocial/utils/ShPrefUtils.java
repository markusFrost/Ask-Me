package com.example.netsocial.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.LoginModel;

public class ShPrefUtils
{
	public static void saveUser(Context context, LoginModel lm) // сохраняем время показа фотографий
	   {
		   SharedPreferences sd = context.getSharedPreferences(lm.getUserName(), Context.MODE_PRIVATE);
		   SharedPreferences.Editor  editor = sd.edit();
		   editor.putString(AppContext.MAIN_PAGE_LOGIN, lm.getUserName());
		   editor.putString(AppContext.MAIN_PAGE_PASSWORD, lm.getPasswod());
		   editor.commit();
		   
		   saveLastUser(context,lm.getUserName());
	   }

	public static LoginModel getUser(Context context) // считываем пользователя
	   {
		   String fileName = getLastUser(context);
		   System.out.println("filename = " + fileName);
		   if ( TextUtils.isEmpty(fileName))
		   {
			   return null;
		   }
		   
		   SharedPreferences sd = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		   String login = sd.getString(AppContext.MAIN_PAGE_LOGIN, "");
		   String password = sd.getString(AppContext.MAIN_PAGE_PASSWORD, "");
		   
		   System.out.println("login = " + login + "  pass = " + password);
		   
		   if ( TextUtils.isEmpty(login)  &&  TextUtils.isEmpty(password))
		   {
			   return null;
		   }
		   else
		   {
		       LoginModel lm = new LoginModel(login, password);
		       return lm;
		   }
	   }
	
	public static void saveLastUser(Context context, String login) // сохраняем логин последнего зашедшего пользователя
	   {
		   SharedPreferences sd = context.getSharedPreferences(AppContext.LAST_USER_FILE_NAME, Context.MODE_PRIVATE);
		   SharedPreferences.Editor  editor = sd.edit();
		   System.out.println("loginUser = " + login);
		   editor.putString(AppContext.LAST_USER_LOGIN, login);
		   editor.commit();
	   }

	public static  String getLastUser(Context context)
	   {
		   
		   SharedPreferences sd = context.getSharedPreferences(AppContext.LAST_USER_FILE_NAME, Context.MODE_PRIVATE);
		   
		   return sd.getString(AppContext.LAST_USER_LOGIN, "");
	   }
}
