/**
 * 项目名称：excel
 * 文件包名：com.ly.test.excel
 * 文件名称：ExcelUtil.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月22日 上午11:02:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Boolean;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Binary Interchange File Format:Biff 二进制文件交换格式
 * 
 * @desc 导入读取和导出写入
 * 
 * */
public class Excel2003Util
{
	//log  
	private static final Logger LOG = LoggerFactory.getLogger(Excel2003Util.class);

	/**
	 * 读取excel工作簿信息.<br>
	 * @param book Excel工作簿
	 * @return 内容，每一个sheet是map的对象
	 */
	public Map<String, String[][]> readExcel(Workbook book)
	{
		try
		{
			//获取excel下的sheet页总个数  
			int maxSheet = book.getNumberOfSheets();
			
			LOG.info("Excel工作簿的sheet页总数：" + maxSheet + "页.");
			
			Map<String, String[][]> xlsContentMap = new HashMap<String, String[][]>();
			
			//获取各个excel，可以控制需要导入那个页  
			for (int sheeti = 0; sheeti < maxSheet; sheeti++)
			{
				LOG.info("第" + (sheeti + 1) + "页读取开始……");
				//获取sheet页  
				Sheet sheet = book.getSheet(sheeti);
				//通过名称获取sheet页  
				//              Sheet sheet = book.getSheet("sheet3");  
				//获取sheet的名称  
				String sheetName = sheet.getName();
				LOG.info("第" + (sheeti + 1) + "页名称：" + sheetName + ".");
				//当前sheet实际行总数  
				int realRows = sheet.getRows();
				LOG.info("第" + (sheeti + 1) + "页实际行总数：" + realRows + "行.");
				//当前sheet实际列总数  
				int realColumns = sheet.getColumns();
				LOG.info("第" + (sheeti + 1) + "页实际列总数：" + realColumns + "列.");
				
				String[][] sheetContent = new String[realRows][];
				
				//遍历每行每列的单元格 --类似二位数组定位excel单元格读取  
				//行  
				for (int row = 0; row < realRows; row++)
				{
					sheetContent[row] = new String[realColumns];
					
					//列  
					for (int column = 0; column < realColumns; column++)
					{
						//定位的单元格  
						Cell cell = sheet.getCell(column, row);
						//获取单元格类型  
						CellType type = cell.getType();
						//获取单元格内容 -- 注：数据验证格式/纯数字需对科学计数法转换  
						String content = cell.getContents();
						
						sheetContent[row][column] = content;
						
						LOG.info("单元格[第" + (sheeti + 1) + "页-第" + (row + 1) + "行-第" + (column + 1) + "列(类型:" + type
								+ ")]：" + content);
					}
				}
				//释放资源  
				sheet = null;
			}
			
			return xlsContentMap;
		}
		catch (IndexOutOfBoundsException e)
		{
			LOG.info("读取excel工作簿信息数组下标越界：" + e.getMessage(), e);
			return new HashMap<String, String[][]>();
		}
		finally
		{
			//释放资源  
			book.close();
		}
	}
	
