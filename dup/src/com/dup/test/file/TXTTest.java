/**
 * 
 */
package com.dup.test.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.dup.test.Constant;

/**
 * @author hugoyang
 * 
 */
public class TXTTest {

	public List<String> read(String path) throws IOException {
		return FileUtils.readLines(new File(path));
	}

	public static String getInterfaceInfo(List<String> content) {
		if (content == null)
			return null;

		int idx = 0;
		StringBuilder sb = new StringBuilder();

		for (String line : content) {
			if ("接口名称".equals(line)) {
				sb.append(content.get(idx - 1)).append("")
						.append(content.get(idx + 1))
						.append(Constant.LINE_SEPARATOR);

			}
			idx++;
		}
		return sb.toString();
	}

	public static String compare(List<String> xls, List<String> doc) {
		StringBuilder sb = new StringBuilder();
		for (String str : xls) {
			if (!doc.contains(str)) {
				sb.append(str).append(Constant.LINE_SEPARATOR);
				;
			}
		}
		return sb.toString();
	}

	public static void  testWriteFile() throws IOException
	{
		String filePath = "E:\\show7\\api\\ticket\\v1\\queryShows\\0001\\1003972\\2015-11-21_all.json";
		String fileC1 = FileUtils.readFileToString(new File(filePath));
		
		String filePath2 = "E:\\show7\\api\\ticket\\v1\\queryShows\\0001\\1003972\\2015-11-21_all1.json";
		String fileC2 = FileUtils.readFileToString(new File(filePath2));
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			File file1 = new File("e://tt.xtx");
			FileUtils.writeStringToFile(file1, fileC1);
			
			
			File file2 = new File("e://tttt.xtx");
			FileUtils.writeStringToFile(file2, fileC2+ i);
			
			FileUtils.copyFile(file2, file1);
			file2.delete();
		}
		System.out.println(System.currentTimeMillis() - start);
		
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			File file1 = new File("e://tt.xtx");
			FileUtils.writeStringToFile(file1, fileC1);
			
			
			File file2 = new File("e://tttt.xtx");
			FileUtils.writeStringToFile(file2, fileC2 + i);
			
			file1.delete();
			file2.renameTo(file1);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String classPath = TXTTest.class.getResource("").getPath();
		String txtPath = classPath.substring(1) + "新OC街口文档V1.74-outcome.txt";
		// System.out.println(txtPath);
		List<String> content = new TXTTest().read(txtPath);
		// System.out.println(content);

		String xlstxtPath = classPath.substring(1) + "xls-src.txt";
		// System.out.println(txtPath);
		List<String> xlscontent = new TXTTest().read(xlstxtPath);

		// String interfaces = getInterfaceInfo(content);
		// System.out.println(interfaces);

		String str1 = compare(xlscontent, content);
		System.out.println(str1);

		// String str2 = compare(content, xlscontent);
		// System.out.println(str2);
	}
}
