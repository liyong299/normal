/**
 * 项目名称：excel
 * 文件包名：excel
 * 文件名称：LockUtils.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月10日 下午3:43:18
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package excel;

import java.text.DecimalFormat;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：LockUtils.java
 * @author ly
 */
public class LockUtils
{

	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}
	public static Double sumStr2Double(String... paras)
	{
		Double d1 = new Double(0);
		for (String str : paras)
		{
			d1 = d1 + (Double.valueOf(str));
		}
		return d1;
	}
	
	public static Integer sumStr2Integer(String... paras)
	{
		Integer d1 = new Integer(0);
		for (String str : paras)
		{
			d1 = d1 + (Integer.valueOf(str));
		}
		return d1;
	}
	
	public static String calcPerc(Double d1, Integer d2, DecimalFormat df)
	{
		if (d2 == 0) 
		{
			return "100%";
		}
		if (d1 == 0) 
		{
			return "0.0%";
		}
		
		return df.format(d1 / d2 * 100) + "%";
	}
}
