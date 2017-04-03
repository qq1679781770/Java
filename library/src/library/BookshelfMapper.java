package library;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookshelfMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bookshelf bookshelf=new Bookshelf();
		bookshelf.setBookshelf_id(rs.getInt("bookshelf_id"));
		bookshelf.setLocation(rs.getString("location"));
		bookshelf.setSubjects(rs.getString("subjects"));
		return bookshelf;
	}

}
