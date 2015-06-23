package cc.sharper.test;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.sharper.service.PersonService;

public class SpringTest
{
	private static ApplicationContext context = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring.xml","spring-mybatis.xml");
	}

	@org.junit.Test
	public void test() {
		PersonService p =  (PersonService) context.getBean("personService");
		System.out.println(p);
	}
}
