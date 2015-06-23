package cc.sharper.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cc.sharper.bean.Person;
import cc.sharper.service.PersonService;
import cc.sharper.util.Page;

@Controller
@RequestMapping("/")
public class IndexController
{
	@Autowired
	public PersonService personService;

//	public PersonService getPersonService()
//	{
//		return personService;
//	}
//
//	
//	
//	public void setPersonService(PersonService personService)
//	{
//		this.personService = personService;
//	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView doIndex()
	{
		ModelAndView mv = new ModelAndView();
		List<Person> list = personService.showAll();
		mv.addObject("message", "tt");
		mv.setViewName("index");
		mv.addObject("list", list);
		return mv;
	}
	
	//分页查询数据 
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView doPage(@RequestParam(value = "page", required = false ,defaultValue="1") int page2)
	{
		Page page = new Page();
		page.setCurrentPage(page2);
		System.out.println(page2);
		ModelAndView mv = new ModelAndView();
		List<Person> list = personService.showPersonByPage(page);
		mv.addObject("page",page);
		mv.setViewName("index");
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.GET)
	public ModelAndView addPerson()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addPerson");
		return mv;
	}

	// 跳转到更新的页面
	@RequestMapping(value = "toUpdatePerson", method = RequestMethod.GET)
	public ModelAndView toUpdatePerson(@RequestParam(value = "id", required = false) String id)
	{
		ModelAndView mv = new ModelAndView();
		Person person = personService.getById(id);
		mv.addObject("person", person);
		mv.setViewName("toUpdatePerson");
		return mv;
	}

	// 保存更新用户
	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
	public ModelAndView updatePerson(HttpServletRequest request, Person person)
	{
		personService.updatePerson(person);

		ModelAndView mv = new ModelAndView();
		List<Person> list = personService.showAll();
		mv.addObject("message", "tt");
		mv.setViewName("index");
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/savePerson")
	public ModelAndView savePerson(HttpServletRequest request, Person person)
	{
		personService.addPerson(person);

		ModelAndView mv = new ModelAndView();
		List<Person> list = personService.showAll();
		mv.addObject("message", "tt");
		mv.setViewName("index");
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
	@ResponseBody
	public void deletePerson(@RequestParam(value = "id", required = false) String id, PrintWriter printWriter, HttpServletResponse response)
	{
		System.out.println(id);
		int num = personService.deletePerson(id);
		if (num == 1)
		{
			printWriter.write("{\"success\":\"true\"}");
		} else
		{
			printWriter.write("{\"success\":\"false\"}");
		}

		response.setContentType("application/json");

		printWriter.flush();
		printWriter.close();
	}

	@RequestMapping(value = "/testJSONP")
	@ResponseBody
	public void testJSONP(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.setContentType("text/plain");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String jsonpCallback = request.getParameter("jsonpCallback");// 客户端请求参数
			PrintWriter out = response.getWriter();
			out.write(jsonpCallback + "({\"result\":\"true\"})");
			out.flush();
			out.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 跳转到上传文件的页面
	@RequestMapping(value = "/toUploadFile")
	public ModelAndView toUploadFile()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("uploadFile");
		return mv;
	}

	@RequestMapping(value = "/uploadFile2",method = RequestMethod.POST)
	public ModelAndView uploadFile2(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			File newFile=new File(request.getRealPath("/")+"uploadFile/"+file.getOriginalFilename());	
			//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
	        file.transferTo(newFile);
	        
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		mv.setViewName("uploadFile");
		return mv;
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFile(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		if (!file.isEmpty())
		{
			try
			{
				//获取输出流
	            OutputStream os=new FileOutputStream(request.getRealPath("/")+"uploadFile/"+file.getOriginalFilename());
	            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
	            InputStream is=file.getInputStream();
	            int temp;
	            //一个一个字节的读取并写入
	            while((temp=is.read())!=(-1))
	            {
	                os.write(temp);
	            }
	           os.flush();
	           os.close();
	           is.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			mv.addObject("message", "tt");
			mv.setViewName("uploadFile");
			return mv; 
		} else
		{
			mv.setViewName("uploadFile");
			return mv; 
		}
	}
	
	@RequestMapping(value = "/uploadFile3",method = RequestMethod.POST)
	public ModelAndView uploadFile3(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
			
		return mv;
	}
	
	
}
	


