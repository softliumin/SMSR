package cc.sharper.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.sharper.bean.Person;
import cc.sharper.service.PersonService;

public class OneManyTest
{
	private static ApplicationContext context = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring.xml","spring-mybatis.xml");
	}
	
	@Test
	public void test()
	{
//		BookService b =  (BookService) context.getBean("bookService");
//		List<Book> ss = b.loadBookWithPerson("1111");
//		System.out.println(ss.get(0).getPerson().getId());
		
		PersonService  b=  (PersonService) context.getBean("personService");
		Person ss = b.loadPersonWithBook("222222222222");
		System.out.println(ss.getBookList().size());
	}
	
	
}
