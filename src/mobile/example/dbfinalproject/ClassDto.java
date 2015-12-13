package mobile.example.dbfinalproject;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassDto implements Serializable{

	private String Class_ID;
	private String Class_Name;
	private ArrayList<HomeworkDto> homeworkList;
	private ArrayList<GradeDto> gradeList;
	
	public String getClass_ID() {
		return Class_ID;
	}
	public void setClass_ID(String class_ID) {
		Class_ID = class_ID;
	}
	public String getClass_Name() {
		return Class_Name;
	}
	public void setClass_Name(String class_Name) {
		Class_Name = class_Name;
	}
	public ArrayList<HomeworkDto> getHomeworkList() {
		return homeworkList;
	}
	public void setHomeworkList(ArrayList<HomeworkDto> homeworkList) {
		this.homeworkList = homeworkList;
	}
	public ArrayList<GradeDto> getGradeList() {
		return gradeList;
	}
	public void setGradeList(ArrayList<GradeDto> gradeList) {
		this.gradeList = gradeList;
	}
	
	
	
	
}
