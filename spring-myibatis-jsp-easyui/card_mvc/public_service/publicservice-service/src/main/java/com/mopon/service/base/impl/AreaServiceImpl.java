package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.AreaDao;
import com.mopon.entity.Area;
import com.mopon.service.base.AreaService;
import com.mopon.util.Page;

@Service
public class AreaServiceImpl implements AreaService{
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> queryListByPage(Page<Area> page) {
		return areaDao.queryListByPage(page);
	}

	@Override
	public List<Area> queryAllByEntity(Area area) {
		return areaDao.queryAllByEntity(area);
	}

	@Override
	public Area queryAreaById(String id) {
		return areaDao.queryAreaById(id);
	}

	@Override
	public void insertAreaInfo(Area area) {
		areaDao.insertAreaInfo(area);
	}

	@Override
	public void updateAreaInfo(Area area) {
		areaDao.updateAreaInfo(area);
	}

	@Override
	public void deleteAreaById(String id) {
		areaDao.deleteAreaById(id);
	}

	@Override
	public String queryAreaNameByNo(String areaNo) {
		return areaDao.queryAreaNameByNo(areaNo);
	}

}
