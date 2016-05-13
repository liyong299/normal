package com.ly.java.javanio.c05selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ClientWorker implements Runnable {

    private Selector selector;
    private Charset charset;
    public ClientWorker(Selector selector, Charset charset)
    {
	this.selector = selector;
	this.charset = charset;
    }
    public void run() {
	try
	{
	    while(true)
	    {
		int readyChannels = selector.select();
		if (readyChannels == 0) continue;
		Set<SelectionKey> set = selector.selectedKeys();
		Iterator<SelectionKey> iterator = set.iterator();
		while (iterator.hasNext())
		{
		    SelectionKey selectionKey = iterator.next();
		    iterator.remove();
		    handlerKey(selectionKey);
		}
	    }
	}
	catch(IOException ex)
	{
	    ex.printStackTrace();
	}
    }
    private void handlerKey(SelectionKey selectionKey) throws IOException {
	if (selectionKey.isReadable())
	{
	    // ????NIO????Channel????????????????????????selectionKey??????????????????SocketChannel
	    // ??????????????????????????
	    SocketChannel sc = (SocketChannel) selectionKey.channel();
	    ByteBuffer readBuffer = ByteBuffer.allocate(4096);
	    String content = "";
	    while (sc.read(readBuffer) > 0)
	    {
		readBuffer.flip();
		content = content + charset.decode(readBuffer);
	    }
	    System.out.println("?????????? " + content);
	    selectionKey.interestOps(SelectionKey.OP_READ);
	}
    }

}
