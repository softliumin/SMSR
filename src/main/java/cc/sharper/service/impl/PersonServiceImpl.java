package cc.sharper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.sharper.bean.Person;
import cc.sharper.dao.PersonMapper;
import cc.sharper.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService
{
	private PersonMapper peresonMapper;
	
	public PersonMapper getPersonMapper()
	{
		return peresonMapper;
	}
	
	@Autowired
	public void setPeresonMapper(PersonMapper peresonMapper)
	{
		this.peresonMapper = peresonMapper;
	}


	@Override
	public List<Person> showAll()
	{
		return  peresonMapper.queryAll();
		
	}	
	@Override
	public void addPerson(Person person)
	{
		peresonMapper.addPerson(person);
	}
	
	@Override
	public int deletePerson(String id)
	{
		 int num =  peresonMapper.deletePerson(id);
		 return num;
		
	}
}
