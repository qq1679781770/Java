package library;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class Handlers {
	@RequestMapping("/inquire")
	public String inquire(){
		return "inquire";
	}
	
	@RequestMapping("/borrow_return")
	public String borrow_return(){
		return "borrow_return";
	}
	/*搜索图书
	 * json string
	 * 
	 * return 结果
	 */
	@RequestMapping(value="/api/inquire",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String inquire(@RequestParam("subjectsname") String subjectsname,@RequestParam("subjectsvalue") String subjectsvalue) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, UnsupportedEncodingException{
		subjectsname=new String(subjectsname.getBytes("ISO-8859-1"),"UTF-8");
		subjectsvalue=new String(subjectsvalue.getBytes("ISO-8859-1"),"UTF-8");
		JSONArray json=new JSONArray();
		List<Book> booklist=(List<Book>) model.queryForList(new Book(), new String[]{subjectsname}, new Object[]{subjectsvalue});
		for(Book book:booklist){
			json.add(book);
		}
		//System.out.println(json);
		//System.out.println(subjectsname+subjectsvalue);
		return json.toString();
	}
	/*类型搜索请求
	 * json string
	 * 
	 * return 结果
	 */
	@RequestMapping(value="/api/inquiresubjects",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String inquiresubjects(@RequestParam("subjectsname") String subjectsname,@RequestParam("subjectsvalue") String subjectsvalue) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, UnsupportedEncodingException{
		subjectsname=new String(subjectsname.getBytes("ISO-8859-1"),"UTF-8");
		subjectsvalue=new String(subjectsvalue.getBytes("ISO-8859-1"),"UTF-8");
		JSONArray json=new JSONArray();
		List<String> res=model.inquireforvalue(new Book(), subjectsname, subjectsvalue);
		for(String item:res){
			json.add(item);
		}
		//System.out.println(json);
		return json.toString();
	}
	/*学生搜索建议
	 * json string
	 * 
	 * return 结果
	 */
	@RequestMapping(value="/api/vagueinquireborrow",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String  vagueinquireborrow(@RequestParam("student_id") String student_id) throws UnsupportedEncodingException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		student_id=new String(student_id.getBytes("ISO-8859-1"),"UTF-8");
		List<String> res=model.inquireforvalue(new BorrowReturn(), "student_id", student_id);
		JSONArray json=new JSONArray();
		for(String item:res){
			json.add(item);
		}
		return json.toString();
	}
	/*根据学生号搜索借阅情况
	 * json string
	 * 
	 * return 结果
	 */
	@RequestMapping(value="/api/inquireborrow",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String inquireborrow(@RequestParam("student_id") String student_id) throws UnsupportedEncodingException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		student_id=new String(student_id.getBytes("ISO-8859-1"),"UTF-8");
		List<BorrowReturn> borrowreturn=model.queryForList(new BorrowReturn(), new String[]{"student_id"}, new Object[]{student_id});
		JSONArray json=new JSONArray();
		
		for(BorrowReturn item:borrowreturn){
			JSONObject jsonobject=new JSONObject();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String borrow_time=df.format(item.getBorrow_time());
			if(item.getReturn_time()!=null){
			    String return_time=df.format(item.getReturn_time());
			    jsonobject.put("return_time", return_time);
			}
			else
				jsonobject.put("return_time", null);

			jsonobject.put("book_id", item.getBook_id());
			jsonobject.put("student_id", item.getStudent_id());
			jsonobject.put("borrow_time", borrow_time);			
			jsonobject.put("amerce", item.getAmerce());
			json.add(jsonobject);
		}
		System.out.println(json);
		return json.toString();
	}
	/*借书请求
	 * json string
	 * 
	 * return 结果
	 */
	@RequestMapping(value="/api/borrow",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String borrow(@RequestBody String  borrow) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, UnsupportedEncodingException{
        borrow=new String(borrow.getBytes("ISO-8859-1"),"UTF-8");
		@SuppressWarnings("static-access")
		JSONObject json=new JSONObject().fromObject(borrow);
        Integer bookid=json.getInt("book_id");
        String student_id=json.getString("student_id");
		Book book=null;
		Student student=null;
		if(model.queryForObject(new Book(), "book_id", bookid)!=null)
		   book=(Book) model.queryForObject(new Book(), "book_id", bookid);
	    if(model.queryForObject(new Student(), "student_id", student_id)!=null)
		   student=(Student) model.queryForObject(new Student(), "student_id", student_id);
		JSONArray jsonres=new JSONArray();
		String res;
		if(student==null){
			res="无学生";
		}
		
		else if(book==null){
			res= "无书籍";
		}
		else if(book!=null&&book.getIs_borrowed()==1){
			res="书籍已借出";
		}
		else if(student!=null&&student.getOnelimit()==5){
			res="一次限额已满";
		}
		else{
		    model.borrow(book, student);
		    res="success";
		}
		jsonres.add(res);
		return jsonres.toString();
	}
	/*还书请求
	 * json string
	 * 
	 * return 结果
	 */
	@RequestMapping(value="/api/return",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String return_book(@RequestBody String Return) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		@SuppressWarnings("static-access")
		JSONObject json=new JSONObject().fromObject(Return);
		Integer bookid=json.getInt("book_id");
        String student_id=json.getString("student_id");
        Book book=null;
		Student student=null;
		if(model.queryForObject(new Book(), "book_id", bookid)!=null)
		   book=(Book) model.queryForObject(new Book(), "book_id", bookid);
	    if(model.queryForObject(new Student(), "student_id", student_id)!=null)
		   student=(Student) model.queryForObject(new Student(), "student_id", student_id);
		JSONArray jsonres=new JSONArray();
		String res;
		if(student==null){
			res="无学生";
		}
		
		else if(book==null){
			res= "无书籍";
		}
		else if(book!=null&&book.getIs_borrowed()==0){
			res="书籍已还";
		}
		else{
			model.Return(book, student);
			res="success";
		}
		jsonres.add(res);
		return jsonres.toString();
	}
}
