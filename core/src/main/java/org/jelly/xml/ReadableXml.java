package org.jelly.xml;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jelly.exception.ExecutetimeException;
import org.jelly.model.ClassToken;
import org.jelly.util.ClassUtil;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FieldUtil;
import org.jelly.util.MapUtil;
/**
 * 可读的XML
 * @author Lychie Fan
 * @since 1.6.0
 */
public class ReadableXml extends XML {
	
	protected ReadableXml(){}

	/**
	 * <des> 字符串中读取 </des>
	 * @see org.jelly.model.ClassToken#getType()
	 * @since 1.6.0
	 */
	public <E> E read(String xmlText, Type type){
		try {
			return read(DocumentHelper.parseText(xmlText), type);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 文件中读取 </des>
	 * @see org.jelly.model.ClassToken#getType()
	 * @since 1.6.0
	 */
	public <E> E read(File file, Type type){
		try {
			return read(new SAXReader().read(file), type);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 输入流中读取 </des>
	 * @see org.jelly.model.ClassToken#getType()
	 * @since 1.6.0
	 */
	public <E> E read(InputStream in, Type type){
		try {
			return read(new SAXReader().read(in), type);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	// 读取解析
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <E> E read(Document doc, Type type) throws Throwable {
		try {
			initMapping(type);
			List list = new ArrayList();
			Element root = doc.getRootElement();
			Iterator<?> it = root.elementIterator();
			while(it.hasNext()){
				Element e = (Element) it.next();
				Object pojo = ClassUtil.getInstance(pojoClass);
				parseElementAttribute(e, pojo);
				parseChildElement(e, pojo);
				list.add(pojo);
			}
			return result(type, list);
		} catch (Throwable e) {
			throw e;
		}
	}
	
	// 解析元素属性
	private void parseElementAttribute(Element e, Object pojo) throws Throwable {
		Iterator<?> it = e.attributeIterator();
		while(it.hasNext()){
			Attribute attr = (Attribute) it.next();
			if(mapping.containsValue(attr.getName())){
				setAttribute(pojo, attr.getName(), attr.getText());
			}
		}
	}
	
	// 解析子元素
	private void parseChildElement(Element root, Object pojo) throws Throwable {
		Iterator<?> it = root.elementIterator();
		while(it.hasNext()){
			Element e = (Element) it.next();
			parseElementAttribute(e, pojo);
			if(hasChildElement(e)){
				parseChildElement(e, pojo);
			}else{
				if(mapping.containsValue(e.getName())){
					setAttribute(pojo, e.getName(), e.getText());
				}
			}
		}
	}
	
	// 填充属性
	private void setAttribute(Object pojo, String lable, String text){
		String field = MapUtil.findKey(mapping, lable);
		Class<?> fieldType = FieldUtil.getFieldType(pojoClass, field);
		Object value = ConvertUtil.objectValue(text, fieldType);
		FieldUtil.setFieldValue(pojo, field, value);
	}
	
	// 元素是否有子元素
	private boolean hasChildElement(Element root) {
		return root.elementIterator().hasNext();
	}
	
	// 处理返还结果
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <E> E result(Type type, List list){
		Class<?> rawType = ClassToken.getRawType(type);
		if(ClassUtil.isInstanceOf(rawType, List.class)){
			return (E) list;
		}else if(ClassUtil.isInstanceOf(rawType, Set.class)){
			return (E) new HashSet(list);
		}
		return (E) list.get(0);
	}
	
}