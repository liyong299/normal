/**
 * 
 */
package com.dup.test.string;

import java.io.ObjectInputStream.GetField;
import java.util.StringTokenizer;

/**
 * @author ly
 *
 */
public class StringTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		getDistInfo("192.168.25.84|2|0#192.168.25.84|2|1");
		
		Integer divisor = Integer.parseInt("2");
		Integer remainder = Integer.parseInt("0");
		String cinemaCode = "wfwfwfw";
		char lastChar = cinemaCode.charAt(cinemaCode.length() - 1);
		if (lastChar % divisor == remainder)
		{
			System.out.println(cinemaCode);
		}
	}
	
	public static String[]	getDistInfo(String syncShowDist)
	{
		StringTokenizer st = new StringTokenizer(syncShowDist, "#");
		while (st.hasMoreTokens())
		{
			System.out.println(st.nextToken());
		}
		return null;
	}
}
