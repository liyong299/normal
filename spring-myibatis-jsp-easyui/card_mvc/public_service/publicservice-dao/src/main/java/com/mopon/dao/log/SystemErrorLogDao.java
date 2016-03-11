package com.mopon.dao.log;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.mopon.util.Page;
import com.mopon.entity.log.SysExecptionLog;


@Repository
public interface SystemErrorLogDao
{
	/**
	 *写入日志信息
	 * @param param
	 * @return list
	 */
	public int insert(SysExecptionLog record);
	
	/**
	 * 分页查询接入日志信息
	 * @param param
	 * @return list
	 */
	public List<SysExecptionLog> queryPageList(Page<SysExecptionLog> param);
	
	/**
	 * 查询单个日志详情信息
	 * @param param
	 * @return list
	 */
	public SysExecptionLog querySystemErrorLogsById(String id);
}
