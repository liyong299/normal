/**
 * ��Ŀ��ƣ�excel
 * �ļ�����excel
 * �ļ���ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * ������ڣ�2016��1��21�� ����7:19:00
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package excel;

import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * @author ly
 *
 */
public class Test
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		if ("2842".matches("(\\d*)|(\\d*.\\d*%)|(\\d*.\\d*)"))
		{
			System.out.println("---------");
		}
		System.out.println((4/5  * 100));
		System.out.println("exe is run");
		throw new Exception("test");
	}

}
