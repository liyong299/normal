package com.mopon.entity.base;

public class KeyValuePair {
	private String text = "";
	private String value = "";
	private Boolean selected = false;
	
	public KeyValuePair(){}
	public KeyValuePair(String text, String value){
		this.text = text;
		this.value = value;
	}
	
	public KeyValuePair(String text, String value, Boolean selected){
		this.text = text;
		this.value = value;
		this.selected = selected;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}