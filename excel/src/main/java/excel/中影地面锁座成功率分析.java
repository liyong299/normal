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

public class 中影地面锁座成功率分析
{
	// 	所有渠道锁座	所有渠道成功	
	static String[] title = { "日期"/*, "时间点"*/
		, "所有锁座", "所有成功", "所有正常失败","所有异常失败",
		"火凤凰锁座", "火凤凰成功", "火凤凰正常失败","火凤凰异常失败", "满天星锁座", "满天星成功", "满天星正常失败", "满天星异常失败",
		"鼎新锁座", "鼎新成功", "鼎新正常失败","鼎新异常失败",  "火烈鸟锁座", "火烈鸟成功", "火烈鸟正常失败", "火烈鸟异常失败", 
		"全部成功率","火凤凰成功率","满天星成功率","鼎新成功率","火烈鸟成功率"
		,"全部正常失败率","火凤凰正常失败率","满天星正常失败率","鼎新正常失败率","火烈鸟正常失败率"
		,"全部异常失败率","火凤凰异常失败率","满天星异常失败率","鼎新异常失败率","火烈鸟异常失败率" };
	
	static int effectiveLen = 20;
	static int startEffectIdx = 2;
	static int addColNum = 9;

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
			fileName = "中影地面锁座成功率分析_" + str + ".xlsx";
			System.out.println(fileName);

			new File(fileName).delete();

			List<File> files = getLogFileNames();
			Map<String, String[]> content = readSrc(files.get(0).getAbsolutePath());
			
			for (int i = 1; i < files.size(); i++)
			{
				mergeSrc(content, readSrc(files.get(i).getAbsolutePath()));
			}

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
			String[] value2 = content2.get(key);
			if (key.equals("2016-02-0800:0"))
			{
				System.out.println(Arrays.asList(value));
				System.out.println(Arrays.asList(value2));
			}
			
			if (value2 == null) continue;
			
			for (int j = 0; j < effectiveLen; j++)
			{
				int idx = j + startEffectIdx;
				value[idx] = (Integer.valueOf(value[idx]) + Integer.valueOf(value2[idx])) + "";
			}
			if (key.equals("2016-02-0800:0"))
			{
				System.out.println(Arrays.asList(value));
				System.out.println("------------------");
			}
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
				String[] result = new String[value.length];

				result[0] = value[0];
				result[1] = value[1] + "0";
				
				for (int j = 0; j < value.length - startEffectIdx; j++)
				{
					result[j + startEffectIdx] = value[j + startEffectIdx];
				}
				
				test(result, value);
				
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
					String[] myArr = new String[tmparr.length - 1];
					for (int i = 1; i < tmparr.length; i++)
					{
						myArr[i-1] = tmparr[i];
					}
					arr[idx++] = myArr;
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
	
	public static void test(String[] result, String[] value)
	{
		int idx = 6;
		String tmpHFHAllNum = value[idx++];
		String tmpHFHSuccNum = value[idx++];
		String tmpHFH240Num = value[idx++];
		String tmpHFHErrNum = value[idx++];
		
		String tmpMTXAllNum = value[idx++];
		String tmpMTXSuccNum = value[idx++];
		String tmpMTX240Num = value[idx++];
		String tmpMTXErrNum = value[idx++];
		
		String tmpDXAllNum = value[idx++];
		String tmpDXSuccNum = value[idx++];
		String tmpDX240Num = value[idx++];
		String tmpDXErrNum = value[idx++];
		
		String tmpHLNAllNum = value[idx++];
		String tmpHLNSuccNum = value[idx++];
		String tmpHLN240Num = value[idx++];
		String tmpHLNErrNum = value[idx++];
		
		String hfhSuccPerc = calcPerc(sumStr2Double(tmpHFHSuccNum),sumStr2Integer(tmpHFHAllNum), df);
		String hfhFailPerc = calcPerc(sumStr2Double(tmpHFH240Num),sumStr2Integer(tmpHFHAllNum), df);
		String hfhErrPerc = calcPerc(sumStr2Double(tmpHFHErrNum),sumStr2Integer(tmpHFHAllNum), df);
		
		String mtxSuccPerc = calcPerc(sumStr2Double(tmpMTXSuccNum),sumStr2Integer(tmpMTXAllNum), df);
		String mtxFailPerc = calcPerc(sumStr2Double(tmpMTX240Num),sumStr2Integer(tmpMTXAllNum), df);
		String mtxErrPerc = calcPerc(sumStr2Double(tmpMTXErrNum),sumStr2Integer(tmpMTXAllNum), df);
		
		String dxSuccPerc = calcPerc(sumStr2Double(tmpDXSuccNum),sumStr2Integer(tmpDXAllNum), df);
		String dxFailPerc = calcPerc(sumStr2Double(tmpDX240Num),sumStr2Integer(tmpDXAllNum), df);
		String dxErrPerc = calcPerc(sumStr2Double(tmpDXErrNum),sumStr2Integer(tmpDXAllNum), df);
		
		String hlnSuccPerc = calcPerc(sumStr2Double(tmpHLNSuccNum),sumStr2Integer(tmpHLNAllNum), df);
		String hlnFailPerc = calcPerc(sumStr2Double(tmpHLN240Num),sumStr2Integer(tmpHLNAllNum), df);
		String hlnErrPerc = calcPerc(sumStr2Double(tmpHLNErrNum),sumStr2Integer(tmpHLNAllNum), df);
		
		String allSuccPerc = calcPerc(sumStr2Double(tmpHLNSuccNum,tmpMTXSuccNum,tmpDXSuccNum,tmpHLNSuccNum),
				sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum), df);
		
