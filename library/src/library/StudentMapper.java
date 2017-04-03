package library;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student=new Student();
		student.setStudent_id(rs.getString("student_id"));
		student.setStudent_name(rs.getString("student_name"));
		student.setOnelimit(rs.getInt("onelimit"));
		student.setTotal_number(rs.getInt("total_number"));
		return student;
	}
	

}
