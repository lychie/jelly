package org.jelly.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jelly.code.EncodingCode;
import org.jelly.exception.ExecutetimeException;
import org.jelly.util.ClassUtil;
import org.jelly.util.CollectionUtil;
import org.jelly.util.FieldUtil;
import org.jelly.util.StringUtil;
/**
 * 可写的XML
 * @author Lychie Fan
 * @since 1.6.0
 */
public class WritableXml extends XML {

	private String rootName;
	private OutputFormat format;
	
	protected WritableXml(){
		format = OutputFormat.createPrettyPrint();
		format.setNewLineAfterDeclaration(false);
		format.setEncoding(EncodingCode.UTF_8.toCode());
	}
	
	/**
	 * <des> 写出到文件 </des>
	 * @since 1.6.0
	 */
	public void write(Object object, File file){
		try {
			write(object, new XMLWriter(new FileWriter(file), format));
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 写出到输出流 </des>
	 * @since 1.6.0
	 */
	public void write(Object object, OutputStream out){
		try {
			write(object, new XMLWriter(out, format));
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 写出到字符串 </des>
	 * @since 1.6.0
	 */
	public String asXml(Object object){
		return buildDocument(object).asXML();
	}
	
	// 写出
	private void write(Object object, XMLWriter xmlWriter) throws Throwable {
		xmlWriter.write(buildDocument(object));
		xmlWriter.flush();
		xmlWriter.close();
	}
	
	// 构建文档
	private Document buildDocument(Object object){
		List<?> list = buildList(object);
		initMapping(list.get(0).getClass());
		Document doc = DocumentHelper.createDocument();
		String pojoName = list.get(0).getClass().getSimpleName();
		pojoName = StringUtil.convertUpperCaseLetter(pojoName, "-").substring(1);
		Element superRoot = buildSuperRoot(doc, pojoName, list.size());
		for(Object obj : list){
			Element root = buildRoot(doc, superRoot, pojoName);
			for(String key : mapping.keySet()){
				Element e = root.addElement(mapping.get(key));
				e.setText(FieldUtil.getFieldValue(obj, key).toString());
			}
		}
		return doc;
	}
	
	// 构建列表
	private List<?> buildList(Object pojo){
		if(ClassUtil.isInstanceOf(pojo, Collection.class)){
			return CollectionUtil.asList((Collection<?>) pojo);
		}else {
			List<Object> list = new ArrayList<Object>(1);
			list.add(pojo);
			return list;
		}
	}
	
	// 构建根标签
	private Element buildSuperRoot(Document doc, String pojoName, int size){
		if(size > 1){
			if(rootName == null){
				return doc.addElement(pojoName + "s");
			}else{
				return doc.addElement(rootName);
			}
		}
		return null;
	}
	
	// 构建根标签
	private Element buildRoot(Document doc, Element superRoot, String text){
		return superRoot == null ? doc.addElement(text) : superRoot.addElement(text);
	}

	// 设置根标签名称
	public WritableXml setRoot(String rootName) {
		this.rootName = rootName;
		return this;
	}

	// 设置编码
	public WritableXml setEncoding(String encoding) {
		format.setEncoding(encoding);
		return this;
	}
	
}
