package cc.sharper.dao;

import java.util.List;

import cc.sharper.bean.Person;

public interface PersonMapper
{
	public List<Person> queryAll();
	
	public void addPerson(Person person);
	
	public int deletePerson(String id);
	
	public Person getById(String id);
}
