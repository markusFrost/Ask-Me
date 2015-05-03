package com.example.netsocial.questions;

import java.util.List;

public class QuestionPostPacket 
{
	public QuestionDto quest;

    public List<byte[]> imgs ; //передаваемы фото в бинарном виде. //Максимальное количество - 6

	public QuestionDto getQuest() {
		return quest;
	}

	public void setQuest(QuestionDto quest) {
		this.quest = quest;
	}

	public List<byte[]> getImgs() {
		return imgs;
	}

	public void setImgs(List<byte[]> imgs) {
		this.imgs = imgs;
	}

   
}
