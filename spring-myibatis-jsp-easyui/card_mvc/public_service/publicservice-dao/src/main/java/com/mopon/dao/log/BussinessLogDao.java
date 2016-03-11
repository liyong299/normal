package com.mopon.dao.log;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.mopon.util.Page;
import com.mopon.entity.log.BusinessLog;


@Repository
public interface BussinessLogDao
{
	/**
	 *写入日志信息
	 * @param param
	 * @return list
	 */
	public int insert(BusinessLog record);
	
	/**
	 * 分页查询接入日志信息
	 * @param param
	 * @return list
	 */
	public List<BusinessLog> queryPageList(Page<BusinessLog> param);
	
	/**
	 * 查询单个日志详情信息
	 * @param param
	 * @return list
	 */
	public BusinessLog queryBussinessLogsById(String id);
}
