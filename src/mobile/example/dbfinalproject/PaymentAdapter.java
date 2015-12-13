package mobile.example.dbfinalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentAdapter extends BaseAdapter{
	Context maincon;
	LayoutInflater inflater;
	ArrayList<PaymentDto> list;
	int layout;

	public PaymentAdapter(Context context, int paymentDetail, ArrayList<PaymentDto> list) {
		maincon = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
		this.layout = paymentDetail;
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
//		
//		TextView tvStuName = (TextView)convertView.findViewById(R.id.tvStuName_pay);
//		ListView lvpayDetail = (ListView)convertView.findViewById(R.id.lvpayDetail_pay);
//		
//		tvStuName.setText(list.get(position).getStudent_name());
//		
//		if(list.get(position).getStudent_name())
//		
		TextView tvStudenttuition = (TextView)convertView.findViewById(R.id.tvpay_payment);
		TextView tvClass_payment = (TextView)convertView.findViewById(R.id.tvClass_payment);
		TextView tvMethod =  (TextView)convertView.findViewById(R.id.tvMethod_pay);
		TextView tvmonth = (TextView)convertView.findViewById(R.id.tvMonth_pay);
 		
		tvStudenttuition.setText(""+list.get(position).getPay_amount());
		tvClass_payment.setText(list.get(position).getClassname());
		tvMethod.setText(list.get(position).getPay_method());
		tvmonth.setText(list.get(position).getPay_month());
		
		return convertView;
	}

}