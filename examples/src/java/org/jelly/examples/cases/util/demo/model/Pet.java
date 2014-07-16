package org.jelly.examples.cases.util.demo.model;

import org.jelly.helper.Testing;
import org.jelly.util.ClassUtil;

public class Pet <E> {
	
	public Pet(){
		Testing.printlnObject(toString());
		Class<?> clazz = ClassUtil.getSuperclassGenericType(this.getClass());
		Testing.printlnObject(clazz);
	}

	@Override
	public String toString() {
		return "I am not a pet!";
	}
	
}