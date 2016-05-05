package com.ly.java.javanio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;

public class NIOScatteringandGathering {
    /**
     * <p>功能描述：<p>通过Apache的文件操作方法，创建和读取文件</p></p>
     * <p>实现逻辑：<p>实现步骤</p></p>
     * @param TPATH
     */
    public void createFiles(String TPATH) {
	try {
	    ByteBuffer bookBuf = ByteBuffer.wrap("java 性能优化技巧".getBytes("utf-8"));
	    ByteBuffer autBuf = ByteBuffer.wrap("test".getBytes("utf-8"));
	    int booklen = bookBuf.limit();
	    int autlen = autBuf.limit();
	    ByteBuffer[] bufs = new ByteBuffer[] { bookBuf, autBuf };
	    File file = new File(TPATH);
	    if (!file.exists()) {
		try {
		    file.createNewFile();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    try {
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		fc.write(bufs);
		fos.close();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    ByteBuffer b1 = ByteBuffer.allocate(booklen);
	    ByteBuffer b2 = ByteBuffer.allocate(autlen);
	    ByteBuffer[] bufs1 = new ByteBuffer[] { b1, b2 };
	    File file1 = new File(TPATH);
	    try {
		FileInputStream fis = new FileInputStream(file1);
		FileChannel fc = fis.getChannel();
		fc.read(bufs1);
		String bookname = new String(bufs1[0].array(), "utf-8");
		String autname = new String(bufs1[1].array(), "utf-8");
//		System.out.println(bookname + " " + autname);
		fc.close();
		fis.close();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}

    }

    public static void main(String[] args) {
	NIOScatteringandGathering nio = new NIOScatteringandGathering();
	long startTime = System.currentTimeMillis();
	for (int i = 0; i < 20000; i++)
	{
//	    nio.createFiles("1.TXT");
	    nio.testFileUtils("1.TXT");
	}
	System.out.println(System.currentTimeMillis() - startTime);
	
	long startTime2 = System.currentTimeMillis();
	for (int i = 0; i < 20000; i++)
	{
	    nio.createFiles("1.TXT");
//	    nio.testFileUtils("1.TXT");
	}
	System.out.println(System.currentTimeMillis() - startTime2);
    }
    
    /**
     * <p>功能描述：<p>通过Apache的文件操作方法，创建和读取文件</p></p>
     * <p>实现逻辑：<p>实现步骤</p></p>
     * @param path
     */
    public void testFileUtils(String path)
    {
	try {
	    FileUtils.write(new File(path), "java 性能优化技巧test");
	    FileUtils.readFileToString(new File(path));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}