/**
 * 项目名称：java
 * 文件包名：com.ly.java.thrift.netty3
 * 文件名称：package-info.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月9日 上午10:20:01
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
/**
 * @功能描述：netty实现HTTP 服务端.
 * 理论介绍：每一个客户端连接到server上会被分配到对应的Handler上处理数据。Netty的设计中把Request分为了HttpRequest和HttpContent两个部分。
 * 而由于担心HttpContent内容过长(例如上传文件这种场景)，HttpContent又被分成了普通的HttpContent和LastHttpContent两个部分，这些消息的处理放到Handler中。
 * @文件名称：package-info.java
 * @author ly
 */
package com.ly.java.thrift.netty5;