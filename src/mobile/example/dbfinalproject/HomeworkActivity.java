package mobile.example.dbfinalproject;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeworkActivity extends Activity{

	public final static int RESULT_OK = 0;
	String url = "http://192.168.131.80/homework.php?";
	ArrayList<String> alClass;
	ArrayList<HomeworkDto> homeworkList;
	LayoutInflater factory;
	HomeworkAdapter hwAdapter;
	MyXmlParser parser;
	static ProgressDialog pdialog;
	ListView lvHw;
	TextView tvDate;
	loginDto loginDto;
	int selectClass;
	boolean flags;
	HomeworkAdapter adapter;
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_homework);
	  adapter = null;
	  Intent intent = getIntent();
	  loginDto = (loginDto) intent.getSerializableExtra("LOGIN");
	  selectClass = (int)intent.getIntExtra("SELECT", 0);
	  
	  flags = true;
	  
	  Calendar cal = Calendar.getInstance();
	  int year = cal.get(Calendar.YEAR);
	  int month = cal.get(Calendar.MONTH)+1;
	  int today_day = cal.get(Calendar.DATE);
	  
	  tvDate = (TextView)findViewById(R.id.tvDate_homework);
	  tvDate.setText("" + year + "-" + month + "-" + today_day);
	  
	  lvHw = (ListView)findViewById(R.id.lvHomeworkList);
	  parser = new MyXmlParser();
	  alClass = new ArrayList<String>();
	  
	  for(int i = 0; i < loginDto.getClassList().size(); i++) {
		  alClass.add(loginDto.getClassList().get(i).getClass_Name());
	  }

	  ArrayAdapter<String> adapter = new ArrayAdapter<String>
	                          (this,android.R.layout.simple_spinner_dropdown_item, alClass);
	  
	  Spinner sp = (Spinner) findViewById(R.id.spClassname_homework);
	  sp.setAdapter(adapter);
	  sp.setSelection(selectClass);
	  sp.setOnItemSelectedListener(new OnItemSelectedListener() {
		  @Override
		  public void onItemSelected(AdapterView<?> arg0, View v, int position, long arg3) {
			  if(position == 2) {
				  flags = false;
			  NetworkThread thread = new NetworkThread(handler, "http://192.168.131.107/homework.php?id=cid0002");
			  thread.setDaemon(true);
			  thread.start();
			  pdialog = ProgressDialog.show(HomeworkActivity.this, "Wait", "Wait a second, please");
		  }}

		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
		  }
	  });
	  
	  factory = LayoutInflater.from(this);
	  
//	  loginDto.getClassList().get(selectClass).getClass_ID()
	  if(flags == true){
	  NetworkThread thread = new NetworkThread(handler, "http://192.168.131.107/homework.php?id=cid0001");
	  thread.setDaemon(true);
	  thread.start();
	  pdialog = ProgressDialog.show(HomeworkActivity.this, "Wait", "Wait a second, please");
	  }
	}	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			if(msg.what == RESULT_OK) {
				String xml = (String)msg.obj;

				homeworkList = new ArrayList<HomeworkDto>();
				homeworkList = parser.parseHomework(xml, homeworkList, 1, -1);
				pdialog.dismiss();
				adapter = new HomeworkAdapter(HomeworkActivity.this, R.layout.list_homework, homeworkList);
				
				lvHw.setAdapter(adapter);
		}
		
	}};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		if(loginDto.getMy_Account() == 2) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.plus_menu, menu);
		return super.onCreateOptionsMenu(menu);
		}
		else
			return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		final View dialogView = factory.inflate(R.layout.dialog_activity, null);
		final EditText etAddHom = (EditText)dialogView.findViewById(R.id.etAddHomework_homework);
		final EditText etDead = (EditText)dialogView.findViewById(R.id.etDeadLine_dialog);
		
		switch(item.getItemId()) {
		case R.id.plus_setting:
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
			.setTitle("등록할 과제를 입력해주세요")
			.setView(dialogView)
			.setPositiveButton("저장", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String content = etAddHom.getText().toString();
					String dead = etDead.getText().toString();
					
					dead = dead.replace("/", "-");
					int size = homeworkList.size();
					
					url = "http://192.168.131.107/addhomework.php?id=hid0010&content=" + content + "&dead=" + dead + "&tid=" + loginDto.getMy_ID() + "&cid=" + loginDto.getClassList().get(0).getClass_ID();
					NetworkThread thread = new NetworkThread(shandler, url);
					thread.setDaemon(true);
					thread.start();
				}
			})
			.setNegativeButton("취소", null);
			
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		return true;
	}
	
	Handler shandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			if(msg.what == RESULT_OK) {
				String xml = (String)msg.obj;
				xml = xml.replace("\n", "");
				String result = "success";

				if(xml.equals(result)) {
					Toast.makeText(HomeworkActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
					NetworkThread thread = new NetworkThread(handler, "http://192.168.131.107/homework.php?id=cid0002");
					thread.setDaemon(true);
					thread.start();
					pdialog = ProgressDialog.show(HomeworkActivity.this, "Wait", "Wait a second, please");
				}
		}
		
	}};

	
}
