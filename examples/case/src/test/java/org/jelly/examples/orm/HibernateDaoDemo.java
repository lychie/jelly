package org.jelly.examples.orm;

import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jelly.examples.orm.dao.PersonDao;
import org.jelly.examples.orm.model.Person;
import org.jelly.examples.orm.util.HibernateUtil;
import org.jelly.helper.Testing;
import org.jelly.model.Pager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class HibernateDaoDemo {

	private static PersonDao personDao;
	private static Transaction transaction;
	
	@BeforeClass
	public static void startup(){
		personDao = new PersonDao(HibernateUtil.getSessionFactory());
		transaction = personDao.beginTransaction();
	}
	
	@AfterClass
	public static void shutdown(){
		transaction.commit();
	}
	
	/**
	 * 查找全部
	 */
	@Test
	public void getAll(){
		List<Person> personList = personDao.getAll();
		Testing.printlnObject(personList);
	}

	/**
	 * 查找
	 */
	@Test
	public void find(){
		List<Person> personList = personDao.find(Restrictions.eq("sex", "女"));
		Testing.printlnObject(personList);
	}

	/**
	 * 查找
	 */
	@Test
	public void findUnique(){
		Person person = personDao.findUnique(Restrictions.eq("name", "叶水燕"));
		Testing.printlnObject(person);
	}

	/**
	 * 查找
	 */
	@Test
	public void findLike(){
		List<Person> persons = personDao.find(Restrictions.like("name", "%叶%"));
		Testing.printlnObject(persons);
	}

	/**
	 * 查找
	 */
	@Test
	public void findPager(){
		int currentPage = 1;
		int pageSize = 5;
		Pager<Person> pager = personDao.findByPager(currentPage, pageSize);
		Testing.printlnObject(pager.getResult());
	}

	/**
	 * 保存
	 */
	@Test
	public void save(){
		Person person = new Person();
		person.setName("李文静");
		person.setSex("女");
		person.setAge(22);
		person.setBirthday(new Date());
		person.setAddress("广东茂名");
		Integer id = personDao.save(person);
		Testing.printlnObject(id);
	}

	/**
	 * 删除
	 */
	@Test
	public void delete(){
		personDao.deleteById(20);
	}
}