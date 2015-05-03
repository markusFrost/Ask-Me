package com.example.netsocial.objects;

import org.apache.http.cookie.Cookie;

public class RequestState 
{
    private Object cookie;
    private int request_code;
    
    public RequestState()
    {
    	
    }

	public Object getObject() {
		return cookie;
	}

	public void setObject(Object cookie) {
		this.cookie = cookie;
	}

	public int getRequest_code() {
		return request_code;
	}

	public void setRequest_code(int request_code) {
		this.request_code = request_code;
	}
    
    
}
