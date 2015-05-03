package com.example.netsocial.objects;

import java.io.Serializable;

public class Topic implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8342459128594417415L;
	private String _Id;
	private String ParentID;
	private String Description ;
	private int QuestionCount; //количество вопросов в топике

	private String Name ;
	private String Name_en ;
	private String Name_zh ;
	public String getId() {
		return _Id;
	}
	public void setId(String id) {
		this._Id = id;
	}
	public String getParentID() {
		return ParentID;
	}
	public void setParentID(String parentID) {
		this.ParentID = parentID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public int getQuestionCount() {
		return QuestionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.QuestionCount = questionCount;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getName_en() {
		return Name_en;
	}
	public void setName_en(String name_en) {
		this.Name_en = name_en;
	}
	public String getName_zh() {
		return Name_zh;
	}
	public void setName_zh(String name_zh) {
		this.Name_zh = name_zh;
	}
	
	
}
