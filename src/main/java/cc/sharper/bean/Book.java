package cc.sharper.bean;

public class Book
{
	private String id;
	private String name;
	private int price;
	
	
	//关联类
	private Person person;  
	public Person getPerson()
	{
		return person;
	}
	public void setPerson(Person person)
	{
		this.person = person;
	}
	//end
	//关联字段
//	private String userId;
//	public String getUserId()
//	{
//		return userId;
//	}
//	public void setUserId(String userId)
//	{
//		this.userId = userId;
//	}
	//end
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	
}
