package cc.sharper.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.sharper.bean.Book;
import cc.sharper.bean.Person;
import cc.sharper.dao.PersonMapper;
import cc.sharper.service.PersonService;
import cc.sharper.util.Page;
import cc.sharper.util.RedisUtil;

import com.alibaba.fastjson.JSON;

@Service("personService")
public class PersonServiceImpl implements PersonService
{
	@Autowired
	public PersonMapper peresonMapper;
	
	@Autowired
	public RedisUtil redisUtil;
	
//	public PersonMapper getPersonMapper()
//	{
//		return peresonMapper;
//	}
//	
//	//@Autowired
//	public void setPeresonMapper(PersonMapper peresonMapper)
//	{
//		this.peresonMapper = peresonMapper;
//	}

	@Override
	public List<Person> showAll()
	{
		return  peresonMapper.queryAll();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> showPersonByPage(Page page)
	{
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("page", page);
		List<Person> lp ;
		Long t1 =  System.currentTimeMillis();
		if(!(null==redisUtil.createRedis().get(page.getCurrentPage()+"")))
		{
			
			lp=JSON.parseObject(redisUtil.createRedis().get(page.getCurrentPage()+""), List.class);
		}else
		{
			lp = peresonMapper.queryPersonByPage(parameter);
			redisUtil.createRedis().set(page.getCurrentPage()+"",JSON.toJSONString(lp));
			parameter.put("page", page);
		}	
		Long t2 =  System.currentTimeMillis();
		System.out.println("time:"+(t2-t1));
		return  lp;
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
	
	@Override
	public Person getById(String id)
	{
		return peresonMapper.getById(id);
	}
	
	@Override
	public void updatePerson(Person person)
	{
		peresonMapper.updatePerson(person);
		
	}
	
	@Override
	public Person loadPersonWithBook(String id)
	{
		
		return peresonMapper.loadPersonWithBook(id);
	}
	
}
