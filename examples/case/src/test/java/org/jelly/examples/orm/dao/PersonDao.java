package org.jelly.examples.orm.dao;

import org.hibernate.SessionFactory;
import org.jelly.examples.orm.model.Person;
import org.jelly.orm.HibernateDao;
/**
 * @author Lychie Fan
 */
public class PersonDao extends HibernateDao <Person, Integer> {

	public PersonDao(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

}