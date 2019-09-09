package com.toughguy.alarmSystem.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class POIUtils {

	/**
	 * @return HSSFCellStyle Style
	 * @ 表头样式     总样式
	 */
	public CellStyle Style(SXSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		// 设置边框	
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		
//		style.setRightBorderColor(HSSFColor.BLACK.index);
//		style.setLeftBorderColor(HSSFColor.BLACK.index);
//		style.setTopBorderColor(HSSFColor.BLACK.index);
//		style.setBottomBorderColor(HSSFColor.BLACK.index);
//		// 设置边框颜色
//		style.setTopBorderColor(HSSFColor.BLACK.index);
//		style.setBottomBorderColor(HSSFColor.BLACK.index);
//		style.setLeftBorderColor(HSSFColor.BLACK.index);
//		style.setRightBorderColor(HSSFColor.BLACK.index);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		
		
		Font fon = wb.createFont();
		fon.setFontName("黑体");
		fon.setFontHeightInPoints((short) 16);// 设置字体大小
		fon.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		
		style.setFont(fon);// 选择需要用到的字体格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		return style;
	}

			/**
			 * @return HSSFCellStyle
			 * @ 样式五  橘色背景  字体12
			 */
			public CellStyle Style1(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true);
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setFont(fon);// 选择需要用到的字体格式
				return style;
			}
			
			
			public CellStyle Style2(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true); 
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setFont(fon);// 选择需要用到的字体格式
				return style;
			}
			//工作底稿导出样式
			public CellStyle Style3(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	            DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.0"));//设置单元类型保留一位小数
				return style;
			}
			public CellStyle Style4(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	            DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.00"));//设置单元类型保留两位小数
				return style;
			}
			public CellStyle Style5(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	            DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.00"));//设置单元类型保留两位小数
				return style;
			}
			/**
			* 导出样品交接单样式
			*/
			public CellStyle Style6(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			public CellStyle Style7(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			public CellStyle Style8(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				return style;
			}
			public CellStyle Style9(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
				style.setWrapText(true); // 设置为自动换行
				return style;
			}
			/**
			* 设置单元格边框（解决合并单元格显示部分边框问题）
			* @param sheet 
			* @param region
			* @param cs
			*/
			@SuppressWarnings("deprecation")
			public void setRegionStyle(Sheet sheet, CellRangeAddress region, CellStyle cs) {
			 for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			  Row row = CellUtil.getRow(i, sheet);
			  for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
			   Cell cell = CellUtil.getCell(row, (short) j);
			   cell.setCellStyle(cs);
			  }
			 }
			}
			
			
			

			
}
