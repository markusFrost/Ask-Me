package com.example.netsocial.helps_activities;

import java.util.ArrayList;

import com.example.netsocial.questions.QuestionDto;
import com.example.netsocial_version_1.R;


import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter
{
	Context context;
	ArrayList<QuestionDto> list_qd;
	public TodoAdapter(Context context, ArrayList<QuestionDto> list_qd) 
	{
		this.context = context;
		this.list_qd = list_qd;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.topic_details_listview_row,
					parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.tv_answer_count = (TextView) convertView
					.findViewById(R.id.topic_details_answers_count);
			
			viewHolder.tv_contetn = (TextView) convertView
					.findViewById(R.id.topic_details_tv_content);
			
			viewHolder.tv_data_time = (TextView) convertView
					.findViewById(R.id.topic_details_tv_data);
			viewHolder.tv_name = (TextView) convertView.
					findViewById(R.id.topic_details_tv_name);

			convertView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();

		QuestionDto qd = (QuestionDto) getItem(position);
		
		holder.tv_answer_count.setText(qd.getAnswersCount() + "");
		holder.tv_contetn.setText(qd.getText());
	//	holder.tv_data_time.setText(qd.getStringDataCreation()); // **************************
		holder.tv_name.setText(qd.getCreatorLogin());
		
		

		return convertView;
	}
	
	static class ViewHolder
	{
		public TextView tv_answer_count;
		public TextView tv_contetn;
		public TextView tv_data_time;
		public TextView tv_name;

	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return list_qd.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list_qd.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
