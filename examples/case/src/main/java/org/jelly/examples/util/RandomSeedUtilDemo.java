package org.jelly.examples.util;

import org.jelly.helper.Testing;
import org.jelly.util.RandomSeedUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class RandomSeedUtilDemo {

	/**
	 * int 随机种子
	 */
	@Test
	public void intSeed(){
		Testing.printlnObject(RandomSeedUtil.intSeed(5));
		Testing.printlnObject(RandomSeedUtil.intSeed(2, 5));
	}

	/**
	 * long 随机种子
	 */
	@Test
	public void longSeed(){
		Testing.printlnObject(RandomSeedUtil.longSeed(5));
		Testing.printlnObject(RandomSeedUtil.longSeed(2, 5));
	}

	/**
	 * float 随机种子
	 */
	@Test
	public void floatSeed(){
		Testing.printlnObject(RandomSeedUtil.floatSeed(0.5f));
		Testing.printlnObject(RandomSeedUtil.floatSeed(0.2f, 0.5f));
	}

	/**
	 * double 随机种子
	 */
	@Test
	public void doubleSeed(){
		Testing.printlnObject(RandomSeedUtil.doubleSeed(0.5));
		Testing.printlnObject(RandomSeedUtil.doubleSeed(0.2, 0.5));
	}

	/**
	 * boolean 随机种子
	 */
	@Test
	public void boolSeed(){
		Testing.printlnObject(RandomSeedUtil.boolSeed());
	}

	/**
	 * char 随机种子
	 */
	@Test
	public void charSeed(){
		Testing.printlnObject(RandomSeedUtil.charSeed('0', '9'));
		Testing.printlnObject(RandomSeedUtil.charSeed('a', 'z'));
		Testing.printlnObject(RandomSeedUtil.charSeed('A', 'Z'));
	}
}