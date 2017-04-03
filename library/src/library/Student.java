package library;

import org.springframework.jdbc.core.JdbcTemplate;

public class Student extends model {

	public static String table;
	public static String primary_key;
	
	protected String student_id;
	protected String student_name;
	protected int onelimit;
	protected int total_number;
	
	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", student_name=" + student_name + ", onelimit=" + onelimit
				+ ", total_number=" + total_number + "]";
	}
	{
		table="student";
		primary_key="student_id";
	}
	public Student(){}
	
	public Student(String id,String name,int limit,int number){
		this.student_id=id;
		this.student_name=name;
		this.onelimit=limit;
		this.total_number=number;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public int getOnelimit() {
		return onelimit;
	}
	public void setOnelimit(int onelimit) {
		this.onelimit = onelimit;
	}
	public int getTotal_number() {
		return total_number;
	}
	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}
	
}
