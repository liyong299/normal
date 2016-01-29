/**
 * ��Ŀ��ƣ�excel
 * �ļ�����com.ly.test.excel
 * �ļ���ƣ�aaa.java
 * �汾��Ϣ��SCEC_Branches
 * ������ڣ�2016��1��22�� ����11:31:47
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class ExcelUtil
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * read the Excel file
	 * 
	 * @param path the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public Map<String, XSSFCell[][]> readExcel(String path) throws IOException
	{
		System.out.println("文件路径：" + path);
		if (path == null || Common.EMPTY.equals(path))
		{
			return null;
		}
		else
		{
			String postfix = Util.getPostfix(path);
			if (!Common.EMPTY.equals(postfix))
			{
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix))
				{
					return readXls(path);
				}
				else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix))
				{
					return readXlsx(path);
				}
			}
			else
			{
				System.out.println(path + Common.NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * @param cell
	 * @return
	 */
	public static Date getDateCell(XSSFCell cell)
	{
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell))
		{
			return cell.getDateCellValue();
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public Map<String, XSSFCell[][]> readXlsx(String path) throws IOException
	{
		System.out.println(Common.PROCESSING + path);
		InputStream is = null;
		XSSFWorkbook xssfWorkbook = null;

		Map<String, XSSFCell[][]> xlsContentMap = new HashMap<String, XSSFCell[][]>();

		try
		{
			System.out.println("打开文件读取");
			is = new FileInputStream(path);
			
			xssfWorkbook = new XSSFWorkbook(is);
			System.out.println("工作薄个数：" + xssfWorkbook.getNumberOfSheets());
			
			// Read the Sheet
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++)
			{
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null)
				{
					System.out.println("工作表为空！");
					continue;
				}

				// Read the Row
				int maxRowNum = xssfSheet.getLastRowNum();
				System.out.println("行数： " + maxRowNum);
				
				XSSFCell[][] sheetContent = new XSSFCell[maxRowNum][];
				for (int rowNum = 0; rowNum < maxRowNum; rowNum++)
				{
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					int lineNum = xssfRow.getLastCellNum();
					XSSFCell[] rowContent = new XSSFCell[lineNum];

					for (int i = 0; i < lineNum; i++)
					{
						//                    	System.out.printf("%-20s", xssfRow.getCell(i) + "     ");
						rowContent[i] = xssfRow.getCell(i);
					}
					//                    System.out.println();
					sheetContent[rowNum] = rowContent;
				}

				xlsContentMap.put(xssfSheet.getSheetName(), sheetContent);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return xlsContentMap;
		}
		finally
		{
			if (xssfWorkbook != null)
				xssfWorkbook.close();
			
			if (is != null)
				is.close();
		}
		return xlsContentMap;
	}

	/**
	 * 将内容写入文件
	 * 
	 * @param filePath 文件路径
	 * @param content 写入内容{sheet名称，内容}
	 */
	public void writeExcel(String filePath, Map<String, XSSFCell[][]> content)
	{
		// 定义工作表
		XSSFWorkbook workBook = null;
		InputStream is = null;

		try
		{
			// 打开文件流
			is = new FileInputStream(filePath);
			workBook = new XSSFWorkbook(is);

			int sheetIdx = 0;
			
			for (Map.Entry<String, XSSFCell[][]> entry : content.entrySet())
			{
				XSSFSheet sheet = workBook.getSheetAt(sheetIdx++);
				XSSFRow[] rows = new XSSFRow[entry.getValue().length];
				
				int rowIdx = 0;
				for (XSSFCell[] rowContent : entry.getValue())
				{
					rows[rowIdx] = sheet.getRow(rowIdx);  
					int columnIdx = 0;
					for (XSSFCell cell : rowContent)
					{
						XSSFCell temp = rows[rowIdx].getCell(columnIdx);
						if (cell != null)
							copyCell(cell, temp);
						columnIdx++;
					}
					rowIdx++;
				}
				break;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close(); 
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		FileOutputStream os = null;
		try
		{
			os = new FileOutputStream(filePath);
			workBook.write(os);
			os.flush();
		}
		catch (Exception ex)
		{
			if (os != null)
			{
				try
				{
					os.close(); 
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (workBook != null)
			{
				try
				{
					workBook.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		System.out.println("创建文件 office 2007 excel");
	}

	public void copyCell(XSSFCell src, XSSFCell dest)
	{
		//		dest.setCellStyle(src.getCellStyle());
		dest.setCellType(src.getCellType());
		dest.setCellComment(src.getCellComment());
		//		dest.setCellErrorValue(src.getErrorCellValue());
//		dest.setCTCell(src.getCTCell());

//		System.out.println(src.getCellType());
		if (src.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN)
		{
			dest.setCellValue(src.getBooleanCellValue());
		}
		if (src.getCellType() == XSSFCell.CELL_TYPE_ERROR)
		{
			dest.setCellValue(src.getErrorCellValue());
		}
		if (src.getCellType() == XSSFCell.CELL_TYPE_FORMULA)
		{
			dest.setCellFormula(src.getCellFormula());
		}
		if (src.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
		{
			dest.setCellValue(src.getNumericCellValue());
		}
		if (src.getCellType() == XSSFCell.CELL_TYPE_STRING)
		{
			dest.setCellValue(src.getStringCellValue());
		}
	}

	/**
	 * Read the Excel 2003-2007 ����δʵ�֡�TODO
	 * 
	 * @param path the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public Map<String, XSSFCell[][]> readXls(String path) throws IOException
	{
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		Map<String, XSSFCell[][]> xlsContentMap = new HashMap<String, XSSFCell[][]>();

		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
		{
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null)
			{
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
			{
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);

			}
		}
		return xlsContentMap;
	}

	@SuppressWarnings("static-access")
	private String getValue(XSSFCell xssfRow)
	{
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(xssfRow.getBooleanCellValue());
		}
		else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC)
		{
			return String.valueOf(xssfRow.getNumericCellValue());
		}
		else
		{
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell)
	{
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(hssfCell.getBooleanCellValue());
		}
		else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC)
		{
			return String.valueOf(hssfCell.getNumericCellValue());
		}
		else
		{
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
