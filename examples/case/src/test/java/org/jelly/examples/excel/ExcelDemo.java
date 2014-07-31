package org.jelly.examples.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jelly.examples.excel.model.Person;
import org.jelly.excel.Format;
import org.jelly.excel.ReadableExcel;
import org.jelly.excel.Setting;
import org.jelly.excel.WritableExcel;
import org.jelly.helper.Testing;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FileUtil;
import org.jelly.util.MethodUtil;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class ExcelDemo {

	private static File file;
	private static String basedir;
	private static final List<Person> persons = new ArrayList<Person>();

	/**
	 * 写出
	 */
	@Test
	public void write(){
		new WritableExcel(new Setting()).write(file, persons);
		Testing.openFile(file);
	}

	/**
	 * 读入
	 */
	@Test
	public void read(){
		List<Person> personList = new ReadableExcel().read(file, Person.class);
		Testing.printlnObject(personList);
	}

	/**
	 * 写出
	 */
	@Test
	public void writeByMapping(){
		WritableExcel excel = new WritableExcel(new Setting());
		excel.setMapping("id", "编号");
		excel.setMapping("name", "姓名");
		excel.setMapping("sex", "性别");
		excel.setMapping("phone", "电话");
		excel.setMapping("owe", "货币", Format.FLOAT_ACCOUNTING_COLOR);
		excel.setMapping("address", "地址");
		excel.setMapping("date", "日期");
		excel.write(file, persons);
		Testing.openFile(file);
	}

	/**
	 * 读入
	 */
	@Test
	public void readByMapping(){
		ReadableExcel excel = new ReadableExcel();
		excel.setMapping("id", "编号");
		excel.setMapping("name", "姓名");
		excel.setMapping("sex", "性别");
		excel.setMapping("phone", "电话");
		excel.setMapping("owe", "货币");
		excel.setMapping("address", "地址");
		excel.setMapping("date", "日期");
		List<Person> personList = excel.read(file, Person.class);
		Testing.printlnObject(personList);
	}

	/**
	 * 追加
	 */
	@Test
	public void append(){
		new WritableExcel(new Setting()).append(file, persons);
		Testing.openFile(file);
	}
	
	// 预设数据
	@BeforeClass
	public static void prepare(){
		basedir = "src/test/java/org/jelly/examples/excel/resources/";
		file = new File(basedir + "bill.xls");
		String[] lines = FileUtil.readLineFile(basedir + "persons.data");
		Class<?>[] types = {String.class, String.class, String.class, double.class, String.class, Date.class};
		for(String line : lines){
			String[] datas = line.split("	");
			Object[] values = ConvertUtil.objectValues(datas, types);
			persons.add(MethodUtil.invokeConstructor(Person.class, values, types));
		}
	}
}