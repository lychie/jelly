package org.jelly.examples.cases.util.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jelly.examples.cases.util.demo.model.User;
import org.jelly.helper.Testing;
import org.jelly.util.ClassPathUtil;
import org.jelly.util.CollectionUtil;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FileUtil;
import org.jelly.util.MethodUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class CollectionUtilDemo {
	
	private static final List<User> users = new ArrayList<User>();
	
	/**
	 * 排序集合里面的对象
	 */
	@Test
	public void sortByAsc(){
		
		prepare();
		
		CollectionUtil.sortByAsc(users, "id");
		// 解开注释看效果
//		CollectionUtil.sortByAsc(users, "sex");
//		CollectionUtil.sortByAsc(users, "name");
//		CollectionUtil.sortByAsc(users, "valid");
//		CollectionUtil.sortByAsc(users, "registerTime");
		
		Testing.printlnObject(users);
		
	}

	/**
	 * 判定集合是否为空
	 */
	@Test
	public void isEmpty(){
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		list2.add(null);
		list3.add("Lychie");
		Testing.printlnObject(CollectionUtil.isEmpty(null));
		Testing.printlnObject(CollectionUtil.isEmpty(list1));
		Testing.printlnObject(CollectionUtil.isEmpty(list2));
		Testing.printlnObject(CollectionUtil.isEmpty(list3));
	}
	
	/**
	 * 将集合转换为数组表示
	 */
	@Test
	public void toArray(){
		List<String> list = new ArrayList<String>();
		list.add("Lychie");
		list.add("Fan");
		String[] strings = CollectionUtil.toArray(list);
		Testing.printlnObject(strings);
	}
	
	/**
	 * 参数作为集合
	 */
	@Test
	public void asList(){
		List<String> list = CollectionUtil.asList("Lychie", "Fan");
		Testing.printlnObject(list);
	}
	
	/**
	 * 创建集合
	 */
	@Test
	public void newList(){
		List<Map<String, Set<Object>>> list = CollectionUtil.newList();
		Testing.printlnObject(list);
	}
	
	/**
	 * 预设数据
	 */
	// @BeforeClass
	public static void prepare(){
		File file = ClassPathUtil.getClassPathFile("user.data");
		String[] lines = FileUtil.readLineFile(file);
		Class<?>[] types = {int.class, String.class, String.class, boolean.class, Date.class};
		final String tab = "	";
		for(String line : lines){
			String[] datas = line.split(tab);
			Object[] values = ConvertUtil.objectValues(datas, types);
			users.add(MethodUtil.invokeConstructor(User.class, values, types));
		}
	}
}