package com.example.netsocial.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.AnalogClock;

import com.example.netsocial.objects.AppContext;
import com.example.netsocial.objects.Like;
import com.example.netsocial.objects.LoginModel;
import com.example.netsocial.objects.RegisterModel;
import com.example.netsocial.objects.Topic;
import com.example.netsocial.objects.TopicDto;
import com.example.netsocial.objects.UserPage;
import com.example.netsocial.questions.AnswerDto;
import com.example.netsocial.questions.QuestionDto;
import com.google.gson.Gson;

public class JsonUtil
{
	
	
	public static byte [] serialize (Object obj)
	{
		Gson gson = new Gson();
		
		String json = gson.toJson(obj);
		
		System.out.println("json = " + json);
		
		byte[] data =json.getBytes(Charset.forName("UTF-8"));
				
		return data;
	}
//	
	
	
	
	
	
	
}
