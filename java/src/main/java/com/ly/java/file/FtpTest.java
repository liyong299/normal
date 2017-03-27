package com.ly.java.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
  
public class FtpTest {
	public static void main(String[] args) throws Exception {
		//        testUpload();       
//        testDownload();       
		//		Runtime.getRuntime().exec(
		//				"E:/02_study/12_linux/PuTTY_0.67.0.0.exe -ssh -l root -pw lenovo -P 22 192.168.27.68");

		Runtime.getRuntime().exec(
				"E:/02_study/12_linux/pscp -C d:/img.jpg -pw lenovo root@192.168.27.68:/root/");
    }       
     
    /**     
     * FTP上传单个文件测试     
     */       
    public static void testUpload() {       
        FTPClient ftpClient = new FTPClient();       
        FileInputStream fis = null;       
     
		try {
			ftpClient.connect("192.168.27.68", 22);
			ftpClient.login("root", "lenovo");
			File srcFile = new File("D:/img.jpg");
			fis = new FileInputStream(srcFile);
            //设置上传目录       
			ftpClient.changeWorkingDirectory("/home/cec/");
            ftpClient.setBufferSize(1024);       
            ftpClient.setControlEncoding("UTF-8");       
            //设置文件类型（二进制）       
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);       
            ftpClient.storeFile("1234.png", fis);       
            System.out.println("成功！");      
        } catch (IOException e) {       
            e.printStackTrace();       
            throw new RuntimeException("FTP客户端出错！", e);       
        } finally {       
            IOUtils.closeQuietly(fis);       
            try {       
                ftpClient.disconnect();       
            } catch (IOException e) {       
                e.printStackTrace();       
                throw new RuntimeException("关闭FTP连接发生异常！", e);       
            }       
        }       
    }       
     
    /**     
     * FTP下载单个文件测试     
     */       
    public static void testDownload() {       
        FTPClient ftpClient = new FTPClient();       
        FileOutputStream fos = null;       
     
        try {       
            ftpClient.connect("192.168.14.117");       
            ftpClient.login("admin", "123");       
     
            String remoteFileName = "/admin/pic/3.gif";       
            fos = new FileOutputStream("e:/xx/down.gif");       
     
            ftpClient.setBufferSize(1024);       
            //设置文件类型（二进制）       
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);       
            ftpClient.retrieveFile(remoteFileName, fos);       
        } catch (IOException e) {       
            e.printStackTrace();       
            throw new RuntimeException("FTP客户端出错！", e);       
        } finally {       
            IOUtils.closeQuietly(fos);       
            try {       
                ftpClient.disconnect();       
            } catch (IOException e) {       
                e.printStackTrace();       
                throw new RuntimeException("关闭FTP连接发生异常！", e);       
            }       
        }       
    }      
}     