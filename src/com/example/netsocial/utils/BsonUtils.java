package com.example.netsocial.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.netsocial.objects.Image;
import com.example.netsocial.objects.RequestState;
import com.example.netsocial.questions.QuestionPostPacket;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class BsonUtils 
{
      public static byte[] serialize(Object p)
      {
    	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
  		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
  		try{
  		mapper.writeValue(baos, p);
  		}
  		catch(Exception exc){
  		System.out.println(exc.getMessage());
  		}

  		return baos.toByteArray();
				
  	    
      }
      
      public static Image deserialize(ByteArrayOutputStream baos)
      {
    	  ObjectMapper mapper = new ObjectMapper(new BsonFactory());
  		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

  	    Image image = null;
  	    try
  	    {
  	    image = mapper.readValue(bais, Image.class);
  	    } catch(Exception e){}
  	    
  	    return image;
      }
      
      
	public static RequestState GET(DefaultHttpClient httpclient, String url,
			Cookie cookie) {
		// String url = "http://103.16.228.174:33232/api/user";

		RequestState rs = null;

		HttpGet httpGet;
		try {
			rs = new RequestState();
			CookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookie(cookie);
			httpclient.setCookieStore(cookieStore);
			httpGet = new HttpGet(new URI(url));

			httpGet.setHeader("Accept", "application/bson");
			httpGet.setHeader("Content-type", "application/bson");

			// System.out.println("Here1");

			try {
				// System.out.println("Here2");
				HttpResponse httpResponse = httpclient.execute(httpGet);
				int request_code = httpResponse.getStatusLine().getStatusCode();
				System.out.println("code_bsn_get = " + request_code);
				rs.setRequest_code(request_code);

				rs.setObject(convertInputStreamToByteArray(httpResponse
						.getEntity().getContent()));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Here2E");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Here1E");
			e.printStackTrace();
		}

		return rs;

	}
	
	public static RequestState PUT(DefaultHttpClient httpclient, String url,
			Cookie cookie, byte[] content) {
		// String url = "http://103.16.228.174:33232/api/user";

		RequestState rs = null;

		HttpPut httpPut;
		try {
			rs = new RequestState();
			CookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookie(cookie);
			httpclient.setCookieStore(cookieStore);
			httpPut = new HttpPut(new URI(url));
			
			httpPut.setEntity(new ByteArrayEntity(content)); 

			httpPut.setHeader("Accept", "application/bson");
			httpPut.setHeader("Content-type", "application/bson");

			// System.out.println("Here1");

			try {
				// System.out.println("Here2");
				HttpResponse httpResponse = httpclient.execute(httpPut);
				int request_code = httpResponse.getStatusLine().getStatusCode();
				System.out.println("code_bsn_put = " + request_code);
				rs.setRequest_code(request_code);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
				String json = reader.readLine();
				System.out.println("data_bsonPut = " + json);

				rs.setObject(convertInputStreamToByteArray(httpResponse
						.getEntity().getContent()));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Here2E");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Here1E");
			e.printStackTrace();
		}

		return rs;

	}
      
      private static  byte[] convertInputStreamToByteArray(InputStream inputStream)
      {
      byte[] bytes= null;
      
      try
      {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      
      byte data[] = new byte[1024];
      int count;
      
      while ((count = inputStream.read(data)) != -1)
      {
      bos.write(data, 0, count);
      }
      
     bos.flush();
      bos.close();
      inputStream.close();
      
     bytes = bos.toByteArray();
      }
      catch (IOException e)
      {
      e.printStackTrace();
      }
      return bytes;
      }
      
      
      
      public static RequestState POST( DefaultHttpClient httpclient,String url, Cookie cookie, byte[] content)
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

  
             // 5. set httpPost Entity
 				httpPost.setEntity(new ByteArrayEntity(content)); 
            
  
             // 6. Set some headers to inform server about the type of the content 
         
             httpPost.setHeader("Accept", "application/bson");
             httpPost.setHeader("Content-type", "application/bson");
        
             // 7. Execute POST request to the given URL

            HttpResponse httpResponse = httpclient.execute(httpPost);
      
            int request_code = httpResponse.getStatusLine().getStatusCode();
           
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
			String json = reader.readLine();
			//System.out.println("data = " + json);
            rs.setRequest_code(request_code);
            
			JSONObject jsonObject1 = new JSONObject(json);
			rs.setObject(httpResponse.getEntity().getContent());
            
            System.out.println("code_post_bson = " + request_code);

            //201 - создано
            //200 - ok
            // 400 - плохой запрос


             }catch ( Exception e)
             {
             	System.out.println("___________________________________");
 e.printStackTrace();
 System.out.println("___________________________________");
             };
 		// System.out.println("res = " + result);
 		 return rs;
 	 }
 	 
      
      
}
