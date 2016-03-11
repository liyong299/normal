package com.mopon.dao.log;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.mopon.util.Page;
import com.mopon.entity.log.AccessApiLog;



@Repository
public interface AccessInLogDao
{
	/**
	 *写入日志信息
	 * @param param
	 * @return list
	 */
	public int insert(AccessApiLog record);
	
	/**
	 * 分页查询接入日志信息
	 * @param param
	 * @return list
	 */
	public List<AccessApiLog> queryPageList(Page<AccessApiLog> param);
	
	/**
	 * 查询单个日志详情信息
	 * @param param
	 * @return list
	 */
	public AccessApiLog queryAccessInLogsById(String id);
}
