package com.mopon.dao.param;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mopon.dao.entity.ParamSys;
/**
 * 
 * <p>Description:系统参数表 </p>
 * @date 28 Apr 2015
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
@Repository
public interface BaseParamSysMapper {
    
	/**
	 * 
	  * purpose:根据名称查询行<br>
	  * step: <br>
	  * @param paramName
	  * @return
	  * int
	 */
	public ParamSys queryParamSysList(@Param("paramName")String paramName);
	
	
}