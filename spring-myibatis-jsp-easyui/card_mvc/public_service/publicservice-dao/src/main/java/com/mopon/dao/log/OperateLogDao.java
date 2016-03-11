package com.mopon.dao.log;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.mopon.util.Page;
import com.mopon.entity.log.OperaterLog;


@Repository
public interface OperateLogDao
{
	/**
	 *写入日志信息
	 * @param param
	 * @return list
	 */
	public int insert(OperaterLog record);
	
	/**
	 * 分页查询接入日志信息
	 * @param param
	 * @return list
	 */
	public List<OperaterLog> queryPageList(Page<OperaterLog> param);
	
	/**
	 * 查询单个日志详情信息
	 * @param param
	 * @return list
	 */
	public OperaterLog queryOperateLogsById(String id);
}
