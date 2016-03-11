package com.mopon.helpers.ui.formatterColumn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.topteam.ui.easyui.ColumnTag;
import org.topteam.ui.easyui.ColumnsTag;
import org.topteam.ui.easyui.ComboGridTag;
import org.topteam.ui.model.Column;
import org.topteam.ui.model.JsFunction;

/**
 * Created by 陈誉 on 2015-05-05.
 */
public class Extenions extends ColumnTag {
	//参数
	private String param;
	private String formatter;
	private List<String> handlers = new ArrayList<String>();
	
	@Override
    public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		
		if (handlers != null && handlers.size() != 0) {
			if(getField() == null || getField() == ""){
				setField("_handler");
			}
			if(getAlign() == null || getAlign() == ""){
				setAlign("center");
			}
			
	        
	        options.put("field", getField());
	        options.put("align", getAlign());
	        options.put("halign", getHalign());
	        options.put("sortable", getSortable());
	        options.put("order", getOrder());
	        options.put("resizable", getResizable());
	        options.put("fixed", getFixed());
	        options.put("hidden", getHidden());
	        options.put("checkbox", getCheckbox());
	        
	            options.put("formatter", new JsFunction("function(" + getParam() + "){var html='';" + getFormatter() + " return html;}"));
	        
	        options.put("styler", getStyler());
	        options.put("sorter", getSorter());
	        options.put("width", getWidth());
		}
        return options;
    }

	@Override
    public void renderStart(JspWriter out) {
		
    }

	@Override
    public void renderEnd(JspWriter out) {
		try {
			if (handlers != null && handlers.size() != 0) {
	            Tag parent = getParent();
	            if(parent.getParent() instanceof ComboGridTag){
	                ColumnsTag columnsTag = (ColumnsTag) parent;
	                columnsTag.getColumns().add(new Column(this));
	            }else{
	                out.write("<th");
	                if (getRowspan() >= 0) {
	                    out.write(" rowspan='" + getRowspan() + "'");
	                }
	                if (getColspan() >= 0) {
	                    out.write(" colspan='" + getColspan() + "'");
	                }
	                out.write(" data-options=\"");
	                out.write(optionsToString());
	                out.write("\"");
	                out.write(">");
	                if (getTitle() != null) {
	                    out.write(getTitle());
	                }
	                if (bodyContent != null && bodyContent.getString() != null) {
	                    out.write(bodyContent.getString());
	                }
	                out.write("</th>");
	            }
	            this.getHandlers().clear();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public List<String> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<String> handlers) {
		this.handlers = handlers;
	}
	
	@Override
	public String getFormatter() {
		//if(formatter == null || formatter == ""){
			StringBuffer str = new StringBuffer();
			for(String handler: handlers){
				str.append(handler);
			}
			formatter = str.toString();
		//}
        return formatter;
    }
}