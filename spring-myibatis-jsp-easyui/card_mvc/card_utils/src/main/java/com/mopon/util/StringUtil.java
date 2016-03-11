package com.mopon.util;

import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.Joinable;


public class StringUtil {
	
	
	public static boolean isNull(String string) {
		if (null == string || "".equals(string)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 方法用途: 判断字符串是否是大写 <br>
	 * 实现步骤: <br>
	 * 
	 * @param word
	 * @return true 是 false不是
	 */
	public static boolean isAcronym(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isLowerCase(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * 方法用途: 判断字符串是否是字母<br>
	 * 实现步骤: <br>
	 * 
	 * @param word
	 * @return true 是 false不是
	 */
	public static boolean isLetter(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}
	/**
	 * 功能：不定长参数,其中一个参数为null或空或为空格字符串则返回true,负责返回false
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isBlank(String... str) {
		for (String s : str) {
			if (isBlank(s))
				return true;
		}
		return false;
	}
	// 将String 数据转成Sting返回，以逗号分隔。
	public static String array2String(String[] array) {
		String rs = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]).append(",");

		}
		if (sb.length() > 0) {
			rs = sb.substring(0, sb.length() - 1);
		}
		return rs;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 给凭证号加密
	 * @param encryString
	 * @return
	 */
	public static String EncryString(String encryString){
		String result="";
		int n=4;
		if(!encryString.equals("") && encryString.length()>n){
			result  = encryString.substring(0, n)+"******"+encryString.substring(encryString.length()-n);
		}
		return result;
	}
	
	/**
	 * 功能描述：<p>重载同样的方法</p>
	 * 实现逻辑：<p></p>
	 * @param strs
	 * @param separator
	 * @return
	 */
	public static String join(List<String> strs, String separator) {
		return join(strs.toArray(new String[] {}), separator);
	}
	
	/**
	 * 功能描述：<p>将数组使用分隔符链接</p>
	 * 实现逻辑：<p>遍历数组并使用分隔符链接，且去掉最后多余的分隔符</p>
	 * @param strs
	 * @param separator
	 * @return
	 */
	public static String join(String[] strs, String separator) {
		Assert.notNull(strs);
		Assert.notNull(separator);

		StringBuilder builder = new StringBuilder();
		for (String str : strs) {
			builder.append(str + separator);
		}

		String result = builder.toString();
		if (!separator.isEmpty()) {
			result = substringBeforeLast(result, separator);
		}
		return result;
	}
	
	/**
	 * 功能描述：<p>返回字符串最后一个分隔符之前的部分</p>
	 * 实现逻辑：<p>先获取最后一个分隔符位置，如果存在，则返回分隔符之前的字符串；否则，返回原字符串</p>
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringBeforeLast(String str, String separator) {
		Assert.notNull(str);
		Assert.notEmpty(separator);

		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}
	
	public static void main(String[] args) {

		System.out.println(!isLetter("DD"));
	}
	
	
}
