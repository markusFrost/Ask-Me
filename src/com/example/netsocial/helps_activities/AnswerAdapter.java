package com.example.netsocial.helps_activities;

import java.util.ArrayList;


import com.example.netsocial.questions.AnswerDto;
import com.example.netsocial_version_1.R;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AnswerAdapter extends BaseAdapter
{
	Context context;
	ArrayList<AnswerDto> list_adt;
	private View.OnClickListener ivListener;
	 int index = -1;
	public AnswerAdapter(Context context, ArrayList<AnswerDto> list_adt , View.OnClickListener ivListener) 
	{
		this.context = context;
		this.list_adt = list_adt;
		this.ivListener = ivListener;
		
	}
	
	public AnswerAdapter(Context context, ArrayList<AnswerDto> list_adt , View.OnClickListener ivListener, int index) 
	{
		this.context = context;
		this.list_adt = list_adt;
		this.ivListener = ivListener;
		this.index = index;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.answer_quiz_row,
					parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.tv_contetn = (TextView) convertView
					.findViewById(R.id.quiz_answer_tv_content);
			
			viewHolder.tv_data_time = (TextView) convertView
					.findViewById(R.id.quiz_answer_tv_data);
			
//			viewHolder.tv_level_count = (TextView) convertView
//					.findViewById(R.id.quiz_answer_level_count);
//			
			viewHolder.tv_like_count = (TextView) convertView
					.findViewById(R.id.quiz_answer_tv_like_count);
//			
			viewHolder.tv_name = (TextView) convertView
					.findViewById(R.id.quiz_answer_tv_name);
			viewHolder.iv_like = (ImageView) convertView.findViewById(R.id.quiz_answer_iv_like);
			
	

			convertView.setTag(viewHolder);
			
			viewHolder.iv_like.setOnClickListener(ivListener);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();

		
		AnswerDto adt = (AnswerDto) getItem(position);
		
		
		holder.tv_contetn.setText(adt.getText());
		holder.tv_data_time.setText(adt.getDtCreate());
//		holder.tv_level_count.setText();
		int count = adt.getLikes().length;
		if ( position == index)
		{
			count++;
		}
		holder.tv_like_count.setText( count + "");
		holder.tv_name.setText(adt.getUserName());
		
		System.out.println("answer adapter name = " + adt.getUserName());
		holder.iv_like.setTag(adt);
		
		
		

		return convertView;
	}
	
	static class ViewHolder
	{
		public TextView tv_name;
		public TextView tv_contetn;
		public TextView tv_data_time;
		public TextView tv_level_count;
		public TextView tv_like_count;
		ImageView iv_like;

	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return list_adt.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list_adt.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
