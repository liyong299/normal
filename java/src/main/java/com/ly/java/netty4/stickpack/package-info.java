/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.stickpack
 * 文件名称：package-info.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年2月28日 上午10:37:13
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
/**
 * @功能描述：栈包问题的重现和解决  粘包的产生是因为Channel初始化的时候，没有指定DelimiterBasedFrameDecoder解码器
 * LineBaseFrameDecoder的工作原理是它依次遍历ByteBuf中的可读字节，判断看是否有”\n”或者”\r\n”,如果有，就以此位置为结束位置，从可读索引到结束位置区间的字节组成一行。
 * 它是以换行符为结束标志的解码器，支持携带结束符或者不携带结束符两种解码方式，同时支持配置单行的最大长度。如果连续读取到最大长度后仍然没有换行符，就会抛出异常，同时忽略掉之前读到到异常码流。
 * StringDecoder的功能更简单，将接收到的对象转换成字符串，然后继续调用后面的handler，LineBaseFrameDecoder+StringDecoder组合就是按行切换的文本解码器，它被设计用来支持TCP的粘包和拆包。
 * @文件名称：package-info.java
 * @author ly
 */
package com.ly.java.netty4.stickpack;