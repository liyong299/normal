package com.mopon.helpers.ui.datagrid;

public class HandlerFormatter{
	private String value = "value";
	private String rowData = "rowData";
	private String index = "index";
	
	private StringBuilder html = new StringBuilder();

	public String getValue() {
		return value;
	}

	public String getRowData() {
		return rowData;
	}

	public String getIndex() {
		return index;
	}
	public StringBuilder getHtml() {
		return html;
	}

	public void setHtml(StringBuilder html) {
		this.html = html;
	}
	
	public HandlerFormatter Add(){
		return this;
	}
}