		String allFailPerc = calcPerc(sumStr2Double(tmpHLN240Num,tmpMTX240Num,tmpDX240Num,tmpHLN240Num),
				sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum), df);
		
		String allErrPerc = calcPerc(sumStr2Double(tmpHLNErrNum,tmpMTXErrNum,tmpDXErrNum,tmpHLNErrNum),
				sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum), df);
		
		int aaa = 22;
		result[aaa++] = allSuccPerc;result[aaa++] = hfhSuccPerc;result[aaa++] = mtxSuccPerc;result[aaa++] = dxSuccPerc;result[aaa++] = hlnSuccPerc;
		result[aaa++] = allFailPerc;result[aaa++] = hfhFailPerc;result[aaa++] = mtxFailPerc;result[aaa++] = dxFailPerc;result[aaa++] = hlnFailPerc;
		result[aaa++] = allErrPerc;result[aaa++] = hfhErrPerc;result[aaa++] = mtxErrPerc;result[aaa++] = dxErrPerc;result[aaa++] = hlnErrPerc;
	}
	
	
	static DecimalFormat df = new DecimalFormat("##.#");
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
				// ${tmpHFHAllNum}  ${tmpHFHSuccNum}  ${tmpHFHErrNum}  ${tmpMTXAllNum}  ${tmpMTXSuccNum}  ${tmpMTX240Num} 
				String tmpHFHAllNum = st.nextToken();
				String tmpHFHSuccNum = st.nextToken();
				String tmpHFHErrNum = st.nextToken();
				String tmpHFH240Num = Integer.valueOf(tmpHFHAllNum) - Integer.valueOf(tmpHFHSuccNum) - Integer.valueOf(tmpHFHErrNum) + "";
				String hfhSuccPerc = df.format((Double.valueOf(tmpHFHSuccNum) / Integer.valueOf(tmpHFHAllNum) * 100)) + "%";
				String hfhFailPerc = df.format((Double.valueOf(tmpHFH240Num) / Integer.valueOf(tmpHFHAllNum) * 100)) + "%";
				String hfhErrPerc = df.format((Double.valueOf(tmpHFHErrNum) / Integer.valueOf(tmpHFHAllNum) * 100)) + "%";
						
				String tmpMTXAllNum = st.nextToken();
				String tmpMTXSuccNum = st.nextToken();
				String tmpMTX240Num = st.nextToken();
				String tmpMTXErrNum = Integer.valueOf(tmpMTXAllNum) - Integer.valueOf(tmpMTXSuccNum) - Integer.valueOf(tmpMTX240Num) + "";
				String mtxSuccPerc = df.format((Double.valueOf(tmpMTXSuccNum) / Integer.valueOf(tmpMTXAllNum) * 100)) + "%";
				String mtxFailPerc = df.format((Double.valueOf(tmpMTX240Num) / Integer.valueOf(tmpMTXAllNum) * 100)) + "%";
				String mtxErrPerc = df.format((Double.valueOf(tmpMTXErrNum) / Integer.valueOf(tmpMTXAllNum) * 100)) + "%";
				
				// ${tmpDXAllNum}  ${tmpDXSuccNum}  ${tmpDX240Num}  ${tmpHLNAllNum}  ${tmpHLNSuccNum}  ${tmpHLN240Num}
				String tmpDXAllNum = st.nextToken();
				String tmpDXSuccNum = st.nextToken();
				String tmpDX240Num = st.nextToken();
				String tmpDXErrNum = Integer.valueOf(tmpDXAllNum) - Integer.valueOf(tmpDXSuccNum) - Integer.valueOf(tmpDX240Num) + "";
				String dxSuccPerc = df.format((Double.valueOf(tmpDXSuccNum) / Integer.valueOf(tmpDXAllNum) * 100)) + "%";
				String dxFailPerc = df.format((Double.valueOf(tmpDX240Num) / Integer.valueOf(tmpDXAllNum) * 100)) + "%";
				String dxErrPerc = df.format((Double.valueOf(tmpDXErrNum) / Integer.valueOf(tmpDXAllNum) * 100)) + "%";
				
				String tmpHLNAllNum = st.nextToken();
				String tmpHLNSuccNum = st.nextToken();
				String tmpHLN240Num = st.nextToken();
				String tmpHLNErrNum = Integer.valueOf(tmpHLNAllNum) - Integer.valueOf(tmpHLNSuccNum) - Integer.valueOf(tmpHLN240Num) + "";
				String hlnSuccPerc = calcPerc(sumStr2Double(tmpHLNSuccNum),sumStr2Integer(tmpHLNAllNum), df);
				String hlnFailPerc = calcPerc(sumStr2Double(tmpHLN240Num),sumStr2Integer(tmpHLNAllNum), df);
				String hlnErrPerc = calcPerc(sumStr2Double(tmpHLNErrNum),sumStr2Integer(tmpHLNAllNum), df);
				
				String allNum = sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum) + "";
				String allSuccNum = sumStr2Integer(tmpHFHSuccNum,tmpMTXSuccNum,tmpDXSuccNum,tmpHLNSuccNum) + "";
				String all240Num = sumStr2Integer(tmpHFH240Num,tmpMTX240Num,tmpDX240Num,tmpHLN240Num) + "";
				String allErrNum = sumStr2Integer(tmpHFHErrNum,tmpMTXErrNum,tmpDXErrNum,tmpHLNErrNum) + "";
				
				String allSuccPerc = calcPerc(sumStr2Double(tmpHLNSuccNum,tmpMTXSuccNum,tmpDXSuccNum,tmpHLNSuccNum),
						sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum), df);
				
				String allFailPerc = calcPerc(sumStr2Double(tmpHLN240Num,tmpMTX240Num,tmpDX240Num,tmpHLN240Num),
						sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum), df);
				
				String allErrPerc = calcPerc(sumStr2Double(tmpHLNErrNum,tmpMTXErrNum,tmpDXErrNum,tmpHLNErrNum),
						sumStr2Integer(tmpHFHAllNum,tmpMTXAllNum,tmpDXAllNum,tmpHLNAllNum), df);
				
				Date d1 = DateUtil.StringToDate(date + " " + min + "0", DateStyle.YYYY_MM_DD_HH_MM);
				Date d2 = DateUtil.StringToDate(date + " 11:00", DateStyle.YYYY_MM_DD_HH_MM);
				Date d3 = DateUtil.StringToDate(date + " 15:30", DateStyle.YYYY_MM_DD_HH_MM);
