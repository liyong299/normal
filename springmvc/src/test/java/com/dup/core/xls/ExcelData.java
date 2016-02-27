package com.dup.core.xls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelData {
	private String templateFileName;
	private List<?> sheetModels;
	private List<String> sheetNames = new ArrayList<String>();
	private String sheetNameField = "name";
	private String sheetModelName;
	private Map<String, ?> otherModel;
	private Integer startSheetNum = 0;

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public List<?> getSheetModels() {
		return sheetModels;
	}

	public void setSheetModels(List<?> sheetModels) {
		this.sheetModels = sheetModels;
	}

	public List<String> getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(List<String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String getSheetNameField() {
		return sheetNameField;
	}

	public void setSheetNameField(String sheetNameField) {
		this.sheetNameField = sheetNameField;
	}

	public String getSheetModelName() {
		return sheetModelName;
	}

	public void setSheetModelName(String sheetModelName) {
		this.sheetModelName = sheetModelName;
	}

	public Map<String, ?> getOtherModel() {
		return otherModel;
	}

	public void setOtherModel(Map<String, ?> otherModel) {
		this.otherModel = otherModel;
	}

	public Integer getStartSheetNum() {
		return startSheetNum;
	}

	public void setStartSheetNum(Integer startSheetNum) {
		this.startSheetNum = startSheetNum;
	}
}
