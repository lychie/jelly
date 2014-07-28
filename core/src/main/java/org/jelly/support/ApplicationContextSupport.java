package org.jelly.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 通过在 Spring 配置文件中配置 <br>
 * &lt;bean class="org.jelly.support.ApplicationContextSupport" /&gt;<br>
 * 或通过 registerApplicationContext 方法手动设置 ApplicationContext
 * @author Lychie Fan
 * @since 1.2.0
 */
public class ApplicationContextSupport implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	private ApplicationContextSupport(){}
	
	@SuppressWarnings("unchecked")
	public static <E> E getBean(String name){
		return (E) applicationContext.getBean(name);
	}
	
	public static <E> E getBean(Class<E> className){
		return applicationContext.getBean(className);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextSupport.applicationContext = applicationContext;
	}
	
	public static void registerApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextSupport.applicationContext = applicationContext;
	}
	
}