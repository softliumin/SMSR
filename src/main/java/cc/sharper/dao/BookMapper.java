package cc.sharper.dao;

import java.util.List;

import cc.sharper.bean.Book;

public interface BookMapper
{
	public List<Book> queryAll();
	public List<Book> loadBookWithPerson(String id);
}
