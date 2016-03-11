package com.mopon.service.base;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.mopon.entity.Area;
import com.mopon.util.Page;

public interface AreaService {

	/**
	 * 分页查询区域列表
	 * @param page
	 * @return
	 */
	public List<Area> queryListByPage(Page<Area> page);
	
	/**
	 * 根据区域实体查询区域列表
	 * @param thirdCardInfo
	 * @return
	 */
	public List<Area> queryAllByEntity(Area area);
	
	/**
	 * 根据区域id查询区域信息
	 * @param id
	 * @return
	 */
	public Area queryAreaById(String id);
	
	/**
	 * 插入区域信息
	 * @param thirdCardInfo
	 */
	public void insertAreaInfo(Area thirdCardInfo);
	
	/**
	 * 跟新区域信息
	 * @param thirdCardInfo
	 */
	public void updateAreaInfo(Area thirdCardInfo);
	
	/**
	 * 根据id删除对应的区域
	 * @param id
	 */
	public void deleteAreaById(@RequestParam("areaId")String id);
	
	/**
	 * 根据areaNo获取areaName
	 * @param areaNo
	 * @return
	 */
	public String queryAreaNameByNo(String areaNo);
}
