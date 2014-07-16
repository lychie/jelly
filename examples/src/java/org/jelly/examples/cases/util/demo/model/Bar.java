package org.jelly.examples.cases.util.demo.model;

public class Bar {

	private int baz;
	private String qux;
	private static double var;
	
	public Bar(){}
	
	public Bar(String qux){
		this(1001, qux);
	}
	
	private Bar(int baz, String qux){
		this.baz = baz;
		this.qux = qux;
	}
	
	void setBaz(int baz){
		this.baz = baz;
	}
	
	int getBaz(){
		return this.baz;
	}

	static double getVar() {
		return var;
	}

	static void setVar(double var) {
		Bar.var = var;
	}

	String getQux() {
		return qux;
	}

	void setQux(String qux) {
		this.qux = qux;
	}

	@Override
	public String toString() {
		return baz + "-" + var + " " + qux;
	}
	
}