package cc.sharper.service;

import java.util.List;

import cc.sharper.bean.Person;
import cc.sharper.util.Page;

public interface PersonService
{
	public List<Person> showAll();
	
	public List<Person> showPersonByPage(Page page);//分页查询
	
	public void addPerson(Person person);
	
	public void updatePerson(Person person);
	
	public int deletePerson(String id);
	
	public Person getById(String id);
	
	public Person loadPersonWithBook(String id);
}
