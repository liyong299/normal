package com.mopon.entity.log;

public class DataChangeInfoEntity {
	private String columnname;
	private String oldvalue;
	private String newvalue;
	private boolean changeflag =false;
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
	public String getOldvalue() {
		return oldvalue;
	}
	public void setOldvalue(String oldvalue) {
		this.oldvalue = oldvalue;
	}
	public String getNewvalue() {
		return newvalue;
	}
	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}
	public boolean isChangeflag() {
		return changeflag;
	}
	public void setChangeflag(boolean changeflag) {
		this.changeflag = changeflag;
	}
}
