package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseBaseTemplAttributeMapper;
import com.mopon.entity.BaseBaseTemplAttribute;
import com.mopon.service.base.IBaseBaseTempAttributorService;
@Service
public class BaseBaseTempAttributorImpl implements
		IBaseBaseTempAttributorService {

	@Autowired
	BaseBaseTemplAttributeMapper tempAttrMapper;
	
	@Override
	public int insert(BaseBaseTemplAttribute record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BaseBaseTemplAttribute record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BaseBaseTemplAttribute> queryTemplateTypeList(
			BaseBaseTemplAttribute record) {
		// TODO Auto-generated method stub
		return tempAttrMapper.queryTemplateTypeList(record);
	}

	@Override
	public List<BaseBaseTemplAttribute> queryTemplateClassList(
			BaseBaseTemplAttribute record) {
		// TODO Auto-generated method stub
		return tempAttrMapper.queryTemplateClassList(record);
	}

	@Override
	public BaseBaseTemplAttribute queryTemplAttrList(
			BaseBaseTemplAttribute record) {
		// TODO Auto-generated method stub
		return tempAttrMapper.queryTemplAttrList(record);
	}

}
