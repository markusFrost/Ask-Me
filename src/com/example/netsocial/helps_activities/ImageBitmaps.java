package com.example.netsocial.helps_activities;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageBitmaps implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3035992243370095577L;
	int index;
	ArrayList<byte[]> bitmArrayList;
	
	public ImageBitmaps(int index, ArrayList<byte[]> bmps)
	{
		this.index = index;
		this.bitmArrayList = bmps;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<byte[]> getBitmArrayList() {
		return bitmArrayList;
	}

	public void setBitmArrayList(ArrayList<byte[]> bitmArrayList) {
		this.bitmArrayList = bitmArrayList;
	}
	
	

}
