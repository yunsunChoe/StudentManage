package mobile.example.dbfinalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class AttendanceActivity extends Activity{
	final static int RESULT_OK = 0;
	
	ListView lvStudentList;
	Spinner spClassList;
	String[] menu;
	SpinnerAdapter adapter;
	AttendanceAdatper detailAdapter;
	ArrayList<AttendanceDto> studentList;
	ArrayList<AttendanceDto> classList;
	static ProgressDialog pdialog;
	TextView tvAttendance;
	String className;
	MyXmlParser parser;
	loginDto loginDto;
	ArrayList<String> classArray;
	int pos;
	int selepos = 0;
	int updatepos = -1;
	
	String url = "http://192.168.131.80/studentlist.php?id=";
	String aUrl = "http://192.168.131.80/addAttendance.php?content=";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance_activity);
		Intent intent = getIntent();
		loginDto = (loginDto) intent.getSerializableExtra("LOGIN");
		
		spClassList = (Spinner)findViewById(R.id.spClass_attendance);
		lvStudentList = (ListView)findViewById(R.id.lvAttendanceList);
		tvAttendance = (TextView)findViewById(R.id.textView1);
		parser = new MyXmlParser();

		classArray = new ArrayList<String>();
		
		for(int i = 0; i < loginDto.getClassList().size() ; i++) {
			classArray.add(loginDto.getClassList().get(i).getClass_Name());
		}
		menu = new String[]{"출석", "결석", "지각"};
		
		adapter = new ArrayAdapter<String>(AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, classArray);
		spClassList.setAdapter(adapter);
		spClassList.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selepos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
				}
			});
		
	}
	

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == RESULT_OK) {
				String xml = (String)msg.obj;
				studentList = new ArrayList<AttendanceDto>();
				
				studentList = parser.parseAttendance(xml, studentList, 1, -1);

				pdialog.dismiss();
				
				detailAdapter = new AttendanceAdatper(AttendanceActivity.this, studentList, R.layout.attendance_detail_activity);
				lvStudentList.setAdapter(detailAdapter);
				
				lvStudentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						final View v = view;
						new AlertDialog.Builder(AttendanceActivity.this)
						.setTitle("출결체크")
						.setIcon(R.drawable.ic_launcher)
						.setItems(menu, 
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									TextView tv = (TextView)v.findViewById(R.id.textView1);
									tv.setText(menu[which]);
									updatepos = which;
								}
							})
						.setNegativeButton("취소", null)
						.show();
					}
				});

			}
	}};
	

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnSave_Attendance:
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
			Date today = new Date ( );
			
			String mTime = mSimpleDateFormat.format ( today );

			for(int i = 0; i < studentList.size(); i++) {
				
				
				aUrl = aUrl + "id=" + studentList.get(i).getId() + "&state" + studentList.get(i).getAttendance_state();
				NetworkThread threadAdd = new NetworkThread(aHandler, aUrl);
				threadAdd.setDaemon(true);
				threadAdd.start();
			}
			
			Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.btnSelect_Attendance:
			pdialog = ProgressDialog.show(AttendanceActivity.this, "Wait", "Wait a second, please");
			url += loginDto.getClassList().get(selepos).getClass_ID() + "&date=2015-12-11";
			NetworkThread thread = new NetworkThread(handler, url);
			thread.setDaemon(true);
			thread.start();
			break;
		}
	}
	
	Handler aHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			if(msg.what == RESULT_OK) {
				int count = 0;
				
				String xml = (String)msg.obj;
				xml = xml.replace("\n", "");
				String result = "success";
				count++;
				if(count == studentList.size()) {
					Toast.makeText(AttendanceActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
				}
			}
		}

	};

}
