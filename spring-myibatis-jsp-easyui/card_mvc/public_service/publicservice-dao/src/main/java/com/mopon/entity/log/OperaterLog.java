package com.mopon.entity.log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.mopon.util.ClassReflectionUtils;
import com.thoughtworks.xstream.mapper.Mapper.Null;

/**
 *  操作日志
 * @author liuxi
 *
 */
public class OperaterLog extends DateShow{
	
	private Long logID;
	
	private Long sysLogID;
	//菜单ID
	private Integer menuID;
	//菜单名称
	private String menuName;
	//按钮名称
	private String btnName;
	//用户ID
	private String userID;
	//用户名称
	private String userName;
	//操作描述
	private String memo;
	//数据变更情况
	private String dataChangeInfos;
	//日志状态
	private String logStatus;
	//记录时间
	private Long recTime;

	public Long getLogID() {
		return logID;
	}

	public Long getSysLogID() {
		return sysLogID;
	}

	public Integer getMenuID() {
		return menuID;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getBtnName() {
		return btnName;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getMemo() {
		return memo;
	}

	public String getDataChangeInfos() {
		return dataChangeInfos;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public Long getRecTime() {
		return recTime;
	}

	public void setLogID(Long logID) {
		this.logID = logID;
	}

	public void setSysLogID(Long sysLogID) {
		this.sysLogID = sysLogID;
	}

	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setDataChangeInfos(String dataChangeInfos) {
		this.dataChangeInfos = dataChangeInfos;
	}
	
	public void setDataChangeInfos(Object oldClass,Object newClass){
		this.dataChangeInfos = getDataChangeInfos(oldClass, newClass);
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public void setRecTime(Long recTime) {
		this.recTime = recTime;
	}
	
	private String getDataChangeInfos(Object oldClass,Object newClass){
		List<DataChangeInfoEntity> list = new ArrayList<DataChangeInfoEntity>();
		DataChangeInfoEntity dataChgEntity=null;
		Field[] fields = oldClass.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Object oldVal = ClassReflectionUtils.getFieldValueByName(fields[i].getName(), oldClass);
			Object newVal = ClassReflectionUtils.getFieldValueByName(fields[i].getName(), newClass);
			dataChgEntity = new DataChangeInfoEntity();
			dataChgEntity.setColumnname(fields[i].getName());
			if(oldVal !=null){
				dataChgEntity.setOldvalue(oldVal.toString());
			}
			
			if(newVal == null && oldVal !=null){
				dataChgEntity.setNewvalue(oldVal.toString());
			}else if(newVal != null){
				dataChgEntity.setNewvalue(newVal.toString());
			}
			
			
			if(!oldVal.equals(newVal)){
				dataChgEntity.setChangeflag(true);
			}
			list.add(dataChgEntity);
		}
		
		return JSONSerializer.toJSON(list).toString();
	}

}
