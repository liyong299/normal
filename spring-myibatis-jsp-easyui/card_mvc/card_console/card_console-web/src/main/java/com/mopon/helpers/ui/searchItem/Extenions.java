package com.mopon.helpers.ui.searchItem;

import javax.servlet.jsp.JspWriter;

import org.topteam.ui.easyui.TagRender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 陈誉 on 2015-04-21.
 */
public class Extenions extends TagRender {
	private String classStyle;
	private String name;

    @Override
    public String getEasyuiTag() {
        return null;
    }

    @Override
    public int renderBody() {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = new HashMap<String, Object>();
        return options;
    }

    @Override
    public void renderStart(JspWriter out) {
        try {
            out.write("<div class=\"" + getClassStyle() + "\"><label>" + name + "：</label>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderEnd(JspWriter out) {
        try {
            out.write("</div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getClassStyle(){
    	if(classStyle == null){
    		return "item";
    	}
    	return classStyle;
    }
    
    public void setClassStyle(String classStyle){
    	this.classStyle = classStyle;
    }
    
    public String getName(){
    	return classStyle;
    }
    
    public void setName(String name){
    	this.name = name;
    }
}