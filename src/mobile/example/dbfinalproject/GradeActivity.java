package mobile.example.dbfinalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class GradeActivity extends Activity{
	
	public final static int RESULT_OK = 0;
	String url = "http://192.168.131.80/test.php?";
	String gurl = "http://192.168.131.80/grade.php?";
	ArrayList<String> class_arrList;
	ArrayAdapter<String> class_adapter;
	ArrayList<String> test_arrList;
	ArrayAdapter<String> test_adapter;
	ArrayList<testDto> testList;
	
	MyXmlParser parser;
	
	TextView tvTotalGrade_grade;
	TextView tvStudent_grade;
	Spinner test_sp;
	Spinner class_sp;
	int spClassPosition = 0;
	int spTestPosition = 0;
	
	loginDto loginDto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grade_activity);
		
		Intent intent = getIntent();
		loginDto = (loginDto) intent.getSerializableExtra("LOGIN");
		
		parser = new MyXmlParser();
		
		tvTotalGrade_grade = (TextView)findViewById(R.id.tvTotalGrade_grade);
		tvStudent_grade = (TextView)findViewById(R.id.tvStudent_grade);
		
		Spinner class_sp = (Spinner)findViewById(R.id.spClass_grade);
		
		ArrayList<String> className = new ArrayList<String>();
		className.add("반을 선택해 주세요.");
		for(int i = 0; i < loginDto.getClassList().size(); i++) {
			className.add(loginDto.getClassList().get(i).getClass_Name());
		}	
		
		class_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, className);
		class_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		class_sp.setAdapter(class_adapter);
		
		test_arrList = new ArrayList<String>();
		
		
		test_sp = (Spinner)findViewById(R.id.spTest_grade);
		
		
		
		class_sp.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				switch(position){
				case 0:
					test_adapter = new ArrayAdapter<String> (GradeActivity.this, android.R.layout.simple_spinner_item, test_arrList);
					test_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					test_sp.setAdapter(test_adapter);
					spClassPosition = 0;
					break;
				default:
					spClassPosition = position-1;
					
					url += "id=" + loginDto.getClassList().get(position-1).getClass_ID();
					NetworkThread thread = new NetworkThread(handler, url);
					thread.setDaemon(true);
					thread.start();

					break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
	}
	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			String xml = (String)msg.obj;
			
			testList = new ArrayList<testDto>();
			testList = parser.parseTest(xml, testList, 1, -1);
		
			ArrayList<String> testName = new ArrayList<String>();
			testName.add("시험을 선택해 주세요.");
			for(int i = 0; i < loginDto.getClassList().size(); i++) {
				testName.add(testList.get(i).getTest_name());
			}	
			
			test_adapter = new ArrayAdapter<String> (GradeActivity.this, android.R.layout.simple_spinner_item, testName);
			test_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			test_sp.setAdapter(test_adapter);
			

			test_sp.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener(){
				
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					switch(position){
					case 0:
						tvTotalGrade_grade.setText("");
						tvStudent_grade.setText("");
						break;
					default:
						spTestPosition = position-1;
						
						if(loginDto.getMy_Account() == 3) {
							gurl += "id=" + loginDto.getClassList().get(position-1).getClass_ID() + "&tid=" + testList.get(position).getTest_id() + "&sid=" + loginDto.getStudent_ID().get(0);
						} else {
							gurl = gurl + "id=" + loginDto.getClassList().get(position-1).getClass_ID() + "&tid=" + testList.get(position).getTest_id() + "&sid=" + loginDto.getMy_ID();
							
						}
						
						NetworkThread thread = new NetworkThread(ghandler, gurl);
						thread.setDaemon(true);
						thread.start();
						break;
					}
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			}));
			
		}
		
	};
	
	Handler ghandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			String xml = (String)msg.obj;
			
			ArrayList<GradeDto> gradeList = new ArrayList<GradeDto>();
			gradeList = parser.parseGrade(xml, gradeList, 1, -1);
			
			loginDto.getClassList().get(spClassPosition).setGradeList(gradeList);
			
			
			tvTotalGrade_grade.setText(gradeList.get(0).getAvgScore());
			tvStudent_grade.setText(gradeList.get(0).getStudentScore());
			
		}
		
	};
	
}
