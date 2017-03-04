/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.longconnection
 * 文件名称：SocketChannelPool.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月2日 下午4:46:50
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能描述：客户端连接池
 * @文件名称：SocketChannelPool.java
 * @author ly
 */
public class SocketChannelPool {

	private int capacity;
	private static final int maxCapacity = Constants.CLIENT_NUM;
	private Map<String, SocketChannel> pool;
	// 增加的时候，使用重入锁控制一下。
	private ReentrantLock lock = new ReentrantLock();
	private static SocketChannelPool scPool = new SocketChannelPool();

	private SocketChannelPool() {
		this.capacity = maxCapacity;
		pool = new ConcurrentHashMap<>(capacity);
	}

	public static SocketChannelPool getInstance() {
		return scPool;
	}

	public Map<String, SocketChannel> getPool() {
		return pool;
	}
	/**
	 * 增加一个连接通道
	 * @param channel
	 * @param clientId
	 */
	public void add(String clientId, SocketChannel channel) {
		if (pool.size() < capacity) {
			try{
				lock.lock();
				if (pool.get(clientId) == null) {
					pool.put(clientId, channel);
				}
			} finally {
				if (lock.isLocked())
					lock.unlock();
			}
		} else {
			throw new RuntimeException("连接池太大暂时不能增加新的请求");
		}
	}

	/**
	 * 增加一个连接通道
	 * @param channel
	 * @param clientId
	 */
	public void remove(String clientId) {
		pool.remove(clientId);
	}

	/**
	 * 增加一个连接通道
	 * @param channel
	 * @param clientId
	 */
	public void remove(SocketChannel socketChannel) {
		for (Map.Entry<String, SocketChannel> entry : pool.entrySet()) {
			if (entry.getValue() == socketChannel) {
				pool.remove(entry.getKey());
			}
		}
	}

	/**
	 * 获取当前客户端的连接通道
	 * @param clientId
	 * @return
	 */
	public SocketChannel get(String clientId) {
		return pool.get(clientId);
	}
}
