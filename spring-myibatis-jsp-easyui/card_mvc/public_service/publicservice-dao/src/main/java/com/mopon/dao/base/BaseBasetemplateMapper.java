package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseBasetemplate;
import com.mopon.util.Page;
@Repository
public interface BaseBasetemplateMapper {
    int deleteByPrimaryKey(Integer templateid);

    int insert(BaseBasetemplate record);

    int insertSelective(BaseBasetemplate record);

    BaseBasetemplate selectByPrimaryKey(BaseBasetemplate record);

    int updateByPrimaryKeySelective(BaseBasetemplate record);

    int updateByPrimaryKey(BaseBasetemplate record);
    
    List<BaseBasetemplate> queryListByPage(Page<BaseBasetemplate> page);
}