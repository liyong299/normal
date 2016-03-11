package com.mopon.helpers.ui.searchCondition;

import javax.servlet.jsp.JspWriter;

import org.topteam.ui.easyui.TagRender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 陈誉 on 2015-04-21.
 */
public class Extenions extends TagRender {

	private String id;
	
    private String region;

    private String classStyle;
    private String style;

    private String title;
    private Boolean border;
    private Boolean split;
    private String iconCls;
    private String href;
    private Boolean collapsible ;
    private int minWidth = -1;
    private int minHeight = -1;
    private int maxWidth = -1;
    private int maxHeight = -1;

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
        options.put("region", region);
        options.put("title", title);
        options.put("border", border);
        options.put("split", split);
        options.put("iconCls", iconCls);
        options.put("href", href);
        options.put("collapsible", collapsible);
        options.put("minWidth", minWidth);
        options.put("minHeight", minHeight);
        options.put("maxWidth", maxWidth);
        options.put("maxHeight", maxHeight);
        return options;
    }

    @Override
    public void renderStart(JspWriter out) {
        try {
            out.write("<div");
            //if (classStyle != null) {
            //    out.write(" class=\"" + classStyle + "\"");
            //}
            //if (style != null) {
            //    out.write(" style=\"" + style + "\"");
            //}
            out.write(" data-options=\"region:'north',border:false\">");
            out.write("<form id=\"" + id + "\" class=\"search_box\" method=\"get\" action=\"demo/getlist.do\">");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderEnd(JspWriter out) {
        try {
            out.write("</form></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
	public String getId(){
    	return id;
    }
    
    @Override
	public void setId(String id){
    	this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getClassStyle() {
        return classStyle;
    }

    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isBorder() {
        return border;
    }

    public void setBorder(Boolean border) {
        this.border = border;
    }

    public Boolean isSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(Boolean collapsible) {
        this.collapsible = collapsible;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
}
