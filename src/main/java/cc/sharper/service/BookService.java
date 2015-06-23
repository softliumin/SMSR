package cc.sharper.service;

import java.util.List;

import cc.sharper.bean.Book;

public interface BookService
{
	public List<Book> loadBookWithPerson(String id);
}
