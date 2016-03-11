package com.mopon.util;

public class ConvertSeatsUtils {

	/**
	 * 座位，分割的，转换为 汉字：3排4座，5排6座
	 * scec单个座位格式: 1_1 
	 * 大地单个座位格式: 1|1|1   (区域|列|行)
	 * 洲立单个座位格式:0000000000|1|5|20   (AreaCatCode|AreaNum|Row|Seat) 
	 * 解析规则:用分割符得到的总数超过2,取最后两个数值.
	 * @param seatCodes
	 * @return
	 */
	public static String convertSeatsName(String seatCodes){
		String name = "";
		String[] codes  = seatCodes.split(",");
		
		//判断座位分割符( _, |) ,并且判断格式: 场区_排名称_列名称, 排名称_列名称
		for(int i=0;i<codes.length;i++){
			if(i > 0) name += ",";
			String singleSeat = codes[i];
			singleSeat =singleSeat.replace("|","_");
			
			int s= singleSeat.split("\\_").length;
			if(s>2) {
				String[] arr =singleSeat.split("\\_");
				singleSeat = arr[s-2] + "_" + arr[s-1];
			}				
			
			name +=singleSeat.replace("_","排") + "座";
		}
		return name;		
	} 
}
