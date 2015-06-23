package cc.sharper.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * 测试serviice
 */
@RunWith(Parameterized.class)
public class Test
{
	private String phrase;
	private boolean match;
	
	public Test(String phrase, boolean match) 
	{
		 this.phrase = phrase;
		 this.match = match;
	}
	
	@Parameters
	public static Collection<Object[]> regExValues() 
	{
	 return Arrays.asList(new Object[][] {
	  {"1111", true },
	  {"2222", false },
	  {"2222", true },
	  {"4444", false }});
	}
	
	@org.junit.Test
    public void testPar() 
	{  
		JDTest tt = new JDTest();
		System.out.println(match==tt.isOne(Integer.parseInt(phrase)));
	}
	
	
	@Ignore
	@Before
	public  void test2Before()
	{
		//System.out.println("Before");
	}
	
	@Ignore
	@After
	public  void test2After()
	{
		//System.out.println("After");
	}
	
	@Ignore
	@BeforeClass
	public static void tt()
	{
		//System.out.println("BeforeClass");
	}
	
	@Ignore
	@AfterClass
	public static void tt2()
	{
		//System.out.println("AfterClass");
	}
	@Ignore
	@org.junit.Test
	public void test()
	{
		JDTest tt = new JDTest();
		//tt.add(9, 1);
	}

	//原方法里面抛出的异常会是NoSuchElementException
	//但是在测试用例里面制定抛出的异常时IndexOutOfBoundsException
	//当二者不相同是会抛出异常，如下所示：
//	java.lang.Exception: Unexpected exception, expected<java.lang.IndexOutOfBoundsException> 
//	but was<java.util.NoSuchElementException>
	@Ignore
	@org.junit.Test(timeout=10,expected=IndexOutOfBoundsException.class)
	public void test2() throws Exception
	{
//		try
//		{
//			Thread.sleep(300);
//		} catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
		
		JDTest tt = new JDTest();
		tt.add(2, 1);
	}
	
	
	
	//忽略此方法
	@Ignore
	@org.junit.Test
	public void test3()
	{
		System.out.println("test3");
	}
	
	//输出如下：
//	BeforeClass
//	Before
//	10
//	After
//	Before
//	10
//	After
//	AfterClass
}
