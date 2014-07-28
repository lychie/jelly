package org.jelly.examples.orm.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jelly.examples.orm.model.Person;
import org.jelly.orm.HibernateDao;
import org.jelly.support.MySqlChineseOrderSupport;
/**
 * @author Lychie Fan
 */
public class PersonDao extends HibernateDao <Person, Integer> {

	public PersonDao(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public List<Person> findByHql(){
		String hql = "from Person where id > ? and sex = ?";
		return find(hql, 2, "女");
	}
	
	public List<Person> findByCriteria(){
		Criteria criteria = createCriteria(Restrictions.gt("id", 2), Restrictions.eq("sex", "女"));
		criteria.addOrder(Order.asc("age"));
		return find(criteria);
	}
	
	public List<Person> findByOrder(){
		Criteria criteria = createCriteria();
		criteria.addOrder(MySqlChineseOrderSupport.asc("name"));
		return find(criteria);
	}

}