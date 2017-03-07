/**
 * 
 */
package com.dup.test.file.patch;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/**
 * @author hugoyang
 *
 */
public class PatchManger {

	private String srcBaseDir = "";
	private String clsBaseDir = "";
	private String destBaseDir = "E:/01_work/04_rmp/04_环境部署/正式环境版本2015-09-14/aa/";
	private static Map<String, String> SRC_CLS_PATH = new HashMap<String, String>();
	private static Map<String, String> SRC_CLS_FILETYPE = new HashMap<String, String>();
	
	static{
		SRC_CLS_PATH.put("/basic/trunk/rmp/tags/rmp v2.0/rmp.site/src/", "E:/01_work/04_rmp/code/rmp2/tags/rmp v2.0/rmp.site/target/classes/");
		SRC_CLS_PATH.put("/basic/trunk/rmp/tags/rmp v2.0/rmp.core/src/", "E:/01_work/04_rmp/code/rmp2/tags/rmp v2.0/rmp.core/target/classes/");
		
		SRC_CLS_FILETYPE.put("java", "class");
	}
	
	private void beforeDeal()
	{
		File file = new File(destBaseDir);
		if (file.exists() && file.delete())  // 如果存在，则删除并且重新创建。
		{
			file.mkdirs();
		}
	}

	/**
	 * 根据源文件的列表，获取编译后的文件，并生成制定文件夹，放入对应目录
	 * @param list
	 */
	public void getClassBySrcList(List<String> list)
	{
		if (list == null) return;
		beforeDeal();
		
		for (String srcPath : list)
		{
			if (srcPath.trim().length() < 1) continue;
			try {
				String clsPath = getClsPathBySrcPath(srcPath);
				File clsFile = new File(clsPath);
				if (clsFile.exists())
				{
					String destPath = clsPath.replace(clsBaseDir, destBaseDir);
					
					String destPathDir = getClsPathDirByClsPath(destPath);
					
					File parentDir = new File(destPathDir);
					if (!parentDir.exists()) parentDir.mkdirs();
					
					
					try {
						FileUtils.copyFile(clsFile, new File(destPath));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("编译后的文件不存在，路径：" + clsPath);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private String getClsPathDirByClsPath(String clsPath) {
		clsPath = clsPath.replaceAll("\\\\", "/");
//		System.out.println(clsPath);
		int lastIdx = clsPath.lastIndexOf('/');
		String clsPathDir = clsPath.substring(0, lastIdx);
		System.out.println(clsPathDir);
		return clsPathDir;
	}

	public String getClsPathBySrcPath(String srcPath) throws Exception
	{
		boolean flag = true;
		for (String pathKey : SRC_CLS_PATH.keySet())
		{
			if (srcPath.contains(pathKey))
			{
				srcBaseDir = pathKey;
				clsBaseDir = SRC_CLS_PATH.get(pathKey);
				flag = false;
				break;
			}
		}
		
		if (flag)
		{
			System.out.println("路径：【" + srcPath + "】无法处理");
			throw new Exception("路径：【" + srcPath + "】无法处理");
		}
		
		srcPath = srcPath.replace(srcBaseDir, "");  // 去掉源码路径中工程目录的部分

		 String[] fileName = srcPath.split("\\.");
		String clsPath = "";

		if (fileName[1] != null && SRC_CLS_FILETYPE.containsKey(fileName[1].toLowerCase()))	// java
		{
			String srcType = "." + fileName[1];
			String clsType = "." + SRC_CLS_FILETYPE.get(fileName[1].toLowerCase());
			clsPath = clsBaseDir + srcPath.replace(srcType, clsType);
			System.out.println(clsPath);
		}
		else
		{
			clsPath = clsBaseDir + srcPath;
		}
		return clsPath;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String classPath = PatchManger.class.getResource("").getPath();
		List<String> list = FileUtils.readLines(new File(classPath + "rmp_v2diff.txt"));
//		System.out.println(list);
		new PatchManger().getClassBySrcList(list);
		
		new PatchManger().getClsPathDirByClsPath("/basic/trunk/rmp/tags/rmp v2.0/rmp.site/src/cn/mopon/rmp/site/actions/index.ftl");
	}

}
