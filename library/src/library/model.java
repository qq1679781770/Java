package library;


import java.util.List;
import java.util.Date;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;



public class model {

	protected DriverManagerDataSource dataSource;
	protected static JdbcTemplate jdbctemplate;
	protected NamedParameterJdbcTemplate namejdbctemplate;
	
	public model(){
		this.dataSource=new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/library");
		dataSource.setUsername("root");
		dataSource.setPassword("****");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		jdbctemplate=new JdbcTemplate(dataSource);
		namejdbctemplate=new NamedParameterJdbcTemplate(dataSource);
	}
	public model(DriverManagerDataSource dataSource){
		this.dataSource=dataSource;
		jdbctemplate=new JdbcTemplate(dataSource);
		namejdbctemplate=new NamedParameterJdbcTemplate(dataSource); 
	}
	/*保存本身
	 */
	public void save() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String sql = null,classname=null;
		classname=this.getClass().getName();
		if(classname.equals("library.BorrowReturn")){
			sql="insert into borrow_return(book_id,student_id,borrow_time,amerce,return_time) "
					+"values(:book_id,:student_id,:borrow_time,:amerce,:return_time)";
			this.getClass().getDeclaredField("borrow_time").set(this, new Date());
			}
		else 
			if(classname.equals("library.Student"))
			     sql="insert into student(student_id,student_name,onelimit,total_number) values(:student_id,:student_name,:onelimit,:total_number)";
			else
				if (classname.equals("library.Book")){
					sql="insert into book(book_name,author,subjects,publisher,bookshelf_id,isbn,is_borrowed,message)"+
				"values(:book_name,:author,:subjects,:publisher,:bookshelf_id,:isbn,:is_borrowed,:message)";
				}
				else{
					sql="insert into bookshelf(bookshelf_id,location,subjects) values(:bookshelf_id,:location,:subjects)";
				}
					
		
		SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(this);
		namejdbctemplate.update(sql,parameterSource);
		
	}
	/*搜索对象根据主键
	 */
	public static <T  extends model>model queryForObject(T v,String fieldname,Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String table=(String) v.getClass().getField("table").get(v);
		String classname=v.getClass().getName();
		RowMapper mapper=null;
		T instance = null;
		if(classname.equals("library.Book"))
		     mapper=new BookMapper();
		else
			if(classname.equals("library.Student"))
				mapper=new StudentMapper();
			else
				if(classname.equals("library.BorrowReturn"))
					mapper=new BorrowReturnMapper();
				else
					mapper=new BookshelfMapper();
		String sql="select * from " + table + " where " + fieldname + "=?";
		try{
			instance= (T)jdbctemplate.queryForObject(sql,new Object[]{value}, mapper);
		}catch(Exception e){
			if((e instanceof IncorrectResultSizeDataAccessException)&&((IncorrectResultSizeDataAccessException)e).getActualSize()==0)
				return null;
		}
		return instance;
	}
	/*搜索对象列表
	 */
	public static <T extends model> List<T> queryForList(T v,String[] fieldnames,Object[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String table=(String)v.getClass().getField("table").get(v);
		String sql="select * from "+table+" where "+fieldnames[0]+"=?";
		for(int i=1;i<fieldnames.length;i++){
			sql=sql+" and "+fieldnames[i]+"=?";
		}
		String classname=v.getClass().getName();
		RowMapper mapper=null;
		if(classname.equals("library.Book"))
		     mapper=new BookMapper();
		else
			if(classname.equals("library.Student"))
				mapper=new StudentMapper();
			else
				if(classname.equals("library.BorrowReturn"))
					mapper=new BorrowReturnMapper();
				else
					mapper=new BookshelfMapper();
		List<T> instances=jdbctemplate.query(sql, args, mapper);
		return instances;
	}
	/*删除
	 */
	public static <T extends model>void delete(T v,String field,Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String table=(String) v.getClass().getField("table").get(v);
		String sql="delete from "+table+" where "+field+"=?";
		jdbctemplate.update(sql, value);
	}
	/*更新实例
	 */
	public static<T extends model> void update(T v,String[] fields,String changefield,Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String table=(String) v.getClass().getField("table").get(v);
		Object[] values=new Object[fields.length+1];
		for(int i=0;i<fields.length;i++){
			values[i+1]=v.getClass().getDeclaredField(fields[i]).get(v);
		}
		values[0]=value;
		String sql="update "+table+" set "+changefield+"=?"+" where "+fields[0]+"=?";
		for(int i=1;i<fields.length;i++){
			sql=sql+" and "+fields[i]+"=?"; 
		}
		jdbctemplate.update(sql,values);
	}
	/*搜索建议值
	 */
	public static <T extends model>List<String> inquireforvalue(T v,String fieldname,Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String table=(String) v.getClass().getField("table").get(v);
		String sql="select "+fieldname+" from "+table+" where "+fieldname+" like ?";
		value="%"+value+"%";
		List<String> res=jdbctemplate.queryForList(sql, new Object[]{value}, String.class);
		return res;
	}
	/*借书事务
	 */
	@Transactional
	public static void borrow(Book book,Student student) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		BorrowReturn borrowreturn =new BorrowReturn(book.getBook_id(),student.getStudent_id());
		borrowreturn.save();
		model.update(book, new String[]{"book_id"},"is_borrowed", 1);
	    model.update(student, new String[]{"student_id"}, "onelimit", student.getOnelimit()+1);
	    model.update(student, new String[]{"student_id"},"total_number", student.getTotal_number()+1);
	}
	/*还书事务
	 */
	@Transactional
	public static void Return(Book book,Student student) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		BorrowReturn borrowreturn=new BorrowReturn(book.getBook_id(),student.getStudent_id());
		model.update(borrowreturn, new String[]{"book_id","student_id"}, "return_time",new Date());
		model.update(book, new String[]{"book_id"},"is_borrowed", 0);
	    model.update(student, new String[]{"student_id"}, "onelimit", student.getOnelimit()-1);
	}
}
