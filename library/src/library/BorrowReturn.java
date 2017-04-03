package library;

import java.util.Date;

public class BorrowReturn extends model{

	public static String table;
	//public static String primary_key;
	
	protected Integer book_id;
	protected String student_id;
	protected Date borrow_time;
	protected Date return_time;
	protected float amerce;
	
	{
		table="borrow_return";
	}
	
	public BorrowReturn(){}
	
	public BorrowReturn(Integer book_id,String student_id){
		this.book_id=book_id;
		this.student_id=student_id;
	}
	
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public Date getBorrow_time() {
		return borrow_time;
	}
	public void setBorrow_time(Date borrow_time) {
		this.borrow_time = borrow_time;
	}
	public Date getReturn_time() {
		return return_time;
	}
	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}
	public float getAmerce() {
		return amerce;
	}
	public void setAmerce(float amerce) {
		this.amerce = amerce;
	}

	@Override
	public String toString() {
		return "BorrowReturn [book_id=" + book_id + ", student_id=" + student_id + ", borrow_time=" + borrow_time
				+ ", return_time=" + return_time + ", amerce=" + amerce + "]";
	}
}
