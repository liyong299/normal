///**
// * 
// */
//package com.dup.test.静态化验证;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.io.FileUtils;
//import org.jsoup.Connection;
//import org.jsoup.Connection.Method;
//import org.jsoup.Jsoup;
//
//import cn.mopon.cec.core.access.show.ShowsReply;
//import cn.mopon.cec.core.access.show.vo.ShowVo;
//import cn.mopon.cec.core.util.JsonUtil;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.dup.test.util.DateUtil;
//
///**
// * @author ly
// *
// */
//public class 静态化验证入口
//{
//	public 静态化验证入口(String oldURL, String newURL)
//	{
//		this.oldURL = oldURL;
//		this.newURL = newURL;
//	}
//	
//	int date_range = 4;
//	String diffFileName = "diff.txt";
//	static String lineSeparator = System.getProperty("line.separator");
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String[] args) throws IOException
//	{		
//		String oldURL = "http://a.piaowutong.cc:8181/api/ticket/v1/queryShows.json?";
//		String newURL = "http://192.168.54.15:8080/api/ticket/v1/queryShows.json?";
//		
//		String cinemas[] = {"1011519", "53250701", "53290401", "61012501", "61080301", "62013701"};
//		String channels[] = {"0001","0002","0003","0004","0005","0006","0007","0008"};
//		
//		for (String cinemaCode : cinemas)
//		{
//			for (String channelCode : channels)
//			{
//				List<CompResp> list = new 静态化验证入口(oldURL, newURL).comp(cinemaCode, channelCode);
//				System.out.println("");
//				System.out.println("-----------------------");
////				System.out.println(list);
//				
////				System.out.println(System.getProperties());
//				
//				FileUtils.write(new File(channelCode + "/" + cinemaCode + "/aaaBBaa.txt"), list.toString()
//						.replaceAll("newContent", lineSeparator + "newContent").replaceAll("oldContent",  lineSeparator + "oldContent"));
//				
//				System.out.println(new CompResp());
//				
//				System.out.println("-----------------------");
//			}
//		}
//		
////		System.out.println(Enum.valueOf(ShelveStatus.class, "OFF"));
//		
////		System.out.println(JsonUtil.generateSpecialEnumJson(ShowType.DMAX2D));
//		
////		File file = new File("e://11.txt");
////		if (!file.exists())
////		{
////			file.createNewFile();
////		}
////		OutputStream os = new FileOutputStream(file, true);
//		
////		System.out.println(JsonUtil.generateSpecialEnumJson(ShowType.DMAX2D));
////		
////		System.out.println(SystemConstats.UTF8.name());
////		
////		System.out.println(JSONObject.toJSONString(ShowType.NORMAL3D, SerializerFeature.WriteEnumUsingToString));
//		
//	}
//	
//	/**
//	 * 对比所有影院的所有渠道
//	 * @param cinemaCode
//	 * @return 不返回，正确的信息，量太大。
//	 * @throws IOException 
//	 */
//	public List<CompResp> comp() throws IOException
//	{
//		Set<String> cinemaCodes = new DBUtils().queryCinemaCodes();
//		for (String cinemaCode : cinemaCodes)
//		{
//			List<CompResp> list = new ArrayList<CompResp>();
//			list.addAll(comp(cinemaCode));
//
//			FileUtils.write(new File("result/aaaaa_" + cinemaCode + "_.txt"), list.toString()
//					.replaceAll("newContent", lineSeparator + "newContent").replaceAll("oldContent",  lineSeparator + "oldContent"));
//		}
//		return new ArrayList<CompResp>();
//	}
//	
//	/**
//	 * 对比一个影院下的所有渠道
//	 * @param cinemaCode
//	 * @return
//	 * @throws IOException 
//	 */
//	public List<CompResp> comp(String cinemaCode) throws IOException
//	{
//		Set<String> channelCodes = new DBUtils().queryChannelCodesByCinemaCode(cinemaCode);
//		List<CompResp> list = new ArrayList<CompResp>();
//		for (String channelCode : channelCodes)
//		{
//			list.addAll(comp(cinemaCode, channelCode));
//		}
//		return list;
//	}
//	
//	String oldURL = "";
//	String newURL = "";
//	
//	public List<CompResp> comp(String cinemaCode, String channelCode) throws IOException
//	{
//		List<CompResp> list = new ArrayList<CompResp>();
//		for (int i = 0; i < date_range; i++)
//		{
//			Date date = DateUtil.addDay(i);
//			String dateStr = DateUtil.getYearmonthDay(date);
//			
//			get(cinemaCode, channelCode, dateStr, "1", list);
//			get(cinemaCode, channelCode, dateStr, "", list);
//		}
//		return list;
//		
//	}
//	
//	private void get(String cinemaCode, String channelCode, String dateStr, String status, List<CompResp> list) throws IOException
//	{
//		String oldContent = getHttpReturn(cinemaCode, channelCode, dateStr, oldURL, status);
//		String newContent = getHttpReturn(cinemaCode, channelCode, dateStr, newURL, status);
//		
////		oldContent = "===" + oldContent + "===";
////		newContent = "+++" + newContent + "+++";
//		if (oldContent == null || newContent == null)
//		{
//			list.add(new CompResp(oldContent, newContent, false, channelCode, cinemaCode, dateStr, status));
//		}
//		else if ((oldContent != null && newContent != null))
//		{
//			Map<String, DiffVo> diffVos = new HashMap<String, DiffVo>();
//			if (compResult(oldContent, newContent, diffVos))
//			{
//				list.add(new CompResp("--", "--", true, channelCode, cinemaCode, dateStr, status));
////				list.add(new CompResp(oldContent, newContent, true, channelCode, cinemaCode, dateStr, status));
//			}
//			else
//			{
//				list.add(new CompResp(oldContent, newContent, false, channelCode, cinemaCode, dateStr, status, diffVos));
//			}
//		}
//		else
//		{
//			list.add(new CompResp(oldContent, newContent, false, channelCode, cinemaCode, dateStr, status));
//		}
//	}
//	
//	private String specialModifyContent(String content)
//	{
//		return content.replaceAll("\"status\":\"0\"", "\"status\":\"OFF\"")
//				.replaceAll("\"status\":\"1\"", "\"status\":\"ON\"")
//				.replaceAll("\"status\":\"2\"", "\"status\":\"INVALID\"")
//				
//				.replaceAll("\"showType\":\"2D\"", "\"showType\":\"NORMAL2D\"")
//				.replaceAll("\"showType\":\"3D\"", "\"showType\":\"NORMAL3D\"")
//				.replaceAll("\"showType\":\"其他\"", "\"showType\":\"OTHER\"")
//				
//				.replaceAll("\"showType\":\"1\"", "\"showType\":\"NORMAL2D\"")
//				.replaceAll("\"showType\":\"2\"", "\"showType\":\"NORMAL3D\"")
//				.replaceAll("\"showType\":\"3\"", "\"showType\":\"MAX3D\"")
//				.replaceAll("\"showType\":\"4\"", "\"showType\":\"DMAX2D\"")
//				.replaceAll("\"showType\":\"5\"", "\"showType\":\"PF\"")
//				.replaceAll("\"showType\":\"6\"", "\"showType\":\"OTHER\"")
//				.replaceAll("\"showType\":\"8\"", "\"showType\":\"DMAX2D\"")
//				.replaceAll("\"showType\":\"9\"", "\"showType\":\"DMAX3D\"");
//	}
//	
//	private boolean compResult(String oldContent, String newContent, Map<String, DiffVo> diffVos) throws IOException
//	{
//		boolean flag = true;
//		try
//		{
//			oldContent = specialModifyContent(oldContent);
//			newContent = specialModifyContent(newContent);
//			
//			ShowsReply oldReply = JSONObject.toJavaObject(JSON.parseObject(oldContent), ShowsReply.class);
//			ShowsReply newReply = JSONObject.toJavaObject(JSON.parseObject(newContent), ShowsReply.class);
//			
////			ShowsReply newReply = JSONObject.toJavaObject(JSON.parseObject(newContent), ShowsReply.class);
////			ShowsReply oldReply = JSONObject.toJavaObject(JSON.parseObject(oldContent), ShowsReply.class);
//			
//			FileUtils.writeStringToFile(new File(diffFileName), "");
//			
//			
//			if (oldReply.getCode().equals(newReply.getCode()) && oldReply.getMsg().equals(newReply.getMsg()))
//			{
//				List<ShowVo> oldList = oldReply.getShows();
//				List<ShowVo> newList = newReply.getShows();
//				
//				for (ShowVo oldvo : oldList)
//				{
//					boolean hasCode = false;
//					for (ShowVo newvo : newList)
//					{
//						if (oldvo.getChannelShowCode().equals(newvo.getChannelShowCode()))
//						{
//							hasCode = true;
//							Map<String, String> diffMap = new HashMap<String, String>();
//							if (!compObj(ShowVo.class, oldvo, newvo, diffMap))
//							{
//								System.out.println(oldContent);
//								System.out.println(newContent);
////								FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(oldvo) + lineSeparator);
////								FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(newvo), true);
//								flag = false;
//								
//								diffVos.put(oldvo.getChannelShowCode(), new DiffVo(oldvo, newvo, diffMap));
//							}
//						}
//					}
//					
//					if (!hasCode)
//					{
//						System.out.println(oldContent);
//						System.out.println(newContent);
//						
////						FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(oldvo) + lineSeparator);
////						FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(""), true);
//						
//						diffVos.put(oldvo.getChannelShowCode() + "-----------", new DiffVo(oldvo, null, null));
//						flag = false;
//					}
//				}
//				
//				for (ShowVo newvo : newList)
//				{
//					boolean hasCode = false;
//					for (ShowVo oldvo : oldList)
//					{
//						if (oldvo.getChannelShowCode().equals(newvo.getChannelShowCode()))
//						{
//							hasCode = true;
//							Map<String, String> diffMap = new HashMap<String, String>();
//							if (!compObj(ShowVo.class, oldvo, newvo, diffMap))
//							{
//								System.out.println(oldContent);
//								System.out.println(newContent);
////								FileUtils.writeStringToFile(new File(diffFileName), "-------------", true);
////								FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(oldvo) + lineSeparator);
////								FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(newvo), true);
//								
//								diffVos.put(oldvo.getChannelShowCode(), new DiffVo(oldvo, newvo, diffMap));
//								flag = false;
//							}
//						}
//					}
//					if (!hasCode)
//					{
//						System.out.println(oldContent);
//						System.out.println(newContent);
////						FileUtils.writeStringToFile(new File(diffFileName), "-------------", true);
////						FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(newvo) + lineSeparator);
////						FileUtils.writeStringToFile(new File(diffFileName), JsonUtil.generateSpecialEnumJson(""), true);
//						flag = false;
//						diffVos.put(newvo.getChannelShowCode() + "==========", new DiffVo(null, newvo, null));
//					}
//				}
//			}
//			
//			FileUtils.writeStringToFile(new File(diffFileName), diffVos.toString(), true);
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//			FileUtils.writeStringToFile(new File(diffFileName), oldContent + lineSeparator + newContent, true);
//			
//		}
//		
//		return flag;
//	}
//	
//	public boolean compObj(Class cls,  Object obj1, Object obj2, Map<String, String> diffmap)
//	{
//		if (obj1 == null || obj2 == null)
//			return true;
//		Field[] fields = cls.getDeclaredFields();
//		
//		String[] types1 = { "int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte" };
//		String[] types2 = { "Integer", "java.lang.String", "java.lang.Boolean", "java.lang.Character",
//				"java.lang.Float", "java.lang.Double", "java.lang.Long", "java.lang.Short", "java.lang.Byte" };
//		
//		boolean flag = true;
//		for (int j = 0; j < fields.length; j++)
//		{
//			fields[j].setAccessible(true);
//			
//			// 字段名  
//			System.out.print(fields[j].getName() + ":");
//			
//			// 字段值  
//			for (int i = 0; i < types1.length; i++)
//			{
//				if (fields[j].getType().getName().equalsIgnoreCase(types1[i])
//						|| fields[j].getType().getName().equalsIgnoreCase(types2[i]))
//				{
//					try
//					{
//						String oldV = fields[j].get(obj1).toString();
//						String newV = fields[j].get(obj2).toString();
//						
//						if (oldV == null && newV == null)
//						{
//							
//						}
//						else if (oldV != null && newV == null)
//						{
//							diffmap.put("field_" + fields[j].getName(), "[" + oldV + "][" + newV + "]");
//							flag = false;
//						}
//						else if (oldV == null && newV != null)
//						{
//							diffmap.put("field_" + fields[j].getName(), "[" + oldV + "][" + newV + "]");
//							flag = false;
//						}
//						else if (!oldV.equals(newV))
//						{
//							diffmap.put("field_" + fields[j].getName(), "[" + oldV + "][" + newV + "]");
//							flag = false;
//						}
////						System.out.print(fields[j].get(obj) + "     ");
//					}
//					catch (Exception e)
//					{
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		
//		return flag;
//	}
//
//
//	private String getHttpReturn(String cinemaCode, String channelCode, String date, String httpURL, String status)
//	{
//		Connection.Response res = null;
//		try {			
//			res = Jsoup.connect(httpURL)
//					  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
//					  .timeout(10000)
//					  .method(Method.GET)
//					  .data("cinemaCode", cinemaCode)
//					  .data("channelCode", channelCode)
//					  .data("startDate", date)
//					  .data("status", status)
//					  .data("sign", channelCode)
//					  .execute();
//			String content = res.body();
//			
//			System.out.println("result : " + content);
//			return content;
//		}
//		catch(Exception ex)
//		{
//			System.out.printf("channelCode:[%s] cinemaCode:[%s] httpURL:[%s] status:[%s] date:[%s]", 
//					channelCode, cinemaCode, httpURL, status, date);
//			ex.printStackTrace();
//			System.exit(1);
//			return null;
//		}
//	}
//	
//	
//	static class DiffVo
//	{
//		private ShowVo oldVo;
//		private ShowVo newVo;
//		private Map<String, String> diffMap;
//		
//		public DiffVo(ShowVo oldVo, ShowVo newVo, Map<String, String> diffMap)
//		{
//			this.diffMap = diffMap;
//			this.oldVo = oldVo;
//			this.newVo = newVo;
//		}
//		
//		@Override
//		public String toString()
//		{
//			try
//			{
//				return JsonUtil.generateSpecialEnumJson(this).toString();
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//				return null;
//			}
//		}
//
//		/**
//		 * @return the oldVo
//		 */
//		public ShowVo getOldVo()
//		{
//			return oldVo;
//		}
//
//		/**
//		 * @param oldVo the oldVo to set
//		 */
//		public void setOldVo(ShowVo oldVo)
//		{
//			this.oldVo = oldVo;
//		}
//
//		/**
//		 * @return the newVo
//		 */
//		public ShowVo getNewVo()
//		{
//			return newVo;
//		}
//
//
//
//		/**
//		 * @param newVo the newVo to set
//		 */
//		public void setNewVo(ShowVo newVo)
//		{
//			this.newVo = newVo;
//		}
//
//
//
//		/**
//		 * @return the diffMap
//		 */
//		public Map<String, String> getDiffMap()
//		{
//			return diffMap;
//		}
//
//
//
//		/**
//		 * @param diffMap the diffMap to set
//		 */
//		public void setDiffMap(Map<String, String> diffMap)
//		{
//			this.diffMap = diffMap;
//		}
//	}
//	static class CompResp
//	{
//		private boolean flag;
//		private String oldContent;
//		private String newContent;
//		private String status;
//		private String date;
//		private String channelCode;
//		private String cinemaCode;
//		private Map<String, DiffVo> diffVos;
//		
//		public CompResp(String oldContent, String newContent, boolean flag,String channelCode,String cinemaCode,String date, String status, Map<String, DiffVo> diffVos)
//		{
//			this.flag = flag;
//			this.newContent = newContent;
//			this.oldContent = oldContent;
//			this.channelCode = channelCode;
//			this.cinemaCode = cinemaCode;
//			this.date = date;
//			this.status = status;
//			this.diffVos = diffVos;
//		}
//		
//		public CompResp()
//		{
//			this(null, null, false, null, null, null, null);
//		}
//		
//		public CompResp(String oldContent, String newContent, boolean flag,String channelCode,String cinemaCode,String date, String status)
//		{
//			this(oldContent, newContent, flag, channelCode, cinemaCode, date, status, null);
//		}
//		
//		/**
//		 * @return the flag
//		 */
//		public boolean isFlag()
//		{
//			return flag;
//		}
//		/**
//		 * @param flag the flag to set
//		 */
//		public void setFlag(boolean flag)
//		{
//			this.flag = flag;
//		}
//		/**
//		 * @return the oldContent
//		 */
//		public String getOldContent()
//		{
//			return oldContent;
//		}
//		/**
//		 * @param oldContent the oldContent to set
//		 */
//		public void setOldContent(String oldContent)
//		{
//			this.oldContent = oldContent;
//		}
//		/**
//		 * @return the newContent
//		 */
//		public String getNewContent()
//		{
//			return newContent;
//		}
//		/**
//		 * @param newContent the newContent to set
//		 */
//		public void setNewContent(String newContent)
//		{
//			this.newContent = newContent;
//		}
//		/* (non-Javadoc)
//		 * @see java.lang.Object#toString()
//		 */
//		@Override
//		public String toString()
//		{
//			try
//			{
//				return JsonUtil.generateSpecialEnumJson(this).toString();
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//				return null;
//			}
//		}
//		/**
//		 * @return the status
//		 */
//		public String getStatus()
//		{
//			return status;
//		}
//		/**
//		 * @param status the status to set
//		 */
//		public void setStatus(String status)
//		{
//			this.status = status;
//		}
//		/**
//		 * @return the date
//		 */
//		public String getDate()
//		{
//			return date;
//		}
//		/**
//		 * @param date the date to set
//		 */
//		public void setDate(String date)
//		{
//			this.date = date;
//		}
//		/**
//		 * @return the channelCode
//		 */
//		public String getChannelCode()
//		{
//			return channelCode;
//		}
//		/**
//		 * @param channelCode the channelCode to set
//		 */
//		public void setChannelCode(String channelCode)
//		{
//			this.channelCode = channelCode;
//		}
//		/**
//		 * @return the cinemaCode
//		 */
//		public String getCinemaCode()
//		{
//			return cinemaCode;
//		}
//		/**
//		 * @param cinemaCode the cinemaCode to set
//		 */
//		public void setCinemaCode(String cinemaCode)
//		{
//			this.cinemaCode = cinemaCode;
//		}
//
//		/**
//		 * @return the diffVos
//		 */
//		public Map<String, DiffVo> getDiffVos()
//		{
//			return diffVos;
//		}
//
//		/**
//		 * @param diffVos the diffVos to set
//		 */
//		public void setDiffVos(Map<String, DiffVo> diffVos)
//		{
//			this.diffVos = diffVos;
//		}
//		
//		
//	}
//
//}
//
