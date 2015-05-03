package com.example.netsocial.questions;

import java.io.Serializable;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.netsocial.objects.LocationDto;
import com.example.netsocial.objects.TopicDto;
import com.example.netsocial_version_1.R.id;

public class QuestionDto implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3470290395397029729L;

	public String Id;

    public String CreatorId ;
    
	//Тема вопроса
    public String Theme ;
    
	//Текст вопроса
    public String Text ;
    
    public Date DataCreation;

	

	//Топик
    public TopicDto Topic ;

	//локация
    public LocationDto Location;

	//количество лайков
    public int Likes ;
    /// <summary>
    /// ID друзей для которых вопрос
    /// </summary>
    public String[] UsersTo;

//true если этот вопрос доступен всем
    public boolean ToAll;

	//количество ответов
    public int AnswersCount;
    
    private String CreatorLogin;
    
    

	public String getCreatorLogin() {
		return CreatorLogin;
	}

	public void setCreatorLogin(String creatorLogin) {
		this.CreatorLogin = creatorLogin;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	public String getCreatorId() {
		return CreatorId;
	}

	public void setCreatorId(String creatorId) {
		this.CreatorId = creatorId;
	}

	public String getTheme() {
		return Theme;
	}

	public void setTheme(String theme) {
		this.Theme = theme;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		this.Text = text;
	}

	public TopicDto getTopic() {
		return Topic;
	}

	public void setTopic(TopicDto topic) {
		this.Topic = topic;
	}

	public LocationDto getLocation() {
		return Location;
	}

	public void setLocation(LocationDto location) {
		this.Location = location;
	}

	public int getLikes() {
		return Likes;
	}

	public void setLikes(int likes) {
		this.Likes = likes;
	}

	public String[] getUsersTo() {
		return UsersTo;
	}

	public void setUsersTo(String[] usersTo) {
		this.UsersTo = usersTo;
	}

	public boolean isToAll() {
		return ToAll;
	}

	public void setToAll(boolean toAll) {
		this.ToAll = toAll;
	}

	public int getAnswersCount() {
		return AnswersCount;
	}

	public void setAnswersCount(int answersCount) {
		this.AnswersCount = answersCount;
	}
	
	public Date getDataCreation() 
	{
		return DataCreation;
	}
	
//	public String getStringDataCreation() 
//	{
//		return convertToData();
//	}
	
	

//	public void setDataCreation(String daraCreation) {
//		this.dataCreation = daraCreation;
//	}

	public void setDataCreation(Date daraCreation) 
	{
	this.DataCreation = daraCreation;
    }


	

	
//	public void setStringDataCreation(String date) {
//		this.dateInString = date;
//	}
	//String dateInString;
	
	
	
//	private String convertToData()
//	{
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//		dateInString = dateInString.replace("T", "-");
//		int index = dateInString.lastIndexOf(".");
//		
//		if ( index < 0) {dateInString = dateInString.substring(0, index);}
//		//System.out.println(dateInString);
//		
//		try 
//		{
//			 
//			Date date = formatter.parse(dateInString);
//
//	        SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("dd. MMM yyyy HH:mm:ss");
//
//	        StringBuilder dateTime = new StringBuilder( dateformatMMDDYYYY.format( date ) );
//	        
//	        return dateTime.toString();
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	
    
    
}



