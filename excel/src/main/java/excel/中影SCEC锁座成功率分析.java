package excel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

public class 中影SCEC锁座成功率分析
{
	// 	所有渠道锁座	所有渠道成功	
	static String[] title = { "日期", "时间点", "地面超时", "所有渠道锁座", "所有渠道成功", "所有渠道240", "猫眼锁座", "猫眼成功", "猫眼240", "全部成功率",
			"猫眼成功率" };
	static int effectiveLen = 6;
	static int startEffectIdx = 2;

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
			fileName = "中影SCEC锁座成功率分析_" + str + ".xlsx";
			System.out.println(fileName);

			new File(fileName).delete();

			String filePath = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/53_lock_result2016-02-23_11_38_43.log";
			String filePath2 = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/56_lock_result2016-02-23_13_45_45.log";
			String filePath3 = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/64_lock_result2016-02-23_13_39_10.log";
			String filePath4 = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/65_lock_result2016-02-23_14_51_56.log";
			String filePath5 = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/74_lock_result2016-02-23_14_07_58.log";

			Map<String, String[]> content = readSrc(filePath);
			Map<String, String[]> content2 = readSrc(filePath2);
			Map<String, String[]> content3 = readSrc(filePath3);
			Map<String, String[]> content4 = readSrc(filePath4);
			Map<String, String[]> content5 = readSrc(filePath5);

			mergeSrc(content, content2);
			mergeSrc(content, content3);
			mergeSrc(content, content4);
			mergeSrc(content, content5);

			Map<String, String[][]> allContent = parse(content);
			new ExcelUtil().writeExcel2(fileName, allContent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
					arr[idx++] = tmparr;
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
				Date d2 = DateUtil.StringToDate(date + " 11:00", DateStyle.YYYY_MM_DD_HH_MM);
				Date d3 = DateUtil.StringToDate(date + " 15:30", DateStyle.YYYY_MM_DD_HH_MM);
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
