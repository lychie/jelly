package org.jelly.support;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.jelly.orm.HibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Lychie Fan
 * @since 1.2.0
 */
public class DaoSupport <E, K extends Serializable> extends HibernateDao <E, K> {

	@Autowired
	protected final void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
}