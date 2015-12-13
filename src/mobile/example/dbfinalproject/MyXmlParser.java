package mobile.example.dbfinalproject;

import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class MyXmlParser {

	private final int NO_TAG = 0;
	private ArrayList<String> arrayList;
	
	public loginDto parse(String xml, loginDto dto, int type, int position) {
		final int LOGIN = 1;
		final int PLOGIN = 2;
		final int CHILD = 3;
		final int NAME = 4;
		final int SNAME = 5;
		boolean parentStu = false;
		ArrayList<String> nameList;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			int tagType = NO_TAG;
			int count = 0;
			arrayList = new ArrayList<String>();
			nameList = new ArrayList<String>();
			
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(type == 1) {
						if(parser.getName().equals("parent_id")) {
							tagType = PLOGIN;
							parentStu = true;
						} else if(parser.getName().equals("student_id") && parentStu) {
							tagType = CHILD;
						} else if(parser.getName().equals("student_id")
								|| parser.getName().equals("teacher_id")) {
							tagType = LOGIN;
						} else if(parser.getName().equals("student_name") && parentStu) {
							tagType = NAME;
						} else if(parser.getName().equals("student_name") || parser.getName().equals("teacher_id")) {
							tagType = SNAME;
						}
					}
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("stuInfo")) {
						dto.setStudent_ID(arrayList);
						dto.setStudent_ID(nameList);
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == PLOGIN) {
						dto.setMy_ID(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == LOGIN) {
						dto.setMy_ID(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == CHILD) {
						arrayList.add(parser.getText().toString());
						tagType = NO_TAG;
						parentStu = false;
					} else if(tagType == NAME) {
						dto.setMy_Name(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == SNAME) {
						nameList.add(parser.getText().toString());
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	public ArrayList<ClassDto> parseClass(String xml, ArrayList<ClassDto> clist, int type, int position) {

		//		Parsing 결과
		final int CLASS_ID = 1;
		final int CLASS_NAME = 2;
		Log.i("wfwefewfewfewfewf", "innnnnnnnnnnnnn");
		ArrayList<ClassDto> list;
		
		list = clist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			ClassDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(type == 1) {
						if(parser.getName().equals("item")) {
							dto = new ClassDto();
						}else if(parser.getName().equals("class_id")) {
							tagType = CLASS_ID;
						} else if(parser.getName().equals("class_name")) {
							tagType = CLASS_NAME;
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == CLASS_ID) {
						dto.setClass_ID(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == CLASS_NAME) {
						dto.setClass_Name(parser.getText().toString());
						tagType = NO_TAG;
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<NoticeDto> parseNotice(String xml, ArrayList<NoticeDto> clist, int type, int position) {

		//		Parsing 결과
		final int NOTICE_CON = 1;
		final int NOTICE_DATE = 2;
		
		ArrayList<NoticeDto> list;
		
		list = clist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			NoticeDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(type == 1) {
						if(parser.getName().equals("item")) {
							dto = new NoticeDto();
						}else if(parser.getName().equals("notice_content")) {
							tagType = NOTICE_CON;
						} else if(parser.getName().equals("notice_date")) {
							tagType = NOTICE_DATE;
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == NOTICE_CON) {
						dto.setNotice_content(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == NOTICE_DATE) {
						dto.setNotice_date(parser.getText().toString());
						tagType = NO_TAG;
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<HomeworkDto> parseHomework(String xml, ArrayList<HomeworkDto> clist, int type, int position) {

		//		Parsing 결과
		final int HOME_NAME = 1;
		final int HOME_CON = 2;
		final int HOME_DEAD = 3;
		
		ArrayList<HomeworkDto> list;
		
		list = clist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			HomeworkDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(type == 1) {
						if(parser.getName().equals("item")) {
							dto = new HomeworkDto();
						} else if(parser.getName().equals("homework_id")) {
							tagType = HOME_NAME;
						} else if(parser.getName().equals("homework_content")) {
							tagType = HOME_CON;
						} else if(parser.getName().equals("homework_deadline")) {
							tagType = HOME_DEAD;
						}
					}
						
					break;
					
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == HOME_NAME) {
						dto.setHwId(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == HOME_CON) {
						dto.setHwContent(parser.getText().toString());
						tagType = NO_TAG;
						Log.i("wefwfewfewf", parser.getText().toString());
					} else if(tagType == HOME_DEAD) {
						dto.setHwDeadline(parser.getText().toString());
						tagType = NO_TAG;
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<GradeDto> parseGrade(String xml, ArrayList<GradeDto> clist, int type, int position) {

		//		Parsing 결과
		final int SCORE = 1;
		final int AVERAGE = 3;
		final int ID = 4;
		final int NAME = 2;
		
		ArrayList<GradeDto> list;
		
		list = clist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			GradeDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if(parser.getName().equals("item")) {
						dto = new GradeDto();
					} else if(parser.getName().equals("grade_id")) {
						tagType = ID;
					} else if(parser.getName().equals("grade_test_score")) {
						tagType = SCORE;
					} else if(parser.getName().equals("test_average")) {
						tagType = AVERAGE;
					} else if(parser.getName().equals("test_name")) {
						tagType = NAME;
					} 


					break;

				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == SCORE) {
						dto.setStudentScore(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == NAME) {
						dto.setTestName(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == AVERAGE) {
						dto.setAvgScore(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == ID) {
						dto.setGrade_id(parser.getText().toString());
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<testDto> parseTest(String xml, ArrayList<testDto> clist, int type, int position) {

		//		Parsing 결과
		final int NAME = 2;
		final int ID = 4;
		
		ArrayList<testDto> list;
		
		list = clist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			testDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(type == 1) {
						if(parser.getName().equals("item")) {
							dto = new testDto();
						} else if(parser.getName().equals("test_id")) {
							tagType = ID;
						} else if(parser.getName().equals("test_name")) {
							tagType = NAME;
						} 
					}
						
					break;
					
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == NAME) {
						dto.setTest_name(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == ID) {
						dto.setTest_id(parser.getText().toString());
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<PaymentDto> parsePay(String xml, ArrayList<PaymentDto> clist, int type, int position) {

		//		Parsing 결과
		final int METHOD = 1;
		final int MONTH = 3;
		final int NAME = 4;
		final int AMOUNT = 2;
		final int CNAME = 5;
		
		ArrayList<PaymentDto> list;
		
		list = clist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			PaymentDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if(parser.getName().equals("item")) {
						dto = new PaymentDto();
					} else if(parser.getName().equals("pay_method")) {
						tagType = METHOD;
					} else if(parser.getName().equals("pay_amount")) {
						tagType = AMOUNT;
					} else if(parser.getName().equals("pay_month")) {
						tagType = MONTH;
					} else if(parser.getName().equals("student_name")) {
						tagType = NAME;
					} else if(parser.getName().equals("class_name")) {
						tagType = CNAME;
					} 

					break;

				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == METHOD) {
						dto.setPay_method(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == AMOUNT) {
						dto.setPay_amount(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == MONTH) {
						dto.setPay_month(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == NAME) {
						dto.setStudent_name(parser.getText().toString());
						tagType = NO_TAG;
					} else if(tagType == CNAME) {
						dto.setClassname(parser.getText().toString());
						tagType = NO_TAG;
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<AttendanceDto> parseAttendance(String xml, ArrayList<AttendanceDto> alist, int type, int position) {

		ArrayList<AttendanceDto> list;
		final int CLASS_ID = 5;
		final int ATTENDANCE_STATE = 2;
		final int ATTENDANCE_DATE = 3;
		final int STUDENT_ID = 6;
		final int STUDENT_NAME = 4;
		
		list = alist;
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();

			AttendanceDto dto = null;
			int tagType = NO_TAG;
		
			parser.setInput(new StringReader(xml));

			for (int eventType = parser.getEventType(); 
					eventType != XmlPullParser.END_DOCUMENT;
					eventType = parser.next()) {

				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if(parser.getName().equals("item")) {
						dto = new AttendanceDto();
					}else if(parser.getName().equals("class_id")) {
						tagType = CLASS_ID;
					}else if(parser.getName().equals("student_id")) {
						tagType = STUDENT_ID;
					}else if(parser.getName().equals("student_name")) {
						tagType = STUDENT_NAME;
					}else if(parser.getName().equals("attendance_date")) {
						tagType = ATTENDANCE_DATE;
					}else if(parser.getName().equals("attendance_state")) {
						tagType = ATTENDANCE_STATE;
					}

					break;

				case XmlPullParser.END_TAG:
					if(parser.getName().equals("item")) {
						list.add(dto);
						dto = null;
					}
					break;
				
				case XmlPullParser.TEXT:
					if(tagType == CLASS_ID) {
						dto.setAttendance_state(parser.getText().toString());
						dto.setClassId(parser.getText().toString());
						tagType = NO_TAG;
					}else if(tagType == STUDENT_ID) {
						dto.setStudentId(parser.getText().toString());
						tagType = NO_TAG;
					}else if(tagType == STUDENT_NAME) {
						dto.setStudentName(parser.getText().toString());
						tagType = NO_TAG;
					}else if(tagType == ATTENDANCE_DATE){
						dto.setAttendance_date(parser.getText().toString());
						tagType = NO_TAG;
					}else if(tagType == ATTENDANCE_STATE) {
						dto.setAttendance_state(parser.getText().toString());
						tagType = NO_TAG;
					}
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
