package mobile.example.dbfinalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PaymentMiddleAdapter extends BaseAdapter{
	Context maincon;
	LayoutInflater inflater;
	ArrayList<PaymentDto> list;
	int layout;

	public PaymentMiddleAdapter(Context context, int paymentDetail, ArrayList<PaymentDto> list) {
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
		
		TextView tvStudenttuition = (TextView)convertView.findViewById(R.id.tvpay_payment);
		TextView tvClass_payment = (TextView)convertView.findViewById(R.id.tvClass_payment);
		
		tvStudenttuition.setText(""+list.get(position).getPay_amount());
		tvClass_payment.setText(list.get(position).getClassname());
		
		return convertView;
	}
}
