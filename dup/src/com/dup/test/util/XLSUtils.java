package com.dup.test.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.Boolean;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class XLSUtils
{
	public static void write(String filePath, String sheetName, int sheetIdx, String[][] data)
	{
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try
		{
			//			String filePath = "D:\\Test2.xls";
			File file = new File(filePath);
			if (!file.isFile()) //如果指定文件不存在，则新建该文件
				file.createNewFile();
			os = new FileOutputStream(file); //创建一个输出流
			wwb = Workbook.createWorkbook(os);

			WritableSheet sheet = wwb.getSheet(sheetName);
			if (sheet == null)
				sheet = wwb.createSheet(sheetName, sheetIdx);//创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页

			for (int i = 0; i < data.length; i++)
			{
				for (int j = 0; j < data[i].length; j++)
				{
					//第一个参数表示列，第二个参数表示行
					Label label = new Label(i, j, data[i][j]);//填充第一行第一个单元格的内容
					sheet.addCell(label);
				}
			}

			wwb.write();//将内容写到excel文件中
			os.flush();//清空输出流
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (WriteException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (wwb != null)
					wwb.close();
				if (os != null)
					os.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (WriteException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		main2(null);
	}

	public static void main2(String[] args)
	{
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try
		{
			String filePath = "D:\\Test.xls";
			File file = new File(filePath);
			if (!file.isFile())//如果指定文件不存在，则新建该文件
				file.createNewFile();
			os = new FileOutputStream(file);//创建一个输出流
			wwb = Workbook.createWorkbook(os);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);//创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页

			//第一个参数表示列，第二个参数表示行
			Label label = new Label(0, 0, "test");//填充第一行第一个单元格的内容
			sheet.addCell(label);
			WritableFont wf = new WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			label = new Label(0, 1, "font", wcf);//定制单元格格式
			sheet.addCell(label);
			NumberFormat nf = new NumberFormat("#.###");//格式化数字
			wcf = new WritableCellFormat(nf);
			Number nb = new Number(0, 2, 3.1415926, wcf);
			sheet.addCell(nb);
			Boolean labelB = new Boolean(0, 3, false); //填充布尔型数据
			sheet.addCell(labelB);
			DateTime labelDT = new DateTime(0, 4, new java.util.Date()); //添加日期
			sheet.addCell(labelDT);
			DateFormat df = new DateFormat("yyyy-MM-dd hh:mm:ss"); //添加时间
			WritableCellFormat wcfDF = new WritableCellFormat(df);
			DateTime labelDTF = new DateTime(0, 5, new java.util.Date(), wcfDF);
			sheet.addCell(labelDTF);
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.GREEN);
			WritableCellFormat wcfFC = new WritableCellFormat(wfc);
			wcfFC.setBackground(jxl.format.Colour.RED);//设置单元格的颜色为红色 
			label = new jxl.write.Label(0, 6, "i love china", wcfFC);
			sheet.addCell(label);
			wwb.write();//将内容写到excel文件中
			os.flush();//清空输出流
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (WriteException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (wwb != null)
					wwb.close();
				if (os != null)
					os.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (WriteException e)
			{
				e.printStackTrace();
			}
		}
	}
}
