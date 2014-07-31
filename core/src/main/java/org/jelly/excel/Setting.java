package org.jelly.excel;

import jxl.format.Colour;
/**
 * 设置项
 * @author Lychie Fan
 * @since 1.5.0
 */
public class Setting {
	
	int titleFontSize;
	int contentFontSize;
	int columnWidth;
	int titleRowHeight;
	int contentRowHeight;
	boolean titleHorizontalAlign;
	boolean titleVerticalAlign;
	boolean titleAutoWrap;
	boolean contentHorizontalAlign;
	boolean contentVerticalAlign;
	boolean contentAutoWrap;
	Colour titleColor;
	Colour contentColor;
	Colour titleBackground;
	Colour contentBackground;
	
	public Setting(){
		this.titleHorizontalAlign = true;		// 标题水平居中对齐
		this.titleVerticalAlign = true;			// 标题垂直居中对齐
		this.titleAutoWrap = true;				// 标题不自动换行
		this.titleFontSize = 11;				// 标题的字体大小默认 10
		this.contentHorizontalAlign = false;	// 内容默认不水平居中
		this.contentVerticalAlign = true;		// 内容默认不垂直居中
		this.contentAutoWrap = true;			// 内容默认不自动换行
		this.contentFontSize = 10;				// 内容的字体大小默认 10
		this.columnWidth = 20;					// 单元格的列的宽度默认 20
		this.titleRowHeight = 420;				// 标题行高默认 350
		this.contentRowHeight = 360;			// 内容行高默认 310
		this.titleColor = Colour.LIGHT_BLUE;	// 标题默认为蓝色字体
		this.contentColor = Colour.BLACK;		// 内容默认为黑色字体
		this.titleBackground = null;			// 标题行背景
		this.contentBackground = null;			// 内容行背景
	}
	
	/**
	 * <des> 设置标题是否水平居中对齐，默认水平居中对齐 </des>
	 * @since 1.5.0
	 */
	public void setTitleHorizontalAlign(boolean titleHorizontalAlign) {
		this.titleHorizontalAlign = titleHorizontalAlign;
	}

	/**
	 * <des> 设置标题是否垂直居中对齐，默认垂直居中对齐 </des>
	 * @since 1.5.0
	 */
	public void setTitleVerticalAlign(boolean titleVerticalAlign) {
		this.titleVerticalAlign = titleVerticalAlign;
	}

	/**
	 * <des> 设置标题是否自动换行，默认自动换行 </des>
	 * @since 1.5.0
	 */
	public void setTitleAutoWrap(boolean titleAutoWrap) {
		this.titleAutoWrap = titleAutoWrap;
	}

	/**
	 * <des> 设置标题的字体大小，默认是 10ps </des>
	 * @since 1.5.0
	 */
	public void setTitleFontSize(int titleFontSize) {
		this.titleFontSize = titleFontSize;
	}

	/**
	 * <des> 设置标题字体的颜色为红色，默认是蓝色 </des>
	 * @since 1.5.0
	 */
	public void setTitleColor(Colour colour) {
		this.titleColor = colour;
	}

	/**
	 * <des> 设置单元格的内容是否水平居中对齐，默认不水平居中对齐 </des>
	 * @since 1.5.0
	 */
	public void setContentHorizontalAlign(boolean contentHorizontalAlign) {
		this.contentHorizontalAlign = contentHorizontalAlign;
	}

	/**
	 * <des> 设置单元格内容是否垂直居中对齐，默认垂直居中对齐 </des>
	 * @since 1.5.0
	 */
	public void setContentVerticalAlign(boolean contentVerticalAlign) {
		this.contentVerticalAlign = contentVerticalAlign;
	}

	/**
	 * <des> 设置单元格内容是否自动换行，默认自动换行 </des>
	 * @since 1.5.0
	 */
	public void setContentAutoWrap(boolean contentAutoWrap) {
		this.contentAutoWrap = contentAutoWrap;
	}

	/**
	 * <des> 设置内容的字体颜色 </des>
	 * @since 1.5.0
	 */
	public void setContentColor(Colour colour) {
		this.contentColor = colour;
	}

	/**
	 * <des> 设置单元格内容的字体的大小，默认是 10ps </des>
	 * @since 1.5.0
	 */
	public void setContentFontSize(int contentFontSize) {
		this.contentFontSize = contentFontSize;
	}

	/**
	 * <des> 设置列的宽度，默认值是 20 </des>
	 * @since 1.5.0
	 */
	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}

	/**
	 * <des> 设置标题的行高，默认值是 350 </des>
	 * @since 1.5.0
	 */
	public void setTitleRowHeight(int titleRowHeight) {
		this.titleRowHeight = titleRowHeight;
	}

	/**
	 * <des> 设置内容单元格的行高，默认值是 310 </des>
	 * @since 1.5.0
	 */
	public void setContentRowHeight(int contentRowHeight) {
		this.contentRowHeight = contentRowHeight;
	}

	public void setTitleBackground(Colour titleBackground) {
		this.titleBackground = titleBackground;
	}

	public void setContentBackground(Colour contentBackground) {
		this.contentBackground = contentBackground;
	}
	
}