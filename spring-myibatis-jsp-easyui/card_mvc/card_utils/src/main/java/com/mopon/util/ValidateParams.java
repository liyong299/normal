/**
 * 
 */
package com.mopon.util;

/**
 * @author moponyf00111
 *
 */
public class ValidateParams {

	
	/**
	 * 是否是 数字
	 * @param str
	 * @return
	 */
	 public static boolean isNumeric2(String str){
	  for (int i = str.length();--i>=0;){
	   if (!Character.isDigit(str.charAt(i))){
	    return false;
	   }
	  }
	  return true;
	 }
	 public static boolean isPhone(String str){
		 if(!isNumeric2(str)) return false;
		 
		 if(!str.startsWith("1")){
			 return false;
		 }
		 if(str.length() != 11){
			 return false;
		 }
		 return true;
	 }
	 
	 /**
	  * 对金额的校验 都是分 整数
	  * @param price
	  * @return
	  */
	public static boolean isPrice(String price) {
		
		try{
			Integer.valueOf(price);
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
