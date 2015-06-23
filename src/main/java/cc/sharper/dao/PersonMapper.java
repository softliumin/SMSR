package cc.sharper.dao;

import java.util.List;
import java.util.Map;

import cc.sharper.bean.Person;
import cc.sharper.util.Page;

public interface PersonMapper
{
	public List<Person> queryAll();
	public List<Person>  queryPersonByPage(Map<String,Object> parameter);
	
	public void addPerson(Person person);
	
	public int deletePerson(String id);
	
	public Person getById(String id);
	
	
	public void updatePerson(Person person);
	
	public Person loadPersonWithBook(String id);
}

