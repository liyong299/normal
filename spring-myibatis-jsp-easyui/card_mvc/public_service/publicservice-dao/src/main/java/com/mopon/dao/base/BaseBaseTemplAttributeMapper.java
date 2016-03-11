package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseBaseTemplAttribute;
@Repository
public interface BaseBaseTemplAttributeMapper {
    int insert(BaseBaseTemplAttribute record);

    int insertSelective(BaseBaseTemplAttribute record);
    
    List<BaseBaseTemplAttribute> queryTemplateTypeList(BaseBaseTemplAttribute record);
    
    List<BaseBaseTemplAttribute> queryTemplateClassList(BaseBaseTemplAttribute record);
    
    BaseBaseTemplAttribute queryTemplAttrList(BaseBaseTemplAttribute record);
}