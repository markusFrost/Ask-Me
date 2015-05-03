package com.example.netsocial.questions;

import java.util.Iterator;

import com.example.netsocial.objects.Like;

public class QuestionDetailsDto extends QuestionDto
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6276992650462595416L;
	public AnswerDto[] Answers;
	//public Like[] Likes ;
	public Iterator<String> MItems;
	public AnswerDto[] getAnswers() {
		return Answers;
	}
	public void setAnswers(AnswerDto[] answers) {
		Answers = answers;
	}
//	public Like[] getLikes() {
//		return this.Likes;
//	}
//	public void setLikes(Like[] likes) {
//		Likes = likes;
//	}
	public Iterator<String> getMItems() {
		return MItems;
	}
	public void setMItems(Iterator<String> mItems) {
		MItems = mItems;
	}
	
	
}
