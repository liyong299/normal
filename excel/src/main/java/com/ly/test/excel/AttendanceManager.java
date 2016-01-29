 /**
 * 项目名称：excel
 * 文件包名：com.ly.test.excel
 * 文件名称：AttendanceManager.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月22日 上午11:03:08
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.excel;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.ly.test.util.DateStyle;
import com.ly.test.util.DateUtil;
import com.ly.test.util.Week;

/**
 * @author ly
 * 
 */
public class AttendanceManager
{
	public void test()
	{
		Excel2003Util jxlrw1 = new Excel2003Util();

		//读取  
		//单元格格式--此处都为"文本"  
		String excelPath = "D:/Work/Workspace/scec_dis/excel/考勤.xlsx";
		File excel = new File(excelPath);
		Map<String, String[][]> content = jxlrw1.readExcelOfFile(excel);

		System.out.println(content);
	}

	static String srcFile = "考勤.xlsx";
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (args == null || args.length < 1)
		{
			System.out.println("参数错误");
			System.exit(1);
		}
		srcFile = args[0];
		new AttendanceManager().executeAttend(srcFile);
	}

	public String executeAttend(String srcFile)
	{
		this.srcFile = srcFile;
		
		// 读取xlsx文件内容
		Map<String, XSSFCell[][]> content = null;
		String path = srcFile;
		try
		{
			content = new ExcelUtil().readExcel(path);
			if (content == null || content.size() == 0)
			{
				System.out.println("文件[" + path + "]文件读取失败");
			}
		}
		catch (IOException e)
		{
			System.out.println("文件[" + path + "]文件读取失败");
			e.printStackTrace();
		}

		// 逐行处理
		calaAttend(content);

		// 重新生成文件
		return createNewFile(content);
	}

	/**
	 * 生成一个新的文件
	 * 
	 * @param content
	 * @throws IOException
	 */
	private String createNewFile(Map<String, XSSFCell[][]> content)
	{
		String str = DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS2);
		String fileName = null;
		try
		{
			File src = new File(srcFile);
			fileName = src.getParent() + "/" + "attendance_" + str + ".xlsx";
			System.out.println(fileName);
			
			new File(fileName).delete();
			FileUtils.copyFile(src, new File(fileName));
			new ExcelUtil().writeExcel(fileName, content);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return fileName;
	}

	/**
	 * 处理所有sheet数据
	 * 
	 * @param content
	 */
	public void calaAttend(Map<String, XSSFCell[][]> content)
	{
		if (content != null)
		{
			for (Map.Entry<String, XSSFCell[][]> entry : content.entrySet())
			{
				calaAttend(entry);
			}
		}
	}

	/**
	 * 处理单个sheet数据
	 * 
	 * @param entry
	 */
	private void calaAttend(Entry<String, XSSFCell[][]> entry)
	{
		String name = entry.getKey();
		XSSFCell[][] content = entry.getValue();
		System.out.println("sheet's name is : " + name);
		
		for (int i = 1; i < content.length; i++)
		{
			XSSFCell[] rowCell = content[i];
			
			if (rowCell[0].getStringCellValue().contains("部门"))
			{
				continue;
			}

			System.out.println("当前行第一个单元格内容：" + rowCell[0].getStringCellValue());
			EmployAttend employAttend = calaAttendToEmploy(rowCell);
			calaAttend(employAttend);
			AttendToRowCell(employAttend, rowCell);
		}
	}

	private void AttendToRowCell(EmployAttend employAttend, XSSFCell[] rowCell)
	{
		int i = 0, size = rowCell.length;
		rowCell[i++] = employAttend.getBuMen();
		rowCell[i++] = employAttend.getGongHao();
		rowCell[i++] = employAttend.getRiQi();
		rowCell[i++] = employAttend.getXingMing();
		rowCell[i++] = employAttend.getXingQi();
		rowCell[i++] = employAttend.getShangBanTime();

		// 下班时间	迟到(分钟)	早退(分钟)	请假(小时)	出差(天) 外出 
		rowCell[i++] = employAttend.getXiaBanTime();
		rowCell[i++] = employAttend.getChiDaoMin();
		rowCell[i++] = employAttend.getZaoTuiMin();
		rowCell[i++] = employAttend.getQingJiaHour();
		rowCell[i++] = employAttend.getChuChai();
		rowCell[i++] = employAttend.getWaiChu();

		// 	外出时间(分)	打卡补登	打卡异常	加班(小时)	旷工(小时)	异常 
		rowCell[i++] = employAttend.getWaiChuMin();
		rowCell[i++] = employAttend.getDaKabudeng();
		rowCell[i++] = employAttend.getDaKayichang();
		if (i < size)
			rowCell[i++] = employAttend.getJiaBanHour();
		if (i < size)
			rowCell[i++] = employAttend.getKuangGong();
		if (i < size)
			rowCell[i++] = employAttend.getYiChangleixing();
		if (i < size)
			rowCell[i++] = employAttend.getYiChangmiaoshu();

		if (size > 18)
		{
			System.out.printf("%10s   %10s  %10s --", rowCell[2], rowCell[17], employAttend.getYiChangleixing());
			System.out.println();
		}
	}

	/**
	 * 计算单个人考勤情况
	 * 
	 * @param employAttend
	 */
	private void calaAttend(EmployAttend employAttend)
	{
		XSSFCell shangBan = employAttend.getShangBanTime();

		System.out.print(shangBan.getRowIndex() + "       ");

		Date shangBanTime = ExcelUtil.getDateCell(employAttend.getShangBanTime());
		Date xiaBanTime = ExcelUtil.getDateCell(employAttend.getXiaBanTime());

		System.out.printf("%10s   %10s ", shangBanTime, xiaBanTime);

		Date riqi = ExcelUtil.getDateCell(employAttend.getRiQi());
		if (judgeHoilday(riqi)) // 节假日，则不处理
		{
			return;
		}	

		// 1、缺少上班或者下班打卡
		if (shangBanTime == null || xiaBanTime == null)
		{
			if (shangBanTime == null)
			{
				modifyYiChang(AttendType.shangBanUnknown, employAttend);
			}
			if (xiaBanTime == null)
			{
				modifyYiChang(AttendType.xiaBanUnknown, employAttend);
			}
		}
		else
		{
			int shangBanHour = com.ly.test.util.DateUtil.getHour(shangBanTime);
			int xiaBanHour = com.ly.test.util.DateUtil.getHour(xiaBanTime);
			System.out.printf("上班时间：%10s   %10s ", shangBanHour, xiaBanHour);
			if (shangBanHour < 9) // 1、上班时间<9点
			{
				if (xiaBanHour >= 18) // 正常
				{
					XSSFCell yiChang = employAttend.getYiChangleixing();
					yiChang.setCellValue(0);
				}
				else
				// 早退，或迟到
				{
					int diffMin = com.ly.test.util.DateUtil.getIntervals(xiaBanTime, shangBanTime, 2);
					XSSFCell zaoTui = employAttend.getZaoTuiMin();
					zaoTui.setCellValue(diffMin);
				}
			}
			else if (shangBanHour >= 9 && shangBanHour < 10)
			{
				int diffMin = com.ly.test.util.DateUtil.getIntervals(xiaBanTime, shangBanTime, 2);
				int chidaoMin = DateUtil.getMinute(shangBanTime);
				XSSFCell chiDao = employAttend.getChiDaoMin();
				chiDao.setCellValue(chidaoMin);

				System.out.printf("迟到时间：%10s   %10s ", diffMin, chidaoMin);

				if (diffMin < Common.NORMAL_WORK_MIN) // 迟到或早退
				{
					int chiDaoMin = Common.NORMAL_WORK_MIN - diffMin;

					if (chiDaoMin <= 10)
					{
						modifyYiChang(AttendType.chiDaohuanchong, employAttend);
					}
					else if (chiDaoMin <= 30)
					{
						modifyYiChang(AttendType.chiDao, employAttend);
					}
					else
					{
						modifyYiChang(AttendType.kuanggong, employAttend);
					}
				}
				else  // 正常
				{
					modifyYiChang(AttendType.zhengchang, employAttend);
				}
			}
			else if (shangBanHour >= 10)
			{
				int chidaoMin = DateUtil.getMinute(shangBanTime);
				XSSFCell chiDao = employAttend.getChiDaoMin();
				chiDao.setCellValue(chidaoMin);
				
				if (chidaoMin <= 10)
				{
					modifyYiChang(AttendType.chiDaohuanchong, employAttend);
				}
				else if (chidaoMin <= 30)
				{
					modifyYiChang(AttendType.chiDao, employAttend);
				}
				else
				{
					modifyYiChang(AttendType.kuanggong, employAttend);
				}
				
			}
		}

		System.out.println();
	}
	
	
	
	private void modifyYiChang(AttendType attendType, EmployAttend employAttend)
	{
		XSSFCell yiChang = employAttend.getYiChangleixing();
		yiChang.setCellValue(attendType.getValue());
		
		XSSFCell miaoShu = employAttend.getYiChangmiaoshu();
		if (miaoShu != null)
		{
			miaoShu.setCellValue(attendType.getText());
		}
	}

	private boolean judgeHoilday(Date riqi)
	{
		return Common.holidays.contains(DateUtil.DateToString(riqi, DateStyle.YYYY_MM_DD))
				|| DateUtil.getWeek(riqi) == Week.SUNDAY || DateUtil.getWeek(riqi) == Week.SATURDAY;
	}

	/**
	 * 处理每一行数据
	 * 
	 * @param rowCell
	 */
	private EmployAttend calaAttendToEmploy(XSSFCell[] rowCell)
	{
		EmployAttend employAttend = new EmployAttend();
		// 部门	工号	日期	姓名	星期	上班时间		

		int i = 0, size = rowCell.length - 1;
		//		System.out.printf("%-10s   %-10s", i, size);
		employAttend.setBuMen(rowCell[i++]);
		employAttend.setGongHao(rowCell[i++]);
		employAttend.setRiQi(rowCell[i++]);
		employAttend.setXingMing(rowCell[i++]);
		employAttend.setXingQi(rowCell[i++]);
		employAttend.setShangBanTime(rowCell[i++]);

		// 下班时间	迟到(分钟)	早退(分钟)	请假(小时)	出差(天)	外出
		employAttend.setXiaBanTime(rowCell[i++]);
		employAttend.setChiDaoMin(rowCell[i++]);
		employAttend.setZaoTuiMin(rowCell[i++]);
		employAttend.setQingJiaHour(rowCell[i++]);
		employAttend.setChuChai(rowCell[i++]);
		employAttend.setWaiChu(rowCell[i++]);

		// 外出时间(分)	打卡补登	打卡异常	加班(小时)	旷工(小时)	异常 
		employAttend.setWaiChuMin(rowCell[i++]);
		employAttend.setDaKabudeng(rowCell[i++]);
		employAttend.setDaKayichang(rowCell[i++]);
		employAttend.setJiaBanHour(i > size ? null : rowCell[i++]);
		employAttend.setKuangGong(i > size ? null : rowCell[i++]);
		employAttend.setYiChangleixing(i > size ? null : rowCell[i++]);
		employAttend.setYiChangmiaoshu(i > size ? null : rowCell[i++]);

		return employAttend;
	}

}
