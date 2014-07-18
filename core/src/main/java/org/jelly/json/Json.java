package org.jelly.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.jelly.code.DateFormatCode;
import org.jelly.exception.ExecutetimeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/**
 * Json
 * @author Lychie Fan
 * @since 1.1.0
 */
public class Json {

	private String dateFormat;
	private boolean serializeNulls;
	private GsonBuilder gsonBuilder;
	private JsonExcluder exclusions;
	
	public Json() {
		this(true);
	}
	
	/**
	 * <des> 获取预设的实例。默认地, 使用 'yyyy-MM-dd' 
	 * 作为日期格式化的模式, 并序列化对象成员属性值为 null 的属性 </des>
	 * @since 1.1.0
	 */
	public static Json getDefault(){
		return new Json(true).setDateFormat(DateFormatCode.SHORT_HYPHEN.toCode());
	}
	
	private Json(boolean serializeNulls){
		if(serializeNulls){
			serializeNulls();
		}else{
			disableSerializeNulls();
		}
	}
	
	/**
	 * <des> 序列化对象 </des>
	 * @param object 若需要以键值对的形式序列化, 可以通过在外部构造 Map 作为参数
	 * @since 1.1.0
	 */
	public String toJson(Object object){
		return createGson().toJson(object);
	}
	
	/**
	 * <des> 以键值对的形式序列化对象 </des>
	 * @param key   键
	 * @param value 值
	 * @since 1.1.0
	 */
	public String toJson(String key, Object value){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return toJson(map);
	}
	
	/**
	 * <des> 反序列化 json 字符串 </des>
	 * @param json json 字符串
	 * @param pojoClass 类型
	 * @since 1.1.0
	 */
	public <E> E fromJson(String json, Class<E> pojoClass){
		return createGson().fromJson(json, pojoClass);
	}
	
	/**
	 * <des> 反序列化 json 字符串 </des>
	 * @param json json 字符串
	 * @param typeToken 反序列化成为的类型。如 new TypeToken&lt;List&lt;Pojo&gt;&gt;(){}
	 * @since 1.1.0
	 */
	public <E> E fromJson(String json, TypeToken<E> typeToken){
		return createGson().fromJson(json, typeToken.getType());
	}
	
	/**
	 * <des> 写出对象 </des>
	 * @param value 写出的对象
	 * @param response HttpServletResponse
	 * @since 1.1.0
	 */
	public void outputObject(Object value, HttpServletResponse response) {
		Json.outputJson(toJson(value), response);
	}
	
	/**
	 * <des> 写出对象, 以键值对的形式 </des>
	 * @param key  键
	 * @param value  值
	 * @param response HttpServletResponse
	 * @since 1.1.0
	 */
	public void outputObject(String key, Object value, HttpServletResponse response) {
		Json.outputJson(toJson(key, value), response);
	}
	
	/**
	 * <des> 写出 json 字符串 </des>
	 * @param json json 字符串 
	 * @param response HttpServletResponse
	 * @since 1.1.0
	 */
	public static void outputJson(String json, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
			out = response.getWriter();
	        out.write(json);
	        out.flush();
		} catch (IOException e) {
			throw new ExecutetimeException(e);
		} finally {
			if(out != null){
				out.close();
			}
		}
	}

	/**
	 * <des> 设定日期类型序列化的格式 </des>
	 * @param pattern 日期模式串
	 * @see org.jelly.code.DateFormatCode
	 * @since 1.1.0
	 */
	public Json setDateFormat(String pattern) {
		this.dateFormat = pattern;
		return this;
	}

	/**
	 * <des> 设定对象序列化时需要排除的字段属性或类型 </des>
	 * @param exclusions 排除的字段属性或类型
	 * @since 1.1.0
	 */
	public Json setExclusions(Object... exclusions) {
		this.exclusions = new JsonExcluder(exclusions);
		return this;
	}

	/**
	 * <des> 序列化对象成员属性值为 null 的属性 </des>
	 * @since 1.1.0
	 */
	public Json serializeNulls() {
		this.serializeNulls = true;
		return init();
	}

	/**
	 * <des> 不序列化对象成员属性值为 null 的属性 </des>
	 * @since 1.1.0
	 */
	public Json disableSerializeNulls() {
		this.serializeNulls = false;
		return init();
	}
	
	// init
	private Json init(){
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.enableComplexMapKeySerialization();
		if(serializeNulls){
			gsonBuilder.serializeNulls();
		}
		return this;
	}
	
	// create gson
	private Gson createGson(){
		gsonBuilder.setDateFormat(dateFormat);
		if(exclusions != null){
			gsonBuilder.setExclusionStrategies(exclusions);
		}
		return gsonBuilder.create();
	}
	
}