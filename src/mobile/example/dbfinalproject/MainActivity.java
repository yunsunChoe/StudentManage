package mobile.example.dbfinalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static int RESULT_OK = 0;
	public final static int RESULT_CANCEL = 1;
	
	String url = "http://192.168.131.107/login.php?";
	SharedPreferences pref;
	static ProgressDialog pdialog;
	ArrayAdapter<String> adAdmin;
	Spinner spAdmin;
	MyXmlParser parser;
	String url_id, url_pwd;
	CheckBox check;
	EditText id, pwd;
	int admin = 1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		id = (EditText)findViewById(R.id.etID_login);
		pwd = (EditText)findViewById(R.id.etPWD_login);
		check = (CheckBox)findViewById(R.id.cblogin_start);
		
		pref = getSharedPreferences("login", 0);
		
		id.setText(pref.getString("ID", ""));
		pwd.setText(pref.getString("PWD", ""));
		
		spAdmin = (Spinner)findViewById(R.id.spAdmin_login);
		spAdmin.setPrompt("계정");
		
		check.setChecked(pref.getBoolean("CHECK", false));
		
		ArrayList<String> arrayAdmin = new ArrayList<String>();
		arrayAdmin.add("학생");
		arrayAdmin.add("선생님");
		arrayAdmin.add("학부모");
		
		adAdmin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayAdmin);
		parser = new MyXmlParser();
		
		spAdmin.setAdapter(adAdmin);
		
		int adm = pref.getInt("ADMIN", -1);
		if(adm == 1) {
			admin = 1;
			spAdmin.setSelection(0);
		}else if(adm == 2) {
			admin = 2;
			spAdmin.setSelection(1);
		}else if(adm == 3) {
			admin = 3;
			spAdmin.setSelection(2);
		}
			
		spAdmin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				admin = position + 1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> v) {
				// TODO Auto-generated method stub
			}}
		);

		if(check.isChecked()) {
			onClick((Button)findViewById(R.id.btnlogin_login));
		}
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnlogin_login:
			if(id.getText().toString().equals("")) {
				Toast.makeText(this, "Id를 입력해주세요", Toast.LENGTH_SHORT).show();
			} else if(id.getText().toString().equals("")) {
				Toast.makeText(this, "Password를 입력해주세요", Toast.LENGTH_SHORT).show();
			} else {
				url_pwd = pwd.getText().toString();
				url_id = id.getText().toString();
				url += "id=" + url_id + "&pwd=" + url_pwd + "&admin=" + admin; 
				
				pdialog = ProgressDialog.show(MainActivity.this, "Wait", "Wait a second, please");
				NetworkThread thread = new NetworkThread(handler, url);
				thread.setDaemon(true);
				thread.start();
			}	
		}
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			if(msg.what == RESULT_OK) {
				String xml = (String)msg.obj;
				loginDto loginDto = new loginDto();
				loginDto = parser.parse(xml, loginDto, 1, -1);
				loginDto.setMy_Account(admin);
				pdialog.dismiss();

				if(loginDto == null || loginDto.getMy_ID().toString().equals("")) {
					Toast.makeText(MainActivity.this, "등록된 계정이 없습니다.", Toast.LENGTH_SHORT).show();;
				}
				else {
					if(check.isChecked()) {
						pref = getSharedPreferences("login", 0);
						SharedPreferences.Editor edit = pref.edit();
						edit.putString("ID", url_id);
						edit.putString("PWD", url_pwd);
						edit.putInt("ADMIN", admin);
						edit.putBoolean("CHECK", true);
						edit.commit();
						
					}
					Toast.makeText(MainActivity.this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MainActivity.this, start.class);
					intent.putExtra("startList", loginDto);
					finish();
					startActivity(intent);
				}
			}
		}
		
	};
	
	
}
