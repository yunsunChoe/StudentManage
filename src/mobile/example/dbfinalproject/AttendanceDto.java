package mobile.example.dbfinalproject;

public class AttendanceDto {

	int id;
	String classId;
	String attendance_date;
	String attendance_state;
	String studentName;
	String studentId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getAttendance_date() {
		return attendance_date;
	}
	public void setAttendance_date(String attendance_date) {
		this.attendance_date = attendance_date;
	}
	public String getAttendance_state() {
		return attendance_state;
	}
	public void setAttendance_state(String attendance_state) {
		this.attendance_state = attendance_state;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
}
