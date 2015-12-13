package mobile.example.dbfinalproject;

import java.io.Serializable;
import java.util.ArrayList;

public class loginDto implements Serializable{

	private String My_ID;
	private String My_Name;
	private int My_Account;
	private ArrayList<String> student_ID;
	private ArrayList<String> student_name;
	private String class_ID;
	private ArrayList<ClassDto> classList;
	
	public ArrayList<String> getStudent_name() {
		return student_name;
	}

	public void setStudent_name(ArrayList<String> student_name) {
		this.student_name = student_name;
	}

	public loginDto() {
		
	}

	public String getMy_ID() {
		return My_ID;
	}

	public void setMy_ID(String my_ID) {
		My_ID = my_ID;
	}
	
	public String getMy_Name() {
		return My_Name;
	}

	public void setMy_Name(String my_Name) {
		My_Name = my_Name;
	}

	public int getMy_Account() {
		return My_Account;
	}

	public void setMy_Account(int my_Account) {
		My_Account = my_Account;
	}
	
	public ArrayList<String> getStudent_ID() {
		return student_ID;
	}

	public void setStudent_ID(ArrayList<String> student_ID) {
		this.student_ID = student_ID;
	}

	public String getClass_ID() {
		return class_ID;
	}

	public void setClass_ID(String class_ID) {
		this.class_ID = class_ID;
	}

	public ArrayList<ClassDto> getClassList() {
		return classList;
	}

	public void setClassList(ArrayList<ClassDto> classList) {
		this.classList = classList;
	}

	
	
	
	
}
