package mobile.example.dbfinalproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeworkAdapter extends BaseAdapter {

	private Context context;
	private int layout;
	private ArrayList<HomeworkDto> myData;
	private LayoutInflater inflater;
	
	
	public HomeworkAdapter(Context context, int layout,
			ArrayList<HomeworkDto> myData) {
		this.context = context;
		this.layout = layout;
		this.myData = myData;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int pos = position;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_homework, parent, false);
		}
		TextView tvHwName = (TextView)convertView.findViewById(R.id.tvHwName);
		TextView tvHwContent = (TextView)convertView.findViewById(R.id.tvHwContent);
		TextView tvHwDead = (TextView)convertView.findViewById(R.id.tvHwDeadline);
		
		if(DateCompare(myData.get(position).getHwDeadline()) >= 0) {
			tvHwName.setText("마감");
		}else
			tvHwName.setText("진행 중");
		
		tvHwContent.setText(myData.get(position).getHwContent());
		tvHwDead.setText(myData.get(position).getHwDeadline());
		
		return convertView;
	}
	
	public int DateCompare(String date) {
		
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        Date deadline = null;
        Date today = new Date();
		
		try {
			deadline = format.parse( date );
			today = format.parse( format.format(today) );
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int compare = deadline.compareTo(today);
		
		return compare;

	}


}
