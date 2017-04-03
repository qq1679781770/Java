package library;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException{
		//new Student("sds","ee",2,4).save();
		//new model().save();
		//ApplicationContext ctx= new ClassPathXmlApplicationContext("jdbctemplate.xml");
        //model mo=(model) ctx.getBean("jdbctemplate");
		//mo.save();
		//Student s=(Student)ctx.getBean("model");
		//s.save();
		//new BorrowReturn(4343, "fddf").save();
		//new Book("jsxnh","jdd","sdd","sdd",1,"dd",1,"dss").save();
		//Field field=Book.class.getDeclaredField("book_id");
		//Book book2=(Book)Book.queryForObject(new Book(), "book_id", 2);
		//System.out.println(book2);
		//BorrowReturn borrowreturn=(BorrowReturn) model.queryForObject(new BorrowReturn(), "book_id", 4343);
        //model.update(borrowreturn,new String[]{"book_id"}, "student_id", "sdsdds");	
        //model.delete(new BorrowReturn(), "book_id", 4343);
		//List<String> res=model.inquireforvalue(new Book(), "book_name", "js");
		//Student s=(Student) mo.queryForObject(new Student(), "student_id", "100");
		 BorrowReturn borrow=(BorrowReturn) model.queryForObject(new BorrowReturn(), "student_id", "54");
		System.out.println(borrow);
	}
	
	
}
