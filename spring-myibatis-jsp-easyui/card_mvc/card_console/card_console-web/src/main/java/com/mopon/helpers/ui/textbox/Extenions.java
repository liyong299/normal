package com.mopon.helpers.ui.textbox;

import java.util.HashMap;
import java.util.Map;

import org.topteam.ui.easyui.*;

/**
 * Created by 陈誉 on 2015-04-19.
 */
public class Extenions extends TextBoxTag {
	private String width;
	private String height;

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("prompt", getPrompt());
        options.put("multiline", getMultiline());
        options.put("editable", getEditable());
        options.put("disabled", getDisabled());
        options.put("readonly", getReadonly());
        options.put("icons", getIcons());
        options.put("iconCls", getIconCls());
        options.put("iconAlign", getIconAlign());
        options.put("iconWidth", getIconWidth());
        options.put("buttonText", getButtonText());
        options.put("buttonIcon", getButtonIcon());
        options.put("buttonAlign", getButtonAlign());
        options.put("required", getRequired());
        options.put("validType",getValidType());
        options.put("width",getWidth());
        options.put("height",getHeight());
        return options;
    }
    
    public String getWidth() {
    	if(this.width == null){
    		return "193px";
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