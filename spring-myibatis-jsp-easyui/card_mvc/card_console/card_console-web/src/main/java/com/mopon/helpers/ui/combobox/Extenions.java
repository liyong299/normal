package com.mopon.helpers.ui.combobox;

import java.util.Map;

import org.topteam.ui.easyui.*;

/**
 * Created by 陈誉 on 2015-04-21.
 */
public class Extenions extends ComboBoxTag {
	private String width;
	private String height;
	
	@Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("valueField", getValueField());
        options.put("textField", getTextField());
        options.put("groupField", getGroupField());
        options.put("mode", getMode());
        options.put("url", getUrl());
        options.put("method", getMethod());
        options.put("data", getData());
        options.put("value",getValue());
        options.put("required",getRequired());
        options.put("queryParams", getQueryParams());
        options.put("width",getWidth());
        options.put("height",getHeight());
        return options;
    }
	
	public String getWidth() {
    	if(this.width == null){
    		return Width.NORMAL;
    	}
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}