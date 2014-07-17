package org.jelly.examples.cases.util.model;

public class Foobar {

	private int bar = -1;
	private String baz = "none";
	private static double version = 1.0;

	@Override
	public String toString() {
		return bar + "  " + baz + "  [" + version + "]";
	}
	
}