//				if (d1.after(d2) && d1.before(d3))
//				{
					content.put(date + "" + min, new String[] { date, min
							,allNum,allSuccNum,all240Num,allErrNum
							,tmpHFHAllNum, tmpHFHSuccNum, tmpHFH240Num,tmpHFHErrNum, tmpMTXAllNum, tmpMTXSuccNum, tmpMTX240Num,tmpMTXErrNum
							,tmpDXAllNum, tmpDXSuccNum, tmpDX240Num,tmpDXErrNum, tmpHLNAllNum, tmpHLNSuccNum, tmpHLN240Num,tmpHLNErrNum 
							,allSuccPerc, hfhSuccPerc, mtxSuccPerc, dxSuccPerc, hlnSuccPerc
							,allFailPerc, hfhFailPerc, mtxFailPerc, dxFailPerc, hlnFailPerc
							,allErrPerc, hfhErrPerc, mtxErrPerc, dxErrPerc, hlnErrPerc});
//				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return content;
	}
	
	private static Double sumStr2Double(String... paras)
	{
		Double d1 = new Double(0);
		for (String str : paras)
		{
			d1 = d1 + (Double.valueOf(str));
		}
		return d1;
	}
	
	private static Integer sumStr2Integer(String... paras)
	{
		Integer d1 = new Integer(0);
		for (String str : paras)
		{
			d1 = d1 + (Integer.valueOf(str));
		}
		return d1;
	}
	
	private static String calcPerc(Double d1, Integer d2, DecimalFormat df)
	{
		if (d1 == 0) 
		{
			return "0.0%";
		}
		if (d2 == 0) 
		{
			return "100%";
		}
		
		return df.format(d1 / d2 * 100) + "%";
	}

	private static List<File> getLogFileNames()
	{
		File dir = new File("E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析");
		File[] paths = dir.listFiles();
		List<File> files = new ArrayList<File>();
		for (File file : paths)
		{
			if (file.getAbsolutePath().matches(".*channel_.*.log"))
			{
				System.out.println(file.getAbsolutePath());
				files.add(file);
			}
		}
		return files;
	}
}
