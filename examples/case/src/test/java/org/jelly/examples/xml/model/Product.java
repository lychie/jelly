package org.jelly.examples.xml.model;
/**
 * @author Lychie Fan
 */
public class Product {

	private int id;
	private String name;
	private String origin;
	private double price;
	
	public Product(){}
	
	public Product(int id, String name, String origin, double price){
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		return id + "    " + name + "    " + price + "    " + origin;
	}
}