package org.jelly.util;

import java.math.BigDecimal;
import java.util.Random;
import org.jelly.exception.ExecutetimeException;
/**
 * <p> <b> @描述：</b> 随机种子常用操作工具类
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-28
 * <p> <b> @since 0.1.0 </b>
 */
public class RandomSeedUtil {

	private RandomSeedUtil(){}

	private static final int DISPLAYABLE_MIN_INDEX = 0 + 33;
	private static final int DISPLAYABLE_MAX_INDEX = 127 - 1;
	
	// 保证 Random 多线程安全
	private static final ThreadLocal<Random> THREADLOCAL = new ThreadLocal<Random>(){
		
		@Override
		protected Random initialValue() {
			return new Random();
		}
		
	};
	
	/**
	 * <des> 获取 Random 实例, 线程安全 </des>
	 * @return Random
	 * @since 1.0.0
	 */
	public static Random getRandom(){
		return THREADLOCAL.get();
	}
	
	/**
	 * <des> 产生 [0, x] 区间随机数, 调该方法要求 x > 0 </des>
	 * @param x > 0
	 * @return [0, x] 区间随机数
	 * @since 1.0.0
	 */
	public static int intSeed(int x){
		return intSeed(0, x);
	}
	
	/**
	 * <des> 产生 [x, y] 区间随机数, 调该方法要求 y > x >= 0 </des>
	 * @param x >= 0
	 * @param y > x
	 * @return [x, y] 区间随机数
	 * @since 1.0.0
	 */
	public static int intSeed(int x, int y){
		if(x < 0){ // ensure x >= 0
			throw new ExecutetimeException(new IllegalArgumentException("x 必须大于等于 0"));
		}
		if(x >= y){ // ensure y > x, of course y > 0
			throw new ExecutetimeException( new IllegalArgumentException("y 必须大于 x"));
		}
		return x + getRandom().nextInt(y - x + 1);
	}
	
	/**
	 * <des> 产生 [0, x] 区间随机数, 调该方法要求 x > 0 </des>
	 * @param x > 0
	 * @return [0, x] 区间随机数
	 * @since 1.0.0
	 */
	public static long longSeed(long x){
		return longSeed(0, x);
	}
	
	/**
	 * <des> 产生 [x, y] 区间随机数, 调该方法要求 y > x >= 0 </des>
	 * @param x >= 0
	 * @param y > x
	 * @return [x, y] 区间随机数
	 * @since 1.0.0
	 */
	public static long longSeed(long x, long y){
		if(x < 0){ // ensure x >= 0
			throw new ExecutetimeException(new IllegalArgumentException("x 必须大于等于 0"));
		}
		if(x >= y){ // ensure y > x, of course y > 0
			throw new ExecutetimeException( new IllegalArgumentException("y 必须大于 x"));
		}
		return Math.abs(getRandom().nextLong() % (y - x + 1)) + x;
	}
	
	/**
	 * <des> 产生 [0, x] 区间随机小数, 调该方法要求 x > 0 </des>
	 * @param x > 0
	 * @return [0, x] 区间随机小数
	 * @since 1.0.0
	 */
	public static float floatSeed(float x){
		return (float) doubleSeed(0., x);
	}
	
	/**
	 * <des> 产生 [x, y] 区间随机小数, 调该方法要求 y > x >= 0 </des>
	 * @param x >= 0
	 * @param y > x
	 * @return [x, y] 区间随机小数
	 * @since 1.0.0
	 */
	public static float floatSeed(float x, float y){
		return (float) doubleSeed(x, y);
	}
	
	/**
	 * <des> 产生 [0, x] 区间随机小数, 调该方法要求 x > 0 </des>
	 * @param x > 0
	 * @return [0, x] 区间随机小数
	 * @since 1.0.0
	 */
	public static double doubleSeed(double x){
		return doubleSeed(0., x);
	}
	
	/**
	 * <des> 产生 [x, y] 区间随机小数, 调该方法要求 y > x >= 0 </des>
	 * @param x >= 0
	 * @param y > x
	 * @return [x, y] 区间随机小数
	 * @since 1.0.0
	 */
	public static double doubleSeed(double x, double y){
		if(x < 0){ // ensure x >= 0
			throw new ExecutetimeException(new IllegalArgumentException("x 必须大于等于 0"));
		}
		if(x >= y){ // ensure y > x, of course y > 0
			throw new ExecutetimeException( new IllegalArgumentException("y 必须大于 x"));
		}
		BigDecimal xbd = new BigDecimal(String.valueOf(x));
		BigDecimal ybd = new BigDecimal(String.valueOf(y));
		double diff = ybd.subtract(xbd).doubleValue();
		xbd = xbd.add(new BigDecimal(String.valueOf(getRandom().nextDouble() * diff)));
		return xbd.doubleValue();
	}
	
	/**
	 * <des> 随机产生 true 或 false </des>
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean boolSeed(){
		return intSeed(0, 1) == 0 ? false : true;
	}
	
	/**
	 * <des> 随机产生有效的可显示的字符 ( 仅支持产生有效的可显示的字符 ) </des>
	 * @param x > 32
	 * @param y < 127
	 * @return 随机产生的字符
	 * @since 1.0.0
	 */
	public static char charSeed(char x, char y){
		if(x < DISPLAYABLE_MIN_INDEX){ // ensure x >= 33('!')
			throw new ExecutetimeException(
				new IllegalArgumentException("x 必须大于等于 '!'\n维基地址 http://zh.wikipedia.org/zh-cn/ASCII")
			);
		}
		if(x >= y){ // ensure y > x, of course y > 33
			throw new ExecutetimeException(
				new IllegalArgumentException("y 必须大于 x\n维基地址 http://zh.wikipedia.org/zh-cn/ASCII")
			);
		}
		if(y > DISPLAYABLE_MAX_INDEX){ // ensure y <= 126('~'), [0, 127] --> [33, 126] 
			throw new ExecutetimeException(
				new IllegalArgumentException("y 小于等于 to '~'\n维基地址 http://zh.wikipedia.org/zh-cn/ASCII")
			);
		}
		return (char) (getRandom().nextInt(y - x + 1) + x);
	}
}