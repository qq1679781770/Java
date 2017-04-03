package library;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BorrowReturnMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		BorrowReturn borrowReturn=new BorrowReturn();
		borrowReturn.setBook_id(rs.getInt("book_id"));
		borrowReturn.setStudent_id(rs.getString("student_id"));
		borrowReturn.setBorrow_time(rs.getTimestamp("borrow_time"));
		borrowReturn.setReturn_time(rs.getTimestamp("return_time"));
		borrowReturn.setAmerce(rs.getFloat("amerce"));
		return borrowReturn;
	}

	
}
