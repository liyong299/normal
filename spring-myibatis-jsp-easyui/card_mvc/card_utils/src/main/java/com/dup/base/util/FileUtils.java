package com.dup.base.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dup.base.constants.Chars;
import com.dup.base.constants.Measure;
import com.dup.base.exception.UncheckedException;

/**
 * 文件操作工具类。
 */
public class FileUtils {
	private static final int DEFAULT_BUFFER_SIZE = 4 * Measure.K;



	/**
	 * 从带有类型后缀的文件名中获取不带类型后缀的文件名。
	 * 
	 * @param fileName
	 *            带有类型后缀的文件名
	 * @return 返回不带类型后缀的文件名。
	 */
	public static String getFileName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 从带有类型后缀的文件名中获取文件名的类型后缀。
	 * 
	 * @param fileName
	 *            带有类型后缀的文件名
	 * @return 返回文件名的类型后缀。
	 */
	public static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 从文件完整路径中截取完整的文件名。
	 * 
	 * @param filePath
	 *            文件完整路径
	 * @return 返回从文件完整路径中截取完整的文件名。
	 */
	public static String getFullFileName(String filePath) {
		int lastSlashIndex = filePath.lastIndexOf(Chars.SLASH);
		int lastBackSlashIndex = filePath.lastIndexOf(Chars.BACKSLASH);
		// 如果没找到斜杠和反斜杠则返回原文件路径
		// 如果斜杠位置在反斜杠位置之后则从斜杠位置截取文件名
		// 否则从反斜杠位置截取文件名
		if (lastSlashIndex == -1 && lastBackSlashIndex == -1) {
			return filePath;
		} else if (lastSlashIndex > lastBackSlashIndex) {
			return StringUtils.substringAfterLast(filePath, Chars.SLASH);
		} else {
			return StringUtils.substringAfterLast(filePath,
					Chars.BACKSLASH);
		}
	}

	/**
	 * 从文件完整路径中截取完整的文件目录。
	 * 
	 * @param filePath
	 *            文件完整路径
	 * @return 返回从文件完整路径中截取完整的文件目录。
	 */
	public static String getFullFileDir(String filePath) {
		int lastSlashIndex = filePath.lastIndexOf(Chars.SLASH);
		int lastBackSlashIndex = filePath.lastIndexOf(Chars.BACKSLASH);
		// 如果没找到斜杠和反斜杠则返回空字符串
		// 如果斜杠位置在反斜杠位置之后则从斜杠位置截取文件目录
		// 否则从反斜杠位置截取文件目录
		if (lastSlashIndex == -1 && lastBackSlashIndex == -1) {
			return "";
		} else if (lastSlashIndex > lastBackSlashIndex) {
			return StringUtils.substringBeforeLast(filePath, Chars.SLASH);
		} else {
			return StringUtils.substringBeforeLast(filePath,
					Chars.BACKSLASH);
		}
	}

	/**
	 * 根据文件的完整路径创建一个新文件。如果目录不存在时先创建目录再创建文件。
	 * 
	 * @param filePath
	 *            文件完整路径
	 * @return 返回创建的File文件对象。
	 */
	public static File createFile(String filePath) {
		try {
			File fileDir = new File(getFullFileDir(filePath));
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}

			File file = new File(filePath);
			file.createNewFile();
			return file;
		} catch (IOException e) {
			throw new UncheckedException("创建文件时发生错误。", e);
		}
	}

	/**
	 * 将输入流转换为字节数组。
	 * 
	 * @param in
	 *            输入流
	 * @return 返回字节数组。
	 */
	public static byte[] toByteArray(InputStream in) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			copyInToOut(in, out);
			byte[] bytes = out.toByteArray();
			in.close();
			out.close();
			return bytes;
		} catch (Exception e) {
			throw new UncheckedException("将文件转换成字节数组时发生异常", e);
		}
	}

	/**
	 * 将一个文件转化为字节数组
	 * 
	 * @param file
	 *            文件
	 * @return 返回字节数组。
	 */
	public static byte[] toByteArray(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			return toByteArray(in);
		} catch (FileNotFoundException e) {
			throw new UncheckedException("将文件转换成字节数组时发生异常", e);
		}
	}

	/**
	 * 将输入流复制到输出流。
	 * 
	 * @param input
	 *            输入流
	 * @param output
	 *            输出流
	 */
	public static void copyInToOut(InputStream input, OutputStream output) {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int n = 0;
		try {
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
		} catch (IOException e) {
			throw new UncheckedException("从输入流复制到输出流时发生异常", e);
		}
	}
}
