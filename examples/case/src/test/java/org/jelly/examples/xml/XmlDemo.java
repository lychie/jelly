package org.jelly.examples.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jelly.examples.xml.model.Product;
import org.jelly.helper.Testing;
import org.jelly.model.ClassToken;
import org.jelly.xml.XML;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class XmlDemo {
	
	private static File file;
	private static String basedir;

	/**
	 * 读
	 */
	@Test
	public void read(){
		List<Product> products = XML.getReadableXml().read(file, new ClassToken<List<Product>>(){}.getType());
		Testing.printlnObject(products);
	}

	/**
	 * 写
	 */
	@Test
	public void write(){
		Product product = new Product(1001, "A产品", "广东茂名", 42);
		File file = new File(basedir + "product.xml");
		XML.getWritableXml().write(product, file);
	}

	/**
	 * 写
	 */
	@Test
	public void writeList(){
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1001, "A产品", "广东茂名", 42.7));
		products.add(new Product(1002, "B产品", "广东湛江", 913.5));
		File file = new File(basedir + "product.xml");
		XML.getWritableXml().write(products, file);
	}
	
	@BeforeClass
	public static void prepare(){
		basedir = "src/test/java/org/jelly/examples/xml/resources/";
		file = new File(basedir + "product-info.xml");
	}
	
}