package org.jelly.examples.orm.controller;

import javax.annotation.Resource;
import org.jelly.examples.orm.dao.PersonDao;
import org.jelly.examples.orm.model.Person;
import org.jelly.model.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author Lychie Fan
 */
@Controller
@RequestMapping("/person")
public class PersonController {

	private static final int PAGE_SIZE = 1;
	@Resource
	private PersonDao personDao;
	
	@RequestMapping(value = "/list/{pageNum}", method = RequestMethod.GET)
	public String findByPager(Model model, @PathVariable int pageNum){
		Pager<Person> pager = personDao.findByPager(pageNum, PAGE_SIZE);
		model.addAttribute("pager", pager);
		return "person_list";
	}
	
}