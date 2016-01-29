package com.ly.test.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ���Դ�http://poi.apache.org/ �������ص�POI��jar�� POI �����Ͷ�ȡ2003-2007�汾Excel�ļ�
 * 
 */

public class CreatAndReadExcel
{
	public static void main(String[] args) throws Exception
	{

		creat2003Excel();// ����2007��Excel�ļ�  
		creat2007Excel();// ����2003��Excel�ļ�  
		//��ȡ2003Excel�ļ�  
		String path2003 = System.getProperty("user.dir") + System.getProperty("file.separator") + "style_2003.xls";
		// ��ȡ��Ŀ�ļ�·��   +2003���ļ���  
		System.out.println("路径：" + path2003);
		File f2003 = new File(path2003);
		
		try
		{
			readExcel(f2003);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		//��ȡ2007Excel�ļ�  
		String path2007 = System.getProperty("user.dir") + System.getProperty("file.separator") + "style_2007.xlsx";// ��ȡ��Ŀ�ļ�·��+2007���ļ���  
		System.out.println("路径：" + path2007);
		File f2007 = new File(path2007);
		try
		{
			readExcel(f2007);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}

	}

	/**
	 * ����2007��Excel�ļ�
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void creat2007Excel() throws FileNotFoundException, IOException
	{
		// HSSFWorkbook workBook = new HSSFWorkbook();// ���� һ��excel�ĵ�����  
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();// ����һ������������  

		sheet.setColumnWidth(1, 10000);// ���õڶ��еĿ��Ϊ  

		XSSFRow row = sheet.createRow(1);// ����һ���ж���  

		row.setHeightInPoints(23);// �����и�23����  

		XSSFCellStyle style = workBook.createCellStyle();// ������ʽ����  

		// ��������  

		XSSFFont font = workBook.createFont();// �����������  

		font.setFontHeightInPoints((short) 15);// ���������С  

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ���ô���  

		font.setFontName("����");// ����Ϊ������  

		style.setFont(font);// ��������뵽��ʽ����  

		// ���ö��뷽ʽ  

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// ˮƽ����  

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ����  

		// ���ñ߿�  

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// �����߿����  

		style.setTopBorderColor(HSSFColor.RED.index);// ����Ϊ��ɫ  

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// �ײ��߿�˫��  

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// ��߱߿�  

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// �ұ߱߿�  

		// ��ʽ������  

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		XSSFCell cell = row.createCell(1);// ������Ԫ��  

		cell.setCellValue(new Date());// д�뵱ǰ����  

		cell.setCellStyle(style);// Ӧ����ʽ����  

		// �ļ������  

		FileOutputStream os = new FileOutputStream("style_2007.xlsx");

		workBook.write(os);// ���ĵ�����д���ļ������  

		os.close();// �ر��ļ������  
		System.out.println("�����ɹ� office 2007 excel");
	}

	/**
	 * ����2003�汾��Excel�ļ�
	 */
	private static void creat2003Excel() throws FileNotFoundException, IOException
	{
		HSSFWorkbook workBook = new HSSFWorkbook();// ���� һ��excel�ĵ�����  

		HSSFSheet sheet = workBook.createSheet();// ����һ������������  

		sheet.setColumnWidth(1, 10000);// ���õڶ��еĿ��Ϊ  

		HSSFRow row = sheet.createRow(1);// ����һ���ж���  

		row.setHeightInPoints(23);// �����и�23����  

		HSSFCellStyle style = workBook.createCellStyle();// ������ʽ����  

		// ��������  

		HSSFFont font = workBook.createFont();// �����������  

		font.setFontHeightInPoints((short) 15);// ���������С  

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ���ô���  

		font.setFontName("����");// ����Ϊ������  

		style.setFont(font);// ��������뵽��ʽ����  

		// ���ö��뷽ʽ  

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// ˮƽ����  

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ����  

		// ���ñ߿�  

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// �����߿����  

		style.setTopBorderColor(HSSFColor.RED.index);// ����Ϊ��ɫ  

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// �ײ��߿�˫��  

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// ��߱߿�  

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// �ұ߱߿�  

		// ��ʽ������  

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		HSSFCell cell = row.createCell(1);// ������Ԫ��  

		cell.setCellValue(new Date());// д�뵱ǰ����  

		cell.setCellStyle(style);// Ӧ����ʽ����  

		// �ļ������  

		FileOutputStream os = new FileOutputStream("style_2003.xls");

		workBook.write(os);// ���ĵ�����д���ļ������  

		os.close();// �ر��ļ������  
		System.out.println("�����ɹ� office 2003 excel");
	}

	/**
	 * �����ṩ��ȡexcel �ķ���
	 */
	public static List<List<Object>> readExcel(File file) throws IOException
	{
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension))
		{
			return read2003Excel(file);
		}
		else if ("xlsx".equals(extension))
		{
			return read2007Excel(file);
		}
		else
		{
			throw new IOException("��֧�ֵ��ļ�����");
		}
	}

	/**
	 * ��ȡ office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003Excel(File file) throws IOException
	{
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		System.out.println("��ȡoffice 2003 excel�������£�");
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++)
		{
			row = sheet.getRow(i);
			if (row == null)
			{
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++)
			{
				cell = row.getCell(j);
				if (cell == null)
				{
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// ��ʽ�� number String  
				// �ַ�  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ�������ַ�  
				DecimalFormat nf = new DecimalFormat("0.00");// ��ʽ������  
				switch (cell.getCellType())
				{
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i + "��" + j + " �� is String type");  
					value = cell.getStringCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i + "��" + j  
					// + " �� is Number type ; DateFormt:"  
					// + cell.getCellStyle().getDataFormatString());  
					if ("@".equals(cell.getCellStyle().getDataFormatString()))
					{
						value = df.format(cell.getNumericCellValue());

					}
					else if ("General".equals(cell.getCellStyle().getDataFormatString()))
					{
						value = nf.format(cell.getNumericCellValue());
					}
					else
					{
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i + "��" + j + " �� is Boolean type");  
					value = cell.getBooleanCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i + "��" + j + " �� is Blank type");  
					value = "";
					System.out.print("  " + value + "  ");
					break;
				default:
					// System.out.println(i + "��" + j + " �� is default type");  
					value = cell.toString();
					System.out.print("  " + value + "  ");
				}
				if (value == null || "".equals(value))
				{
					continue;
				}
				linked.add(value);

			}
			System.out.println("");
			list.add(linked);
		}

		return list;
	}

	/**
	 * ��ȡOffice 2007 excel
	 */

	private static List<List<Object>> read2007Excel(File file) throws IOException
	{

		List<List<Object>> list = new LinkedList<List<Object>>();
		// String path = System.getProperty("user.dir") +  
		// System.getProperty("file.separator")+"dd.xlsx";  
		// System.out.println("·����"+path);  
		// ���� XSSFWorkbook ����strPath �����ļ�·��  
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));

		// ��ȡ��һ�±������  
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		System.out.println("��ȡoffice 2007 excel�������£�");
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++)
		{
			row = sheet.getRow(i);
			if (row == null)
			{
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++)
			{
				cell = row.getCell(j);
				if (cell == null)
				{
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// ��ʽ�� number String  
				// �ַ�  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ�������ַ�  
				DecimalFormat nf = new DecimalFormat("0.00");// ��ʽ������  

				switch (cell.getCellType())
				{
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i + "��" + j + " �� is String type");  
					value = cell.getStringCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i + "��" + j  
					// + " �� is Number type ; DateFormt:"  
					// + cell.getCellStyle().getDataFormatString());  
					if ("@".equals(cell.getCellStyle().getDataFormatString()))
					{
						value = df.format(cell.getNumericCellValue());

					}
					else if ("General".equals(cell.getCellStyle().getDataFormatString()))
					{
						value = nf.format(cell.getNumericCellValue());
					}
					else
					{
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i + "��" + j + " �� is Boolean type");  
					value = cell.getBooleanCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i + "��" + j + " �� is Blank type");  
					value = "";
					// System.out.println(value);  
					break;
				default:
					// System.out.println(i + "��" + j + " �� is default type");  
					value = cell.toString();
					System.out.print("  " + value + "  ");
				}
				if (value == null || "".equals(value))
				{
					continue;
				}
				linked.add(value);
			}
			System.out.println("");
			list.add(linked);
		}
		return list;
	}
}