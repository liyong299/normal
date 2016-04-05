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

public class 渠道到SCEC锁座成功率分析
{
	// 	所有渠道锁座	所有渠道成功	
	static String[] title = {  "时间点", "地面超时", 
		"所有渠道锁座", "所有渠道成功", "所有渠道240", 
		"猫眼锁座", "猫眼成功", "猫眼240", 
		"全部成功率","猫眼成功率" };
	static int effectiveLen = 6;
	static int startEffectIdx = 2;

	static String DIR_PATH = "E:/01_work/02_cec/03_需求/04_NGC/07_NGC锁座分析/渠道到NGC/";
	
	/**
	 * 锁座关键字：ID_DLockSeat
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str = DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS2);
		String fileName = null;
		try
		{
			fileName = DIR_PATH + "渠道到NGC锁座成功率分析_" + str + ".xlsx";
			System.out.println(fileName);

			new File(fileName).delete();

			Map<String, String[]> content = readLogFile();

			Map<String, String[][]> allContent = parse(content);
			new ExcelUtil().writeExcel2(fileName, allContent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static Map<String, String[]> readLogFile()
	{
		List<File> files = getLogFileNames();
		Map<String, String[]> content = readSrc(files.get(0).getAbsolutePath());
		
		for (int i = 1; i < files.size(); i++)
		{
			mergeSrc(content, readSrc(files.get(i).getAbsolutePath()));
		}
		
		return content;
	}

	private static List<File> getLogFileNames()
	{
		File dir = new File(DIR_PATH);
		File[] paths = dir.listFiles();
		List<File> files = new ArrayList<File>();
		for (File file : paths)
		{
			if (file.getAbsolutePath().matches(".*lock_result.*.log"))
			{
				System.out.println(file.getAbsolutePath());
				files.add(file);
			}
		}
		return files;
	}
	

	private static void mergeSrc(Map<String, String[]> content, Map<String, String[]> content2)
	{
		// 合并三个机器计算结果
		for (Map.Entry<String, String[]> entry : content.entrySet())
		{
			String key = entry.getKey();
			String[] value = entry.getValue();
			
			//						System.out.printf("------1111-------%10s   %10s   %10s   %10s \n", value[0], value[1], value[2], value[3]);

			String[] value2 = content2.get(key);
			if (value2 == null) continue;

			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
			StringBuilder sb4 = new StringBuilder();
			for (int j = 0; j < effectiveLen; j++)
			{
				sb1.append(value[j + startEffectIdx]).append("     ");
				value[j + startEffectIdx] = (Integer.valueOf(value[j + startEffectIdx]) + Integer.valueOf(value2[j
						+ startEffectIdx]))
						+ "";
				sb2.append(value2[j + startEffectIdx]).append("     ");
				sb4.append(value[j + startEffectIdx]).append("     ");
			}

			System.out.printf("------1111-------%10s \n ", sb1);
			System.out.printf("------2222-------%10s \n ", sb2);
			System.out.printf("------3333-------%10s \n ", sb3);
			System.out.printf("------4444-------%10s \n ", sb4);
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
				String[] result = new String[value.length + 3];

				result[0] = value[0];
				result[1] = value[1] + "0";
				result[2] = Integer.valueOf(value[2]) - Integer.valueOf(value[3]) - Integer.valueOf(value[4]) + "";
				for (int j = 0; j < effectiveLen; j++)
				{
					result[j + 3] = value[j + startEffectIdx];
				}

				DecimalFormat df = new DecimalFormat("##.#");

				// 所有渠道成功率
				result[effectiveLen + 3] = df.format((Double.valueOf(value[3]) / Integer.valueOf(value[2]) * 100))
						+ "%";
				// 猫眼成功率
				result[effectiveLen + 4] = df.format((Double.valueOf(value[6]) / Integer.valueOf(value[5]) * 100))
						+ "%";

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
				String allNum = st.nextToken();
				String all001Num = st.nextToken();
				String all240Num = st.nextToken();

				String myAllNum = st.nextToken();
				String my001Num = st.nextToken();
				String my240Num = st.nextToken();

				Date d1 = DateUtil.StringToDate(date + " " + min + "0", DateStyle.YYYY_MM_DD_HH_MM);
				Date d2 = DateUtil.StringToDate(date + " 08:00", DateStyle.YYYY_MM_DD_HH_MM);
				Date d3 = DateUtil.StringToDate(date + " 22:30", DateStyle.YYYY_MM_DD_HH_MM);
				if (d1.after(d2) && d1.before(d3))
				{
					content.put(date + "" + min, new String[] { date, min, allNum, all001Num, all240Num, myAllNum,
							my001Num, my240Num });
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
