package org.jelly.examples.orm.dao;

import org.jelly.examples.orm.model.Person;
import org.jelly.support.DaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends DaoSupport<Person, Integer> {


}