	/**
	 * 写入数据导出excel工作簿 -- 数据格式化
	 * @param filePath 文件路径
	 * @param content 文件内容{sheet名称，内容}
	 */
	public void writeExcel(String filePath, Map<String, XSSFCell[][]> content)
	{
		WritableWorkbook book = null;
		try
		{
			//不加路径，导出在项目根目录  
			String excelName = "writeExcel.xls";
			File excel = new File(excelName);
			book = Workbook.createWorkbook(excel);
			
			//参数一：名称，写入名为"batchexport"sheet页,参数二：0表示这是第一页  
			WritableSheet sheet = book.createSheet("batchexport", 0);
			//行高和列宽  
			//将第一行的高度设为320  
			sheet.setRowView(0, 320);
			//将第4列的宽度设为30  
			sheet.setColumnView(3, 300);
			sheet.setColumnView(1, 30);
			//构造Label对象，并指定单元格位置是第一列第一行(0,0)--(列,行)  
			Label str1 = new Label(0, 0, "卡卡");
			//将定义好的str1添加到工作表中  
			sheet.addCell(str1);
			Number num1 = new Number(1, 0, 17400005555.889d);
			sheet.addCell(num1);
			Boolean bool1 = new Boolean(2, 0, true);
			sheet.addCell(bool1);
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			//---字串格式化  
			//WritableFont不同情况下有非常丰富的构造方法，jExcelAPI的java-doc中有详细介绍，也可搜索.  
			//字串格式：字体为TIMES，字号16，加粗显示  --WritableFont.createFont("宋体")  
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD);
			//WritableCellFormat类，通过它可以指定单元格的各种属性，单元格格式化  
			WritableCellFormat format1 = new WritableCellFormat(font1);
			//定数据的对齐方式  
			//水平对齐方式指定为居中  
			format1.setAlignment(Alignment.CENTRE);
			format1.setVerticalAlignment(VerticalAlignment.CENTRE);
			//垂直对齐方式指定为居中  
			//字串被赋予format1格式  
			Label date = new Label(3, 0, "时间:" + time, format1);
			sheet.addCell(date);
			//合并第一列，第二行到第六列，第一行的所有单元格  
			sheet.mergeCells(0, 1, 5, 1);
			//写入数据到工作簿  
			book.write();
			LOG.info(excelName + "写入文件导出成功.");
		}
		catch (RowsExceededException e)
		{
			LOG.info("写入Excel工作簿内容行超过异常：" + e.getMessage(), e);
		}
		catch (WriteException e)
		{
			LOG.info("写入Excel工作簿内容写入异常：" + e.getMessage(), e);
		}
		catch (IOException e)
		{
			LOG.info("写入Excel工作簿内容输入输出流异常：" + e.getMessage(), e);
		}
		finally
		{
			try
			{
				//关闭文件  
				book.close();
			}
			catch (WriteException e)
			{
				LOG.info("写入Excel工作簿内容写入异常：" + e.getMessage(), e);
			}
			catch (IOException e)
			{
				LOG.info("写入Excel工作簿内容输入输出流异常：" + e.getMessage(), e);
			}
		}
	}

	/**
	 * 写入数据导出excel工作簿 -- 数据格式化
	 * */
	public void writeExcel()
	{
		WritableWorkbook book = null;
		try
		{
			//不加路径，导出在项目根目录  
			String excelName = "writeExcel.xls";
			File excel = new File(excelName);
			book = Workbook.createWorkbook(excel);
			//参数一：名称，写入名为"batchexport"sheet页,参数二：0表示这是第一页  
			WritableSheet sheet = book.createSheet("batchexport", 0);
			//行高和列宽  
			//将第一行的高度设为320  
			sheet.setRowView(0, 320);
			//将第4列的宽度设为30  
			sheet.setColumnView(3, 300);
			sheet.setColumnView(1, 30);
			//构造Label对象，并指定单元格位置是第一列第一行(0,0)--(列,行)  
			Label str1 = new Label(0, 0, "卡卡");
			//将定义好的str1添加到工作表中  
			sheet.addCell(str1);
			Number num1 = new Number(1, 0, 17400005555.889d);
			sheet.addCell(num1);
			Boolean bool1 = new Boolean(2, 0, true);
			sheet.addCell(bool1);
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			//---字串格式化  
			//WritableFont不同情况下有非常丰富的构造方法，jExcelAPI的java-doc中有详细介绍，也可搜索.  
			//字串格式：字体为TIMES，字号16，加粗显示  --WritableFont.createFont("宋体")  
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD);
			//WritableCellFormat类，通过它可以指定单元格的各种属性，单元格格式化  
			WritableCellFormat format1 = new WritableCellFormat(font1);
			//定数据的对齐方式  
			//水平对齐方式指定为居中  
			format1.setAlignment(Alignment.CENTRE);
			format1.setVerticalAlignment(VerticalAlignment.CENTRE);
			//垂直对齐方式指定为居中  
			//字串被赋予format1格式  
			Label date = new Label(3, 0, "时间:" + time, format1);
			sheet.addCell(date);
			//合并第一列，第二行到第六列，第一行的所有单元格  
			sheet.mergeCells(0, 1, 5, 1);
			//写入数据到工作簿  
			book.write();
			LOG.info(excelName + "写入文件导出成功.");
		}
		catch (RowsExceededException e)
		{
			LOG.info("写入Excel工作簿内容行超过异常：" + e.getMessage(), e);
		}
		catch (WriteException e)
		{
			LOG.info("写入Excel工作簿内容写入异常：" + e.getMessage(), e);
		}
		catch (IOException e)
		{
			LOG.info("写入Excel工作簿内容输入输出流异常：" + e.getMessage(), e);
		}
		finally
		{
			try
			{
				//关闭文件  
				book.close();
			}
			catch (WriteException e)
			{
				LOG.info("写入Excel工作簿内容写入异常：" + e.getMessage(), e);
			}
			catch (IOException e)
			{
				LOG.info("写入Excel工作簿内容输入输出流异常：" + e.getMessage(), e);
			}
		}
	}

	/**
	 * 修改数据导出excel工作簿
	 * */
	public void editExcel()
	{
		String excelName = "writeExcel.xls";
		Workbook orgBook = null;
		WritableWorkbook reBook = null;
		try
		{
			File orgExcel = new File(excelName);
			File reExcel = new File(excelName);
			//获得excel文件  
			orgBook = Workbook.getWorkbook(orgExcel);
			//打开一个excel文件的副本，并且指定数据写回到原文件  
			reBook = Workbook.createWorkbook(reExcel, orgBook);
			//添加一个工作页  
			WritableSheet sheet = reBook.createSheet("editSheet", 1);
			sheet.addCell(new Label(0, 0, "编辑excel文件测试数据"));
			reBook.write();
			LOG.info(excelName + "编辑文件导出成功.");
		}
		catch (RowsExceededException e)
		{
			LOG.info("编辑Excel工作簿内容异常：" + e.getMessage(), e);
		}
		catch (BiffException e)
		{
			LOG.info("编辑Excel工作簿内容异常：" + e.getMessage(), e);
		}
		catch (WriteException e)
		{
			LOG.info("编辑Excel工作簿内容异常：" + e.getMessage(), e);
		}
		catch (IOException e)
		{
			LOG.info("编辑Excel工作簿内容异常：" + e.getMessage(), e);
		}
		finally
		{
			try
			{
				reBook.close();
				orgBook.close();
			}
			catch (WriteException e)
			{
				LOG.info("编辑Excel工作簿内容写入异常：" + e.getMessage(), e);
			}
			catch (IOException e)
			{
				LOG.info("编辑Excel工作簿内容输入输出流异常：" + e.getMessage(), e);
			}
		}
	}

	/**
	 * 文件形式.<br>
	 * 操作Excel工作簿.<br>
	 * 
	 * @param file Excel文件
	 * @return 
	 * */
	public Map<String, String[][]> readExcelOfFile(File file)
	{
		Workbook book = null;
		try
		{
			book = Workbook.getWorkbook(file);
			return readExcel(book);
		}
		catch (BiffException e)
		{
			LOG.info("读取Excel工作簿文件二进制文件交换格式异常：" + e.getMessage(), e);
		}
		catch (IOException e)
		{
			LOG.info("读取Excel工作簿文件输入输出流异常：" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 输入流形式.<br>
	 * 操作Excel工作簿.<br>
	 * 
	 * @param is Excel输入流，比如上传
	 * @return 
	 * @throws IOException
	 * */
	public Map<String, String[][]> readExcelOfInputStream(InputStream is) throws IOException
	{
		Workbook book = null;
		try
		{
			book = Workbook.getWorkbook(is);
			return readExcel(book);
		}
		catch (BiffException e)
		{
			LOG.info("读取Excel工作簿文件二进制文件交换格式异常：" + e.getMessage(), e);
		}
		catch (IOException e)
		{
			LOG.info("读取Excel工作簿文件输入输出流异常：" + e.getMessage(), e);
		}
		finally
		{
			is.close();
		}
		return null;
	}

	//测试  
	public static void main(String[] args)
	{
		Excel2003Util jxlrw1 = new Excel2003Util();
		//读取  
		//单元格格式--此处都为"文本"  
		String excelPath = "D:/RWExcelFile_jxl.xls";
		File excel = new File(excelPath);
		jxlrw1.readExcelOfFile(excel);
		//导出  
		jxlrw1.writeExcel();
		//编辑  
		jxlrw1.editExcel();
	}
}
