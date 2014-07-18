package org.jelly.examples.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jelly.code.DateFormatCode;
import org.jelly.examples.json.model.Person;
import org.jelly.helper.Testing;
import org.jelly.json.Json;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FileUtil;
import org.jelly.util.MethodUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.gson.reflect.TypeToken;
/**
 * @author Lychie Fan
 */
public class JsonDemo {

	private static Person person;
	private static String basedir;
	private static String[] jsons;
	private static final List<Person> persons = new ArrayList<Person>();
	
	/**
	 * 一个简单的例子
	 */
	@Test
	public void simpleToJson(){
		String json = Json.getDefault().toJson("<p>Hi there</p>");
		Testing.printlnObject(json);
	}
	
	/**
	 * 序列化对象
	 */
	@Test
	public void pojoToJson(){
		String json = Json.getDefault().toJson(person);
		Testing.printlnObject(json);
	}
	
	/**
	 * 序列化对象集合
	 */
	@Test
	public void pojoListToJson(){
		String json = Json.getDefault().toJson(persons);
		Testing.printlnObject(json);
	}
	
	/**
	 * 键值对的形式序列化对象
	 */
	@Test
	public void mapToJson(){
		String json = Json.getDefault().toJson("person", person);
		Testing.printlnObject(json);
	}
	
	/**
	 * 日期格式化模式
	 */
	@Test
	public void formatDate(){
		String pattern = DateFormatCode.SHORT_VIRGULE.toCode();
		String json = Json.getDefault().setDateFormat(pattern).toJson(person);
		Testing.printlnObject(json);
	}
	
	/**
	 * 排除字段
	 */
	@Test
	public void skipField(){
		String json = Json.getDefault().setExclusions("sex").toJson(person);
		Testing.printlnObject(json);
	}
	
	/**
	 * 排除类型
	 */
	@Test
	public void skipType(){
		String json = Json.getDefault().setExclusions(String.class).toJson(person);
		Testing.printlnObject(json);
	}
	
	/**
	 * 解析json成对象
	 */
	@Test
	public void fromJsonToPojo(){
		String json = jsons[0];
		Person person = Json.getDefault().fromJson(json, Person.class);
		Testing.printlnObject(json);
		Testing.printlnObject(person);
	}
	
	/**
	 * 解析json成对象集合
	 */
	@Test
	public void fromJsonToPojoList(){
		String json = jsons[1];
		List<Person> person = Json.getDefault().fromJson(json, new TypeToken<List<Person>>(){});
		Testing.printlnObject(json);
		Testing.printlnObject(person);
	}
	
	/**
	 * 解析json为散列表
	 */
	@Test
	public void fromJsonToMap(){
		String json = jsons[2];
		Map<String, List<Person>> map = Json.getDefault().fromJson(json, new TypeToken<Map<String, List<Person>>>(){});
		Testing.printlnObject(json);
		Testing.printlnObject(map.get("persons"));
	}
	
	/**
	 * 排除字段
	 */
	@Test
	public void fromJsonSkipField(){
		String json = jsons[0];
		Person person = Json.getDefault().setExclusions("sex").fromJson(json, Person.class);
		Testing.printlnObject(json);
		Testing.printlnObject(person);
	}
	
	// 预设数据
	@BeforeClass
	public static void prepare(){
		basedir = "src/test/java/org/jelly/examples/json/resources/";
		jsons = FileUtil.readLineFile(basedir + "person.json");
		String[] lines = FileUtil.readLineFile(basedir + "person.data");
		Class<?>[] types = {String.class, String.class, String.class, Date.class};
		for(String line : lines){
			String[] datas = line.split("	");
			Object[] values = ConvertUtil.objectValues(datas, types);
			persons.add(MethodUtil.invokeConstructor(Person.class, values, types));
		}
		person = persons.get(0);
	}
}