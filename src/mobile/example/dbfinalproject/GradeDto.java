package mobile.example.dbfinalproject;

public class GradeDto {

	private String grade_id;
	private String TestName;
	private String studentScore;
	private String avgScore;
	
	public GradeDto() {
		super();
	}

	public String getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(String grade_id) {
		this.grade_id = grade_id;
	}

	public String getTestName() {
		return TestName;
	}

	public void setTestName(String testName) {
		TestName = testName;
	}

	public String getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(String studentScore) {
		this.studentScore = studentScore;
	}
	public String getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}
	
	
}
