package com.example.netsocial.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.netsocial.objects.RequestState;

public class HttpUtils 
{
	public static RequestState GET(DefaultHttpClient httpclient, String url, Cookie cookie)
	{
//		String url = "http://103.16.228.174:33232/api/user";
		
		RequestState rs = null;
		
		 
		HttpGet httpGet;
		try 
		{
			rs = new RequestState();
			CookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookie(cookie);
			httpclient.setCookieStore(cookieStore);
			httpGet = new HttpGet(new URI(url));

			
			httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
			
            // System.out.println("Here1");
			
			try 
			{
				 //System.out.println("Here2");
				HttpResponse httpResponse = httpclient.execute(httpGet);
				int request_code =  httpResponse.getStatusLine().getStatusCode();
				 System.out.println("code_get = " + request_code);
				 rs.setRequest_code(request_code);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
				String json = reader.readLine();
				//System.out.println("data_get = " + json);
				
				
				JSONTokener tokener = new JSONTokener(json);
				JSONArray finalResult = null;
				//System.out.println("token");
				try
				{
				 finalResult = new JSONArray(tokener);
				 rs.setObject(finalResult);
				System.out.println("final result");
				} catch (Exception e) 
				{
					JSONObject jsonObject = new JSONObject(json);
					rs.setObject(jsonObject);
					
					System.out.println("object");
				
					
				//System.out.println("data_get = " + jsonObject.toString());
					
				}
				
				
				
				
				 
				
				
				
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				 System.out.println("Here2E");
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			 System.out.println("Here1E");
			e.printStackTrace();
		}

		return rs;
		
        
	}
	
	
	 public static RequestState PUT( String url, Cookie cookie)
	 {
		 RequestState rs = new RequestState();

		 return rs;
	 }
	
	
	 public static RequestState POST( DefaultHttpClient httpclient, String url, Cookie cookie, JSONObject jsonObject)
	 {
		 
		 RequestState rs = new RequestState();
	     // 1. create HttpClient
          
 
            // 2. make POST request to the given URL
            HttpPost httpPost;
            
			try {
				
				CookieStore cookieStore = new BasicCookieStore();
				cookieStore.addCookie(cookie);
				httpclient.setCookieStore(cookieStore);
				
				httpPost = new HttpPost(new URI(url) );
			
 
            String json = "";

            json = jsonObject.toString();
            
            System.out.println("jsonPost = " + json);

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");


           HttpResponse httpResponse = httpclient.execute(httpPost);
           ArrayList<Object> listObjects = new ArrayList<Object>();
			if (cookie == null)

			{

				List<Cookie> cookies = httpclient.getCookieStore().getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						// System.out.println("- " + cookies.get(i).toString());
					}
				}

				//rs.setCookie(cookies.get(0));
				
				try
				{
				
				listObjects.add(cookies.get(0));
				} catch (Exception e){}
				
			}
           
           int request_code = httpResponse.getStatusLine().getStatusCode();
          
           
           rs.setRequest_code(request_code);
           
           System.out.println("code_post = " + request_code);
           
           BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
			String jsonq = reader.readLine();
			System.out.println("data_post = " + jsonq);
			if (cookie == null)
			{
				listObjects.add(jsonq);
				rs.setObject(listObjects);
			}
			
			
			try
			{
			
			JSONObject jsonObject1 = new JSONObject(jsonq);
			rs.setObject(jsonObject1);
			System.out.println("object");
			} catch (Exception rx){}

            }catch ( Exception e)
            {
            	System.out.println("___________________________________");
//System.out.println(e.getMessage());
System.out.println("___________________________________");
            };
		// System.out.println("res = " + result);
		 return rs;
	 }


	public static RequestState POST(DefaultHttpClient httpclient, String url,
			Cookie cookie, byte[] data) 
	{
		 RequestState rs = new RequestState();
	     // 1. create HttpClient
          
 
            // 2. make POST request to the given URL
            HttpPost httpPost;
            
			try {
				
				CookieStore cookieStore = new BasicCookieStore();
				cookieStore.addCookie(cookie);
				httpclient.setCookieStore(cookieStore);
				
				httpPost = new HttpPost(new URI(url) );
			


            httpPost.setEntity(new ByteArrayEntity(data));

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");


           HttpResponse httpResponse = httpclient.execute(httpPost);
           ArrayList<Object> listObjects = new ArrayList<Object>();
			if (cookie == null)

			{

				List<Cookie> cookies = httpclient.getCookieStore().getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						// System.out.println("- " + cookies.get(i).toString());
					}
				}

				//rs.setCookie(cookies.get(0));
				
				try
				{
				
				listObjects.add(cookies.get(0));
				} catch (Exception e){}
				
			}
           
           int request_code = httpResponse.getStatusLine().getStatusCode();
          
           
           rs.setRequest_code(request_code);
           
           System.out.println("code_post = " + request_code);
           
           
//           String content = EntityUtils.toString(httpResponse.getEntity());
//           byte[] bytes = content.getBytes("UTF8");
//           
//           rs.setObject(bytes);
           
           BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
			String jsonq = reader.readLine();
			rs.setObject(jsonq);			

			if (cookie == null)
			{
				listObjects.add(jsonq);
				rs.setObject(listObjects);
			}
		 
//		 try
//			{
//			
//			JSONObject jsonObject1 = new JSONObject(jsonq);
//			rs.setObject(jsonObject1);
//			System.out.println("object");
//			} catch (Exception rx){}

            }catch ( Exception e)
            {
            	System.out.println("___________________________________");
//System.out.println(e.getMessage());
System.out.println("___________________________________");
            };
		// System.out.println("res = " + result);
		 return rs;
	}
	
	
	
	public static RequestState GET1(DefaultHttpClient httpclient, String url, Cookie cookie)
	{
//		String url = "http://103.16.228.174:33232/api/user";
		
		RequestState rs = null;
		
		 
		HttpGet httpGet;
		try 
		{
			rs = new RequestState();
			CookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookie(cookie);
			httpclient.setCookieStore(cookieStore);
			httpGet = new HttpGet(new URI(url));

			
			httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
			
            // System.out.println("Here1");
			
			try 
			{
				 //System.out.println("Here2");
				HttpResponse httpResponse = httpclient.execute(httpGet);
				
				
				  
					
					
				int request_code =  httpResponse.getStatusLine().getStatusCode();
				 System.out.println("code_get = " + request_code);
				 rs.setRequest_code(request_code);
				
//				 String content = EntityUtils.toString(httpResponse.getEntity());
//		           byte[] bytes = content.getBytes("UTF8");
//		           
//		           rs.setObject(bytes);
				  BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
					String jsonq = reader.readLine();
					
				 
				rs.setObject(jsonq);
				
				
				
				
				 
				
				
				
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				 System.out.println("Here2E");
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			 System.out.println("Here1E");
			e.printStackTrace();
		}

		return rs;
		
        
	}
	 
	  

}
