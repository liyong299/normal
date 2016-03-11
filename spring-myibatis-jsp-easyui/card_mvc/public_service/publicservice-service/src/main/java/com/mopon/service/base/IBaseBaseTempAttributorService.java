package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseBaseTemplAttribute;

public interface IBaseBaseTempAttributorService {
	int insert(BaseBaseTemplAttribute record);

    int insertSelective(BaseBaseTemplAttribute record);
    /**
     * 查询模板类型列表
     * @param record
     * @return
     */
    List<BaseBaseTemplAttribute> queryTemplateTypeList(BaseBaseTemplAttribute record);
    /**
     * 查询业务类型列表
     * @param record
     * @return
     */
    List<BaseBaseTemplAttribute> queryTemplateClassList(BaseBaseTemplAttribute record);
    /**
     * 查询模板选择项列表
     * @param record
     * @return
     */
    BaseBaseTemplAttribute queryTemplAttrList(BaseBaseTemplAttribute record);
}
