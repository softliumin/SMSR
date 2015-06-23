package cc.sharper.bean;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable
{
	private static final long serialVersionUID = 279852017075801260L;
	
	private String id;
	private String loginId;
	private String password;
	
	private List<Book> bookList;
	
	
	
	public List<Book> getBookList()
	{
		return bookList;
	}
	public void setBookList(List<Book> bookList)
	{
		this.bookList = bookList;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getLoginId()
	{
		return loginId;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
