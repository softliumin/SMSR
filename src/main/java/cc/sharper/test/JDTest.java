package cc.sharper.test;

public class JDTest
{
	public void add(int a,int b) throws Exception
	{
		if(a==2)
		throw new java.util.NoSuchElementException();	
		System.out.println(a+b);
	}
	
	public void add2(int a,int b) 
	{
		System.out.println(a+b);
	}
	
	
	public void add3(int a,int b) 
	{
		System.out.println(a+b);
	}
	
	
	public void add4(int a,int b) 
	{
		
		System.out.println(a+b);
	}
	
	
	public boolean isOne(int num)
	{
		if(num%2==0)
		return false;
		return true;
		
	}
}
