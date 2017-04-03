package library;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class BookMapper  implements RowMapper{

	 public Book mapRow(ResultSet rs,int row) throws SQLException{
		 Book book=new Book();
		 book.setBook_id(rs.getInt("book_id"));
		 book.setBook_name(rs.getString("book_name"));
		 book.setAuthor(rs.getString("author"));
		 book.setSubjects(rs.getString("subjects"));
		 book.setPublisher(rs.getString("publisher"));
		 book.setBookshelf_id(rs.getInt("bookshelf_id"));
		 book.setIs_borrowed(rs.getInt("is_borrowed"));
		 book.setIsbn(rs.getString("isbn"));
		 book.setMessage(rs.getString("message"));
		 return book;
	 }
}
