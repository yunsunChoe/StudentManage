package mobile.example.dbfinalproject;

public class PaymentDto {

	 String pay_id;
	 String pay_method;
	 String pay_amount;
	 String pay_month;
	 String classname;
	 String student_name;
	 
	 public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getPay_id() {
		return pay_id;
	}
	
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getPay_month() {
		return pay_month;
	}
	public void setPay_month(String pay_month) {
		this.pay_month = pay_month;
	}
}
