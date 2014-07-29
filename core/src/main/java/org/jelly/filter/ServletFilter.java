package org.jelly.filter;

import javax.servlet.FilterConfig;
/**
 * Servlet 的识别规则
 * @author Lychie Fan
 * @since 1.3.0
 */
public class ServletFilter extends AbstractServletFilter {

	private String[] rules;
	private static final String WILDCARD = "*";
	private static final String SUFFIX_WILDCARD = ".*";
	private static final String WILDCARD_REGEX = "[a-zA-Z0-9_-]*";
	private static final String SUFFIX_WILDCARD_REGEX = "\\.[a-zA-Z]+[a-zA-Z0-9]";
	
	@Override
	public void init(FilterConfig config) {
		String[] configs = config.getInitParameter("includeServlets").split(",");
		rules = new String[configs.length];
		String rule;
		for(int i = 0; i < configs.length; i++){
			rule = configs[i].trim();
			if(rule.contains(WILDCARD)){
				rule = rule.replace(SUFFIX_WILDCARD, SUFFIX_WILDCARD_REGEX);
				rule = rule.replace(WILDCARD, WILDCARD_REGEX);
			}
			rules[i] = rule;
		}
	}

	@Override
	protected boolean matches(String uri) {
		for(String rule : rules){
			if(uri.matches(rule)){
				return true;
			}
		}
		return false;
	}
}