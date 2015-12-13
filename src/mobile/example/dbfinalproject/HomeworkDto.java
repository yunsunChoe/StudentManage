package mobile.example.dbfinalproject;

import java.io.Serializable;

public class HomeworkDto implements Serializable{

	private String hwId;
	private String hwContent;
	private String hwDeadline;
	
	public HomeworkDto() {
		super();
	}
	
	
	public String getHwId() {
		return hwId;
	}


	public void setHwId(String hwId) {
		this.hwId = hwId;
	}


	public String getHwContent() {
		return hwContent;
	}
	public void setHwContent(String hwContent) {
		this.hwContent = hwContent;
	}
	public String getHwDeadline() {
		return hwDeadline;
	}
	public void setHwDeadline(String hwDeadline) {
		this.hwDeadline = hwDeadline;
	}
	
	
}
