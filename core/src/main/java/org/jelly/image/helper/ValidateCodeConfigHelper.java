package org.jelly.image.helper;

import com.google.code.kaptcha.Constants;
/**
 * 验证码配置项助手
 * @author Lychie Fan
 * @since 1.7.0
 */
public interface ValidateCodeConfigHelper {

	/**
	 * 边框
	 */
	String border  = Constants.KAPTCHA_BORDER;
	/**
	 * 边框颜色
	 */
	String borderColor = Constants.KAPTCHA_BORDER_COLOR;
	/**
	 * 边框厚度
	 */
	String borderThickness = Constants.KAPTCHA_BORDER_THICKNESS;
	/**
	 * 验证码样式
	 */
	String style = Constants.KAPTCHA_OBSCURIFICATOR_IMPL;
	/**
	 * 验证码宽度
	 */
	String width = Constants.KAPTCHA_IMAGE_WIDTH;
	/**
	 * 验证码高度
	 */
	String height = Constants.KAPTCHA_IMAGE_HEIGHT;
	/**
	 * 验证码字体大小
	 */
	String fontSize = Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE;
	/**
	 * 验证码字体颜色
	 */
	String fontColor = Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR;
	/**
	 * 验证码字体
	 */
	String fontFamily = Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES;
	/**
	 * 验证码字符
	 */
	String charString = Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING;
	/**
	 * 验证码字符长度
	 */
	String charLength = Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH;
	/**
	 * 验证码字符间隔空隙
	 */
	String charSpace = Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE;
	/**
	 * 干扰线
	 */
	String noiseImpl = Constants.KAPTCHA_NOISE_IMPL;
	/**
	 * 干扰线颜色
	 */
	String noiseColor = Constants.KAPTCHA_NOISE_COLOR;
	/**
	 * 验证码背景
	 */
	String backgroundImpl = Constants.KAPTCHA_BACKGROUND_IMPL;
	/**
	 * PRODUCER IMPL
	 */
	String producerImpl = Constants.KAPTCHA_PRODUCER_IMPL;
	/**
	 * TEXTPRODUCER IMPL
	 */
	String textproducerImpl = Constants.KAPTCHA_TEXTPRODUCER_IMPL;
	/**
	 * BACKGROUND CLR TO
	 */
	String backgroundClrTo = Constants.KAPTCHA_BACKGROUND_CLR_TO;
	/**
	 * WORDRENDERER IMPL
	 */
	String wordrendererImpl = Constants.KAPTCHA_WORDRENDERER_IMPL;
	/**
	 * Session Key
	 */
	String sessionKey = Constants.KAPTCHA_SESSION_CONFIG_KEY;
	/**
	 * Session Key Value
	 */
	String SESSION_KEY = "idCode";
	/**
	 * 鱼眼(验证码样式)
	 */
	String fishEye = "com.google.code.kaptcha.impl.FishEyeGimpy";
	/**
	 * 阴影(验证码样式)
	 */
	String shadow = "com.google.code.kaptcha.impl.ShadowGimpy";
	/**
	 * 波纹(验证码样式)
	 */
	String wave = "com.google.code.kaptcha.impl.WaterRipple";
	/**
	 * 数值(验证码字符)
	 */
	String N = "0,1,2,3,4,5,6,7,8,9";
	/**
	 * 小写英文字母(验证码字符)
	 */
	String L = "q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
	/**
	 * 大写英文字母(验证码字符)
	 */
	String U = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M";
	/**
	 * 数值 & 小写英文字母(验证码字符)
	 */
	String NL = N + L;
	/**
	 * 数值 & 大写英文字母(验证码字符)
	 */
	String NU = N + U;
	/**
	 * 小写英文字母 & 大写英文字母(验证码字符)
	 */
	String LU = L + U;
	/**
	 * 数值 & 小写英文字母 & 大写英文字母(验证码字符)
	 */
	String NLU = N + L + U;
	
}