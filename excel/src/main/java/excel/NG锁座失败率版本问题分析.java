package excel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import com.ly.test.excel.ExcelUtil;
import com.ly.test.util.DateStyle;
import com.ly.test.util.DateUtil;

public class NG锁座失败率版本问题分析
{
	static String[] title={"时间点","总体锁座数", "5.0所有锁座","5.0异常失败","5.0成功锁座","6.3所有锁座","6.3异常失败","6.3成功锁座","5.0成功率","6.3成功率","总体成功率"};
	static int effectiveLen = 6;
	static int startEffectIdx = 3;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str = DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS2);
		String fileName = null;
		try
		{
			fileName = "NGC不同版本锁座成功率分析_" + str + ".xlsx";
			System.out.println(fileName);

			new File(fileName).delete();

			String filePath = "E:/01_work/02_cec/03_需求/04_NGC/05_NGC腾讯云迁移后的锁座成功率/21_lock_nocache.txt";
//			String filePath2 = "E:/01_work/02_cec/03_需求/04_NGC/05_NGC腾讯云迁移后的锁座成功率/22_lock.txt";

			Map<String, String[]> content = readSrc(filePath);
//			Map<String, String[]> content2 = readSrc(filePath2);

//			mergeSrc(content, content2);
			
			Map<String, String[][]> allContent = parse(content);
			
			new ExcelUtil().writeExcel2(fileName, allContent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static Map<String, String[][]> parse(Map<String, String[]> content)
	{
		Map<String, String[][]> xlsContent = new HashMap<String, String[][]>();
		try
		{
			Map<String, String[]> calcResult = new HashMap<String, String[]>();
			// 合并三个机器计算结果
			for (Map.Entry<String, String[]> entry : content.entrySet())
			{
				String key = entry.getKey();
				String[] value = entry.getValue();
//				System.out.printf("------1111-------%10s   %10s   %10s   %10s \n", value[0], value[1], value[2], value[3]);
				
				// 保存每行记录和每次计算结果
				String[] result = new String[value.length + 4];
				
				result[0] = value[effectiveLen + 0];
				result[1] = value[effectiveLen + 1] + "0";
				result[2] = LockUtils.sumStr2Integer(value[0], value[3]) + "";
				
				for (int j = 0; j < effectiveLen; j++)
				{
					result[j + startEffectIdx] = value[j];
				}
				
				DecimalFormat df   = new DecimalFormat("##.#");
				
				result[effectiveLen + 3] = LockUtils.calcPerc(LockUtils.sumStr2Double(value[2]), 
						LockUtils.sumStr2Integer(value[0]), df);
				
				result[effectiveLen + 4] = LockUtils.calcPerc(LockUtils.sumStr2Double(value[5]), 
						LockUtils.sumStr2Integer(value[3]), df);
				
				result[effectiveLen + 5] = LockUtils.calcPerc(LockUtils.sumStr2Double(value[2], value[5]), 
						LockUtils.sumStr2Integer(value[3], value[0]), df);
				
				calcResult.put(key, result);
			}
			
			// 将合并结果转化保存，以方便写入xls
			Map<String, List<String[]>> tt = new HashMap<String, List<String[]>>();
			for (Map.Entry<String, String[]> entry : calcResult.entrySet())
			{
				String[] value = entry.getValue();
				String date = value[0];
//				System.out.printf("%10s   %10s   %10s   %10s \n ", value[0], value[1], value[2], value[3]);
				List<String[]> list = tt.get(date);
				if (list == null)
				{
					list = new ArrayList<String[]>();
					tt.put(date, list);
				}
				
				list.add(value);
			}
			
			for (Map.Entry<String, List<String[]>> entry : tt.entrySet())
			{
				List<String[]> value = entry.getValue();
				String[][] arr = new String[value.size() + 1][]; // 需要加上标题行
				
				int idx = 0;
				arr[idx++] = title;
				for (String[] tmparr : value)
				{
					arr[idx++] = Arrays.copyOfRange(tmparr, 1, tmparr.length);
				}
				
				xlsContent.put(entry.getKey(), arr);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return xlsContent;
	}


   static void mergeSrc(Map<String, String[]> content,
			Map<String, String[]> content2)
	{
		try
		{
			// 合并三个机器计算结果
			for (Map.Entry<String, String[]> entry : content.entrySet())
			{
				String key = entry.getKey();
				String[] value = entry.getValue();
								
				String[] value2 = content2.get(key);
								
				for (int j = 0; j < effectiveLen; j++)
				{
					if (value2 != null) value[j] = (Integer.valueOf(value[j]) + Integer.valueOf(value2[j])) + "";
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static Map<String, String[]> readSrc(String srcFile)
	{
		// 读取每个机器上shell分析结果的内容
		Map<String, String[]> content = new TreeMap<String, String[]>();
		try
		{
			List<String> list = FileUtils.readLines(new File(srcFile));
			for (String line : list)
			{
				StringTokenizer st = new StringTokenizer(line, "  ");
				String date = st.nextToken();
				String min = st.nextToken();
				
				String all50Num = st.nextToken();
				String fail50Num = st.nextToken();
				String succ50Num = st.nextToken();
				
				String all63Num = st.nextToken();
				String fail63Num = st.nextToken();
				String succ63Num = st.nextToken();

				Date d1 = DateUtil.StringToDate(date + " " + min + "0", DateStyle.YYYY_MM_DD_HH_MM);
				Date d2 = DateUtil.StringToDate(date + " 00:00", DateStyle.YYYY_MM_DD_HH_MM);
				Date d3 = DateUtil.StringToDate(date + " 15:30", DateStyle.YYYY_MM_DD_HH_MM);
				
				if (d1.after(d2) && d1.before(d3))
				{
					content.put(date + "" + min, new String[] { all50Num, fail50Num,succ50Num,all63Num, fail63Num,succ63Num, date, min});
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return content;
	}

	
}
