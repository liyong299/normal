package com.ly.java.file;

import java.io.File;
import java.io.IOException;

public class 空目录默认生成一个文件 {

	public static void main(String[] args) throws IOException {
		readAndgen("D:\\Work\\Workspace\\other\\dup");
	}
	
	private static void readAndgen(String dir) throws IOException {
		File file = new File(dir);
		readAndgen(file);
	}

	private static void readAndgen(File file) throws IOException {
		if (!file.exists())
			System.out.println("dir is not exist, path is : " + file.getAbsolutePath());
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 空目录则生成文件
			if (files.length < 1) {
				File newFile = new File(file.getAbsoluteFile() + System.getProperty("file.separator")
						+ "默认文件.txt");
				newFile.createNewFile();
				System.out.println("create file : " + newFile.getAbsolutePath());
			} else {
				for (File tmpFile : files) {
					readAndgen(tmpFile);
				}
			}
		}
	}
}
