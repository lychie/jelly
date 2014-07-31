package org.jelly.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.jelly.exception.ExecutetimeException;
import org.jelly.util.ClassUtil;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FieldUtil;
import org.jelly.util.MapUtil;
/**
 * 可读的 Excel
 * @author Lychie Fan
 * @since 1.5.0
 */
public class ReadableExcel extends Excel {
	
	private LinkedList<String> labelList = new LinkedList<String>();
	
	/**
	 * <des> 文件中读取 </des>
	 * @since 1.5.0
	 */
	public <E> List<E> read(File file, Class<E> pojoClass) {
		try {
			return read(new FileInputStream(file), pojoClass);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> 输入流中读取 </des>
	 * @since 1.5.0
	 */
	public <E> List<E> read(InputStream in, Class<E> pojoClass) {
		try {
			Workbook book = Workbook.getWorkbook(in);
			Sheet sheet = book.getSheet(sheetName);
			int rowCounts = sheet.getRows();
			int columnCounts = sheet.getColumns();
			initMapping(pojoClass);
			initLabelList(sheet, columnCounts);
			List<E> pojoList = new ArrayList<E>();
			for(int row = BODY_ROW_NUMBER; row < rowCounts; row++){
				pojoList.add(readLine(sheet, row, columnCounts, pojoClass));
			}
			return pojoList;
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	// 初始化标题标签列表
	private void initLabelList(Sheet sheet, int columnCount){
		for(int column = 0; column < columnCount; column++){
			labelList.add(sheet.getCell(column, TITLE_ROW_NUMBER).getContents());
		}
	}
	
	// 读取行内容
	private <E> E readLine(Sheet sheet, int row, int columnCounts, Class<E> pojoClass) {
		E pojo = ClassUtil.getInstance(pojoClass);
		for(int column = 0; column < columnCounts; column++){
			String fieldName = MapUtil.findKey(mapping, labelList.get(column));
			String cellValue = sheet.getCell(column, row).getContents();
			Class<?> fieldType = FieldUtil.getFieldType(pojoClass, fieldName);
			cellValue = handleNumberCellText(fieldType, cellValue);
			Object value = ConvertUtil.objectValue(cellValue, fieldType);
			FieldUtil.setFieldValue(pojo, fieldName, value);
		}
		return pojo;
	}
	
	// 处理数值类型单元格内容
	private String handleNumberCellText(Class<?> type, String value){
		if(ClassUtil.isInstanceOf(ConvertUtil.getBoxedPrimitiveType(type), Number.class)){
			value = value.replaceAll(",", "").replaceAll("￥", "");
			if(value.contains("%")){
				value = value.replaceAll("%", "");
				double val = Double.parseDouble(value);
				value = String.valueOf(val / 100);
			}
		}
		return value;
	}
	
}