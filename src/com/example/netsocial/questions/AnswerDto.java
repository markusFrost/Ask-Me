package com.example.netsocial.questions;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.ParseException;

import com.example.netsocial.objects.Like;
import com.google.gson.annotations.SerializedName;

public class AnswerDto
{
	
	public static final String TEXT = "Text";
	public static final String DT_CREATE = "DtCreate";
	public static final String ID = "Id";
	public static final String URL_SET_LIKE_1 = "http://103.16.228.174:33232/api/answers?quest_id=";
	public static final String URL_SET_LIKE_2 = "&answ_id=";

	public AnswerDto(){}
	
	//@SerializedName("QuestionId")
	public String QuestionId;

	//@SerializedName("UserID")
	public String UserID;

	//@SerializedName("DtCreate")
	public String DtCreate ;

	//@SerializedName("Text")
	public String Text;

	//@SerializedName("Likes")
	public Like[] Likes;

	//@SerializedName("Id")
	public int Id ;
	//@SerializedName("Name")
	public String Name;
	
	public String UserName;
	
	
	 
	 

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getQuestionId() {
		return QuestionId;
	}

	public void setQuestionId(String questionId) {
		this.QuestionId = questionId;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		this.UserID = userID;
	}

	public String getDtCreate() {
		return convertToData(DtCreate);
	}

	public void setDtCreate(String dtCreate) {
		this.DtCreate = dtCreate;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		this.Text = text;
	}

	public Like[] getLikes() {
		return Likes;
	}

	public void setLikes(Like[] likes) {
		this.Likes = likes;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}
	
	private String convertToData(String dateInString)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		dateInString = dateInString.replace("T", "-");
		int index = dateInString.lastIndexOf(".");
		dateInString = dateInString.substring(0, index);
		//System.out.println(dateInString);
		
		try 
		{
			 
			Date date = formatter.parse(dateInString);

	        SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("dd. MMM yyyy HH:mm:ss");

	        StringBuilder dateTime = new StringBuilder( dateformatMMDDYYYY.format( date ) );
	        
	        return dateTime.toString();

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
     
     

}
