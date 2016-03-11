package com.mopon.service.base.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseBasetemplateMapper;
import com.mopon.entity.BaseBaseTempKeyWord;
import com.mopon.entity.BaseBasetemplate;
import com.mopon.service.base.IBaseBaseTemplateService;
import com.mopon.util.Page;
@Service
public class BaseBaseTemplateImpl implements IBaseBaseTemplateService {

	@Autowired
	public BaseBasetemplateMapper baseTempMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer templateid) {
		// TODO Auto-generated method stub
		return baseTempMapper.deleteByPrimaryKey(templateid);
	}

	@Override
	public int insert(BaseBasetemplate record) {
		// TODO Auto-generated method stub
		return baseTempMapper.insert(record);
	}

	@Override
	public int insertSelective(BaseBasetemplate record) {
		// TODO Auto-generated method stub
		return baseTempMapper.insertSelective(record);
	}

	@Override
	public BaseBasetemplate selectByPrimaryKey(BaseBasetemplate record) {
		// TODO Auto-generated method stub
		return baseTempMapper.selectByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseBasetemplate record) {
		// TODO Auto-generated method stub
		return baseTempMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BaseBasetemplate record) {
		// TODO Auto-generated method stub
		return baseTempMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BaseBasetemplate> queryListByPage(Page<BaseBasetemplate> page) {
		// TODO Auto-generated method stub
		return baseTempMapper.queryListByPage(page);
	}
	
	/**
	 * 获取下发短信内容
	 */
	@Override
	public BaseBasetemplate getSendVoucherSMS(String templKey,
			BaseBaseTempKeyWord record) {
		BaseBasetemplate result = new BaseBasetemplate(); 
		String msg1="";
		String msg2="";
		try {
			BaseBasetemplate templ = new BaseBasetemplate();
			templ.setTemplatekey(templKey);
			BaseBasetemplate baseBasetemplate = baseTempMapper.selectByPrimaryKey(templ);
			if(baseBasetemplate !=null){
				msg1 = baseBasetemplate.getTemplatecontent();
				msg2 = baseBasetemplate.getTemplatecontent1();
				Field[] fields = record.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Object val=this.getFieldValueByName(fields[i].getName(), record) ;
					if(val !=null){						
						msg1 = msg1.replace("("+fields[i].getName()+")",val.toString());
						msg2 = msg2.replace("("+fields[i].getName()+")",val.toString());
					}else {
						msg1 = msg1.replace("("+fields[i].getName()+")","");
						msg2 = msg2.replace("("+fields[i].getName()+")","");
					}
					
				}
				result.setTemplatecontent(msg1);
				result.setTemplatecontent1(msg2);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		return result;
	}
	
    /** 
     * 根据属性名获取属性值 
     * */  
       private Object getFieldValueByName(String fieldName, Object o) {  
           try {    
               String firstLetter = fieldName.substring(0, 1).toUpperCase();    
               String getter = "get" + firstLetter + fieldName.substring(1);    
               Method method = o.getClass().getMethod(getter, new Class[] {});    
               Object value = method.invoke(o, new Object[] {});    
               return value;    
           } catch (Exception e) {    
               
               return null;    
           }    
       }   
         
       /** 
        * 获取属性名数组 
        * */  
       private String[] getFiledName(Object o){  
        Field[] fields=o.getClass().getDeclaredFields();  
            String[] fieldNames=new String[fields.length];  
        for(int i=0;i<fields.length;i++){  
            System.out.println(fields[i].getType());  
            fieldNames[i]=fields[i].getName();  
        }  
        return fieldNames;  
       }  
         
       /** 
        * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list 
        * */  
       private List getFiledsInfo(Object o){  
        Field[] fields=o.getClass().getDeclaredFields();  
            String[] fieldNames=new String[fields.length];  
            List list = new ArrayList();  
            Map infoMap=null;  
        for(int i=0;i<fields.length;i++){  
            infoMap = new HashMap();  
            infoMap.put("type", fields[i].getType().toString());  
            infoMap.put("name", fields[i].getName());  
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));  
            list.add(infoMap);  
        }  
        return list;  
       }  
         
       /** 
        * 获取对象的所有属性值，返回一个对象数组 
        * */  
       public Object[] getFiledValues(Object o){  
        String[] fieldNames=this.getFiledName(o);  
        Object[] value=new Object[fieldNames.length];  
        for(int i=0;i<fieldNames.length;i++){  
            value[i]=this.getFieldValueByName(fieldNames[i], o);  
        }  
        return value;  
       }      
}
