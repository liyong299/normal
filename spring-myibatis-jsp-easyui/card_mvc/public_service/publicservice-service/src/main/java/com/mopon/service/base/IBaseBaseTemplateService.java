package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseBaseTempKeyWord;
import com.mopon.entity.BaseBasetemplate;
import com.mopon.util.Page;

public interface IBaseBaseTemplateService {
	
	 	int deleteByPrimaryKey(Integer templateid);

	    int insert(BaseBasetemplate record);

	    int insertSelective(BaseBasetemplate record);

	    BaseBasetemplate selectByPrimaryKey(BaseBasetemplate record);

	    int updateByPrimaryKeySelective(BaseBasetemplate record);

	    int updateByPrimaryKey(BaseBasetemplate record);
	    
	    List<BaseBasetemplate> queryListByPage(Page<BaseBasetemplate> page);
	    /**
	     * 获取下发短信
	     * @return
	     */
	    BaseBasetemplate getSendVoucherSMS(String templKey,BaseBaseTempKeyWord record);
	    
	    
}
