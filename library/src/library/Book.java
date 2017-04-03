package library;

public class Book extends model{
	
	public static String table;
	public static String primary_key;
	
	protected Integer book_id;
	protected String book_name;
	protected String author;
	protected String subjects;
	protected String publisher;
	protected Integer bookshelf_id;
	protected String isbn;
	protected Integer is_borrowed;
	protected String message;
	
	{
		table="book";
		primary_key="book_id";
	}

	public Integer getBook_id() {
		return book_id;
	}
	
	public Book(){}
	public Book(String name,String author,String subjects,String publisher,Integer bookshelf_id,String isbn,Integer is_borrowed,String message){
		this.setBook_name(name);
		this.setAuthor(author);
		this.setBookshelf_id(bookshelf_id);
		this.setSubjects(subjects);
		this.setPublisher(publisher);
		this.setIsbn(isbn);
		this.setMessage(message);
		this.setIs_borrowed(is_borrowed);
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_name=" + book_name + ", author=" + author + ", subjects=" + subjects
				+ ", publisher=" + publisher + ", bookshelf_id=" + bookshelf_id + ", isbn=" + isbn + ", is_borrowed="
				+ is_borrowed + ", message=" + message + "]";
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getBookshelf_id() {
		return bookshelf_id;
	}

	public void setBookshelf_id(Integer bookshelf_id) {
		this.bookshelf_id = bookshelf_id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getIs_borrowed() {
		return is_borrowed;
	}

	public void setIs_borrowed(Integer is_borrowed) {
		this.is_borrowed = is_borrowed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
