package cc.sharper.service;

import java.util.List;

import cc.sharper.bean.Person;

public interface PersonService
{
	public List<Person> showAll();
	
	public void addPerson(Person person);
	
	public int deletePerson(String id);
}
