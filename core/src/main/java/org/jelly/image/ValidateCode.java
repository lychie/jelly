package org.jelly.image;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.jelly.image.helper.ValidateCodeConfigHelper;
import org.jelly.util.ArrayUtil;
import org.jelly.util.CollectionUtil;
import org.jelly.util.FieldUtil;
import com.google.code.kaptcha.servlet.KaptchaServlet;
/**
 * 验证码
 * @author Lychie Fan
 * @since 1.7.0
 */
public class ValidateCode extends KaptchaServlet implements ValidateCodeConfigHelper {

	private Properties prop;
	private Map<String, String> config = new HashMap<String, String>(10, 1);
	private static final long serialVersionUID = 4004634481345251378L;
	
	@Override
	public void init(ServletConfig conf) throws ServletException {
		initConfig();
		Enumeration<?> initParams = conf.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String key = (String) initParams.nextElement();
			String value = conf.getInitParameter(key);
			key = FieldUtil.getFieldValue(ValidateCodeConfigHelper.class, key);
			if(config.containsKey(value)){
				value = shuffle(config.get(value));
			}
			prop.put(key, value);
		}
		config = null;
		super.init(conf);
	}
	
	// 初始化配置项
	private void initConfig(){
		prop = FieldUtil.getFieldValue(this, "props");
		prop.put(border, "no");
		prop.put(borderColor, "black");
		prop.put(borderThickness, "1");
		prop.put(width, "230");
		prop.put(height, "90");
		prop.put(fontSize, "70");
		prop.put(charLength, "4");
		prop.put(producerImpl, "com.google.code.kaptcha.impl.DefaultKaptcha");
		prop.put(textproducerImpl, "com.google.code.kaptcha.text.impl.DefaultTextCreator");
		prop.put(fontColor, "black");
		prop.put(charSpace, "1");
		prop.put(noiseImpl, "com.google.code.kaptcha.impl.DefaultNoise");
		prop.put(noiseColor, "black");
		prop.put(backgroundImpl, "com.google.code.kaptcha.impl.DefaultBackground");
		prop.put(backgroundClrTo, "white");
		prop.put(wordrendererImpl, "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
		prop.put(fontFamily, "Arial,Helvetica,Times New Roman,Liberation Mono");
		prop.put(style, wave);
		prop.put(charString, shuffle(NU));
		prop.put(sessionKey, SESSION_KEY);
		config.put("N", N);
		config.put("L", L);
		config.put("U", U);
		config.put("NL", NL);
		config.put("NU", NU);
		config.put("LU", LU);
		config.put("NLU", NLU);
		config.put("wave", wave);
		config.put("shadow", shadow);
		config.put("fishEye", fishEye);
	}
	
	// 打乱顺序
	private String shuffle(String text){
		if(!text.contains(",")) return text;
		List<String> list = CollectionUtil.asList(text.split(","));
		Collections.shuffle(list);
		String string = ArrayUtil.toSimpleString(ArrayUtil.asArray(list));
		return string.replaceAll(", ", "");
	}
	
}