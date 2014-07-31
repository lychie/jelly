package org.jelly.excel;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jelly.exception.ExecutetimeException;
import org.jelly.util.ArrayUtil;
import org.jelly.util.ClassUtil;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FieldUtil;
import org.jelly.util.MapUtil;
import org.jelly.util.StringUtil;
/**
 * 可写的 Excel
 * @author Lychie Fan
 * @since 1.5.0
 */
public class WritableExcel extends Excel {
	
	private Setting setting;
	private WritableFont titleFont;
	private WritableFont contentFont;
	private WritableFont cellFont;
	private WritableFont redFont;
	private WritableFont greenFont;
	private WritableFont blueFont;
	private Map<String, Format> format;
	private WritableCellFormat defaultCellFormat;
	
	public WritableExcel(Setting setting){
		this.setting = setting;
		this.format = new HashMap<String, Format>();
		this.titleFont = getFont(setting.titleFontSize, setting.titleColor, true);
		this.contentFont = getFont(setting.contentFontSize, setting.contentColor, false);
		this.cellFont = contentFont;
		try {
			this.defaultCellFormat = getCellFormat(TEXT_FORMAT);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 写出到文件 </des>
	 * @since 1.5.0
	 */
	public File write(File file, List<?> pojoList){
		try {
			return write(file, pojoList, Workbook.createWorkbook(file), 0);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 追加到文件 </des>
	 * @since 1.5.0
	 */
	public File append(File file, List<?> pojoList){
		try {
			Workbook book = Workbook.getWorkbook(file);
			String[] sheetNames = book.getSheetNames();
			if(ArrayUtil.contains(sheetNames, sheetName)){
				sheetName = DEFAULT_SHEET_PREFIX + (sheetNames.length + 1);
			}
			WritableWorkbook workBook = Workbook.createWorkbook(file, book);
			return write(file, pojoList, workBook, sheetNames.length);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 设置字段属性的映射 </des>
	 * @since 1.5.0
	 */
	public void setMapping(String field, String label, Format format){
		this.mapping.put(field, label);
		this.format.put(field, format);
	}
	
	// 写出
	private File write(File file, List<?> pojoList, WritableWorkbook workBook, int sheetIndex) throws Throwable {
		initMapping(pojoList.get(0).getClass());
		WritableSheet sheet = workBook.createSheet(sheetName, sheetIndex);
		buildSheetTitle(sheet);
		int rowIndex = BODY_ROW_NUMBER;
		List<String> fieldList = MapUtil.mapKeyAsList(mapping);
		for(Object pojo : pojoList){
			sheet.setRowView(rowIndex, setting.contentRowHeight);
			for(int i = 0; i < fieldList.size(); i++){
				sheet.addCell(getFormatCell(pojo, fieldList.get(i), rowIndex, i));
			}
			rowIndex++;
		}
		workBook.write();
		workBook.close();
		return file;
	}
	
	// 构建标题行
	private void buildSheetTitle(WritableSheet sheet) throws Throwable {
		WritableCellFormat format = getTitleCellFormat();
		sheet.setRowView(TITLE_ROW_NUMBER, setting.titleRowHeight);
		List<String> labelList = MapUtil.mapValueAsList(mapping);
		for(int column = 0; column < labelList.size(); column++){
			sheet.setColumnView(column, setting.columnWidth);
			sheet.addCell(new Label(column, TITLE_ROW_NUMBER, labelList.get(column), format));
		}
	}
	
	// 标题行格式
	private WritableCellFormat getTitleCellFormat() throws Throwable {
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
		titleFormat.setWrap(setting.titleAutoWrap);
		if (setting.titleHorizontalAlign) {
			titleFormat.setAlignment(Alignment.CENTRE);
		}
		if (setting.titleVerticalAlign) {
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		}
		if (setting.titleBackground != null) {
			titleFormat.setBackground(setting.titleBackground);
		}
		return titleFormat;
	}
	
	// 内容行格式
	private WritableCellFormat getCellFormat(String type) throws Throwable {
		WritableCellFormat format = null;
		if(type == TEXT_FORMAT){
			format = new WritableCellFormat(cellFont);
		}else if(type == DATE_FORMAT){
			format = new WritableCellFormat(cellFont, new DateFormat(dateFormat));
		}else if(type == NUMBER_FORMAT){
			format = new WritableCellFormat(cellFont, new NumberFormat(numberFormat));
		}
		format.setWrap(setting.contentAutoWrap);
		if (setting.contentHorizontalAlign) {
			format.setAlignment(Alignment.CENTRE);
		}
		if (setting.contentVerticalAlign) {
			format.setVerticalAlignment(VerticalAlignment.CENTRE);
		}
		if (setting.contentBackground != null) {
			format.setBackground(setting.contentBackground);
		}
		return format;
	}
	
	// 字体
	private WritableFont getFont(int fontSize, Colour fontColour, boolean bold){
		return new WritableFont(WritableFont.ARIAL, fontSize, bold ? WritableFont.BOLD : 
			WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, fontColour);
	}
	
	// 红色字体
	private void useRedFont(){
		if(redFont == null){
			redFont = getFont(setting.contentFontSize, Colour.RED, false);
		}
		cellFont = redFont;
	}
	
	// 绿色字体
	private void useGreenFont(){
		if(greenFont == null){
			greenFont = getFont(setting.contentFontSize, Colour.GREEN, false);
		}
		cellFont = greenFont;
	}
	
	// 蓝色字体
	private void useBlueFont(){
		if(blueFont == null){
			blueFont = getFont(setting.contentFontSize, Colour.BLUE, false);
		}
		cellFont = blueFont;
	}
	
	// 重置日期格式
	private void resetDateFormat(String field){
		Format value = format.get(field);
		dateFormat = value == null ? dateFormat.toString() : value.toString();
	}
	
	// 重置数值格式
	private void resetNumberFormat(String field, double fieldValue, Format defaultValue) throws Throwable {
		Format value = format.get(field);
		numberFormat = value == null ? defaultValue.toString() : value.toString();
		if(numberFormat.contains("_COLOR")){
			numberFormat = StringUtil.substringAfter(numberFormat, "_COLOR");
			if(fieldValue > 0){
				useGreenFont();
			}else if(fieldValue < 0){
				useRedFont();
			}else {
				useBlueFont();
			}
		}
	}
	
	// 格式化单元格
	private WritableCell getFormatCell(Object pojo, String field, int row, int column) throws Throwable {
		cellFont = contentFont;
		Class<?> type = FieldUtil.getFieldType(pojo, field);
		Object value = FieldUtil.getFieldValue(pojo, field);
		if(value.toString().matches("-?[0-9]\\d*")){
			long cellValue = Long.parseLong(value.toString());
			resetNumberFormat(field, cellValue, Format.INTEGER);
			return new jxl.write.Number(column, row, cellValue, getCellFormat(NUMBER_FORMAT));
		}
		if(value.toString().matches("-?[0-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*")){
			double cellValue = Double.valueOf(value.toString());
			resetNumberFormat(field, cellValue, Format.FLOAT);
			return new jxl.write.Number(column, row, cellValue, getCellFormat(NUMBER_FORMAT));
		}
		if(ClassUtil.isInstanceOf(ConvertUtil.getBoxedPrimitiveType(type), Boolean.class)){
			return new jxl.write.Boolean(column, row, (Boolean) value, defaultCellFormat);
		}
		if(ClassUtil.isInstanceOf(type, Date.class)){
			resetDateFormat(field);
			return new DateTime(column, row, (Date) value, getCellFormat(DATE_FORMAT));
		}
		return new Label(column, row, value.toString(), defaultCellFormat);
	}
	
}