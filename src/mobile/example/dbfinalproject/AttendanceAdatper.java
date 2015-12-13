package mobile.example.dbfinalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class AttendanceAdatper extends BaseAdapter{
	
	LayoutInflater inflater;
	ArrayList<AttendanceDto> list;
	int layout;

	public AttendanceAdatper(Context context, ArrayList<AttendanceDto> list, int layout) {
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null)
			convertView = inflater.inflate(layout, parent, false);
		
		TextView tvStudentName = (TextView)convertView.findViewById(R.id.tvStuList_Attendance);
		TextView tvAttendance = (TextView)convertView.findViewById(R.id.textView1);
		
		tvStudentName.setText(list.get(position).getStudentName());
		tvAttendance.setText(list.get(position).getAttendance_state());
		
		return convertView;
	}

}
