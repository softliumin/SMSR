package cc.sharper.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.sharper.bean.Person;
import cc.sharper.service.PersonService;

@Controller
@RequestMapping("/")
public class IndexController
{
	private PersonService personService;
	
	public PersonService getPersonService()
	{
		return personService;
	}
	
	@Autowired
	public void setPersonService(PersonService personService)
	{
		this.personService = personService;
	}
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ModelAndView doIndex() 
	{
		ModelAndView mv = new ModelAndView();
		List<Person> list = personService.showAll();
		mv.addObject("message","tt");
		mv.setViewName("index");
		mv.addObject("list",list);
		return mv;
	}
	
	@RequestMapping(value = "/addPerson",method = RequestMethod.GET)
	public ModelAndView addPerson()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addPerson");
		return mv;
	}
	
	@RequestMapping(value = "/savePerson")
	public ModelAndView savePerson(HttpServletRequest request, Person person)
	{
		personService.addPerson(person); 
		
		ModelAndView mv = new ModelAndView();
		List<Person> list = personService.showAll();
		mv.addObject("message","tt");
		mv.setViewName("index");
		mv.addObject("list",list);
		return mv;
	}
	
	@RequestMapping(value = "/deletePerson",method = RequestMethod.POST)
	@ResponseBody
	public void deletePerson(@RequestParam(value="id",required = false) String id,PrintWriter printWriter,HttpServletResponse response)
	{
		System.out.println(id);
		int num= personService.deletePerson(id); 
		if(num==1)
		{
			printWriter.write("{\"success\":\"true\"}");
		}	
		else{
			printWriter.write("{\"success\":\"false\"}");
		}
			
		response.setContentType("application/json");
		
		printWriter.flush();
		printWriter.close();
	}
	
	
}
