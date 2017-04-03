package library;

public class Bookshelf extends model{
	public static String table;
	public static String primary_key;
	
	protected Integer bookshelf_id;
	protected String location;
	protected String subjects;
	
	{
		table="bookshelf";
		primary_key="bookshelf_id";
	}

	public Integer getBookshelf_id() {
		return bookshelf_id;
	}

	public void setBookshelf_id(Integer bookshelf_id) {
		this.bookshelf_id = bookshelf_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

}
