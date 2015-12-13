package mobile.example.dbfinalproject;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoticeAdpater extends BaseAdapter{
	private Context context;
	private int layout;
	private ArrayList<NoticeDto> myData;
	private LayoutInflater inflater;
	
	
	public NoticeAdpater(Context context, int layout, ArrayList<NoticeDto> myData) {
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
			convertView = inflater.inflate(layout, parent, false);
		}

		TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitleNotice);
		TextView tvDate = (TextView)convertView.findViewById(R.id.tvDateNotice);
		Log.i("wefwefewf", myData.get(position).getNotice_content());
		tvTitle.setText(myData.get(position).getNotice_content());
		tvDate.setText(myData.get(position).getNotice_date());
		
		return convertView;
		
	}

}
