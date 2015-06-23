package cc.sharper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.sharper.bean.Book;
import cc.sharper.dao.BookMapper;
import cc.sharper.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService
{
	@Autowired
	public BookMapper bookMapper;
	
	@Override
	public List<Book> loadBookWithPerson(String id)
	{
		
		return bookMapper.loadBookWithPerson(id);
	}

}
