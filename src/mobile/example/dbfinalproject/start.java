package mobile.example.dbfinalproject;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class start extends Activity {
	public final static int RESULT_OK = 0;
	private String url = "http://192.168.131.107/reverse.php?";
	private String urlN = "http://192.168.131.107/notice.php";
	
	ArrayList<HomeworkDto> alHw1;
	HomeworkAdapter hwAdapter;
	ListView lvClass_start;
	ListView lvNotice_start;
	static ProgressDialog pdialog;
	loginDto loginDto;
	MyXmlParser parser;
	int year, month, today_day, today_name;
	Calendar cal;
	SharedPreferences pref;
	TextView tvDate;
	LocationManager locManager;
	String id;
	int admin;
	String provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		
		Intent intent = getIntent();
		loginDto = (loginDto) intent.getSerializableExtra("startList");
		id = loginDto.getMy_ID().toString();
		admin = loginDto.getMy_Account();
		parser = new MyXmlParser();
		
		cal = Calendar.getInstance();
		
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		today_day = cal.get(Calendar.DATE)+1;
		today_name = cal.get(Calendar.DAY_OF_WEEK);
		
		tvDate = (TextView)findViewById(R.id.tvDate);
		
		
		lvClass_start = (ListView)findViewById(R.id.lvClass_start);
		lvNotice_start = (ListView)findViewById(R.id.lvNoticContent_start);
		locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.NO_REQUIREMENT);
		crit.setPowerRequirement(Criteria.NO_REQUIREMENT);
		crit.setAltitudeRequired(false);
		crit.setCostAllowed(true);
		
		if(locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else {
			provider = LocationManager.NETWORK_PROVIDER;
		}
		
		
		if(loginDto.getMy_Account() == 3) {
			ArrayList<String> list= new ArrayList<String>();
			list.add("홍진이");
			list.add("이지영");
			lvClass_start.setAdapter(new ArrayAdapter<String>(start.this, android.R.layout.simple_list_item_1, list));
		}
		else {
			pdialog = ProgressDialog.show(start.this, "Wait", "Wait a second, please");
			
			url = url + "id=" + loginDto.getMy_ID() + "&admin=" + loginDto.getMy_Account();
			NetworkThread thread = new NetworkThread(handler, url);
			thread.setDaemon(true);
			thread.start();
		}
		
		NetworkThread thread = new NetworkThread(nHandler, urlN);
		thread.setDaemon(true);
		thread.start();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		String temp = null;
		switch(today_name){
		case 1:  temp = "일요일";  break;
		case 2:  temp = "월요일";  break;
		case 3:  temp = "화요일";  break;
		case 4:  temp = "수요일";  break;
		case 5:  temp = "목요일";  break;
		case 6:  temp = "금요일";  break;
		case 7:  temp = "토요일";  break;
		}
		
		tvDate.setText("" + year + " " + month + " " + today_day + " " + temp);
		
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			if(msg.what == RESULT_OK) {
				String xml = (String)msg.obj;

				ArrayList<ClassDto> cDto= new ArrayList<ClassDto>();
				cDto = parser.parseClass(xml, cDto, 1, -1);
				
				loginDto.setClassList(cDto);
				
				
				pdialog.dismiss();
				
				ArrayList<String> className = new ArrayList<String>();
				for(int i = 0; i < loginDto.getClassList().size(); i++) {
					className.add(loginDto.getClassList().get(i).getClass_Name());
				}	
				
				lvClass_start.setAdapter(new ArrayAdapter<String>(start.this, android.R.layout.simple_list_item_1, className));
				lvClass_start.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(start.this, HomeworkActivity.class);
						intent.putExtra("LOGIN", loginDto);
						intent.putExtra("SELECT", position);
						startActivity(intent);
					}
				});
			}
		}
		
	};
	
	Handler nHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			if(msg.what == RESULT_OK) {
				String xml = (String)msg.obj;
				ArrayList<NoticeDto> notice = new ArrayList<NoticeDto>();
				notice = parser.parseNotice(xml, notice, 1, -1);
				NoticeAdpater adapter = new NoticeAdpater(start.this, R.layout.notice_activity, notice);
				lvNotice_start.setAdapter(adapter);
				
			}
		}
		
	};
	
	public void onClick(View v) {
		Intent intent = null;
		
		switch(v.getId()) {
		case R.id.btnStart1:
			intent = new Intent(start.this, PaymentActivity.class);
			intent.putExtra("LOGIN", loginDto);
			break;
		case R.id.btnStart2:
			locManager.requestLocationUpdates(provider, 10, 1, locListener);
			break;
		case R.id.btnStart3:
			intent = new Intent(start.this, AttendanceActivity.class);
			intent.putExtra("LOGIN", loginDto);
			break;
		case R.id.btnStart4:
			intent = new Intent(start.this, GradeActivity.class);
			intent.putExtra("LOGIN", loginDto);
			break;
		case R.id.btnStart5:
			intent = new Intent(start.this, OptionActivity.class);
			intent.putExtra("LOGIN", loginDto);
			break;
		case R.id.btnStart6:
			pref = getSharedPreferences("login", 0);
			SharedPreferences.Editor edit = pref.edit();
			edit.putString("ID", "");
			edit.putString("PWD", "");
			edit.putInt("ADMIN", 1);
			edit.putBoolean("CHECK", false);
			edit.commit();
			intent = new Intent(start.this, MainActivity.class);
			finish();
			break;
		}
		  if (intent != null) 
			  startActivity(intent);
	}
	
	LocationListener locListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location loc) {
			// TODO Auto-generated method stub
			double latitude = loc.getLatitude();
			double longtitude = loc.getLongitude();
			
			if(latitude >= 37.605320  && latitude <= 37.607320 && longtitude <= 127.042808 && longtitude >= 127.040808) {
				Toast.makeText(start.this, "학원입니다. 출석완료", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	};
}

