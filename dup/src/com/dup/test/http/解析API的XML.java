/**
 * 项目名称：scec-saas-server
 * 文件包名：com.mopon.cec
 * 文件名称：解析API的HTML.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年10月8日 下午7:49:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.http;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @功能描述：解析API转换的HTML文档
 * @文件名称：解析API的HTML.java
 * @author ly
 */
public class 解析API的XML {

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws IOException, DocumentException {
		String apiPath = "D:/Work/Workspace/scec-saas/scec-saas-server/src/main/java/backstage_test_html/02_接口文档/"
				//				+ "泰久云平台2.0[SCEC]-权益卡模块接口 - 叶茂林.xml";
				+ "泰久云平台2.0[SCEC]-权益卡模块接口 - 黄新艺.XML";
		//				+ "泰久云平台2.0[SCEC]-权益卡模块接口 - 白士伟.xml";
		File srcFile = new File(apiPath);

		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(srcFile);

		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());

		// 获取特定名称的子元素
		//		List<Element> childList2 = root
		//				.selectNodes("/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:p/w:r/w:t");
		//		System.out.println("hello child: " + childList2.size());

		changeLineFile = new File("changeLine_" + srcFile.getName() + ".txt");
		FileUtils.write(file, "-----------------------------\r\n", false);
		FileUtils.write(changeLineFile, "-----------------------------\r\n", false);
		getChild(root);
	}

	public static boolean isInterFlag = true;
	public static String interPath = "/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:p/w:r/w:t";
	public static String changeLinePath = "/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:p";
	public static String tabPath = "/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:tbl";
	public static String tabTrPath = "/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:tbl/w:tr";
	public static String tabTrTcPath = "/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:tbl/w:tr/w:tc";
	public static String tabTrTcTrPath = "/pkg:package/pkg:part/pkg:xmlData/w:document/w:body/w:tbl/w:tr/w:tc";
	public static String filePath = "log.txt";
	public static File file = new File(filePath);
	public static File changeLineFile = new File("changeLine.txt");

	public static void getChild(Element root) throws IOException {
		String data = root.getData().toString();
		if (!"".equals(data)){
			FileUtils.write(file, root.getPath() + "    " + root.getName() + "   " + data + "\r\n", true);
			System.out.println(root.getPath() + "    " + root.getName() + "   " + data);
		}

		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			String path = e.getPath();
			String pathTmp = path.replaceAll(":", "").replaceAll("/", "");
			String inferPathTmp = interPath.replaceAll(":", "").replaceAll("/", "");
			if (pathTmp.equals(inferPathTmp)) {
				System.out.println("-----------------------------");
				FileUtils.write(file, "-----------------------------\r\n", true);
				isInterFlag = false;
			}
			if (pathTmp.equals(changeLinePath.replaceAll(":", "").replaceAll("/", ""))) {
				System.out.println("*************************");
				FileUtils.write(changeLineFile, "\r\n" + e.getStringValue(),
						true);
				isInterFlag = false;
			}
			if (pathTmp.equals(tabTrPath.replaceAll(":", "").replaceAll("/", ""))) {
				FileUtils.write(changeLineFile, "\r\n|", true);
				isInterFlag = false;
			}
			if (pathTmp.equals(tabTrTcPath.replaceAll(":", "").replaceAll("/", ""))) {
				String content = e.getStringValue();
				content = content.replaceAll(" ", "&nbsp;");
				FileUtils.write(changeLineFile, content + "|", true);
				isInterFlag = false;
			}

			getChild(e);
		}
	}
}
