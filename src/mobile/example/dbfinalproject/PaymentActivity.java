package mobile.example.dbfinalproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PaymentActivity extends Activity{
	
	public final static int RESULT_OK = 0;
	private String url = "http://192.168.131.80/pay.php?";
	
	ListView lvPaymentList;
	ArrayList<PaymentDto> studentList;
	ArrayList<PaymentDto> payList;
	ArrayList<String> classList;
	static ProgressDialog pdialog;
	String className;
	loginDto loginDto;
	MyXmlParser parser;
	TextView tvName_payment; 
	TextView nspay_payment; 
	TextView tvpay_payment;
	TextView tvClass_payment; 
	TextView tvTotal_payment;
	long totalPay = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_activity);
		Intent intent = getIntent();
		loginDto = (loginDto) intent.getSerializableExtra("LOGIN");
		
		parser = new MyXmlParser();
		
		payList = new ArrayList<PaymentDto>();

		nspay_payment =(TextView)findViewById(R.id.nspay_payment);
		tvTotal_payment = (TextView)findViewById(R.id.tvTotal_payment);
		tvName_payment = (TextView)findViewById(R.id.tvName_payment);
		tvpay_payment = (TextView)findViewById(R.id.tvpay_payment);
		lvPaymentList = (ListView)findViewById(R.id.lvPayment);

		pdialog = ProgressDialog.show(PaymentActivity.this, "Wait", "Wait a second, please");
		
		url = url +  "id=" + loginDto.getMy_ID() + "&admin=" + loginDto.getMy_Account();
		NetworkThread thread = new NetworkThread(handler, url);
		thread.setDaemon(true);
		thread.start();
		
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			String xml = (String)msg.obj;

			ArrayList<PaymentDto> payList = new ArrayList<PaymentDto>();
			payList = parser.parsePay(xml, payList, 1, -1);
			Log.i("wefwefwefwef", "erhtehreawr");
			pdialog.dismiss();
			PaymentAdapter payad = new PaymentAdapter(PaymentActivity.this, R.layout.payment_detail, payList);
			lvPaymentList.setAdapter(payad);

			for(int i =0;i<payList.size();i++){
				tvName_payment.setText(payList.get(i).getStudent_name());
				Log.i("wefwfwef", ""  + Integer.parseInt(payList.get(i).getPay_amount().toString()));
				totalPay = Long.parseLong(payList.get(i).getPay_amount().toString());
			}
			tvName_payment.setText("수강료 결제");
			tvTotal_payment.setText("" + totalPay);

		}

	};

}

