package com.mopon.helpers.ui.handlerColumn;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.topteam.ui.easyui.TagRender;

import com.mopon.interceptor.ApplicationContext;
import com.mopon.service.role.RoleService;
import com.mopon.util.SpringContextUtil;
/**
 * Created by 陈誉 on 2015-05-05.
 */
public class Extenions extends TagRender{
	private String text;
	private String onclick;
	private String param;
	private String condition;

	@Override
    public String getEasyuiTag() {
        return null;
    }

    @Override
    public int renderBody() {
        return EVAL_BODY_BUFFERED;
    }
    
	@Override
    public void renderStart(JspWriter out) {
		//权限判断
		String controller = ApplicationContext.controller();
		String action = ApplicationContext.action();
		Integer userID = Integer.parseInt(ApplicationContext.cookie("userID"));
		Integer roleID = Integer.parseInt(ApplicationContext.cookie("roleID"));
		
		Boolean flag = false;
		
		if(userID == 1){
			flag = true;
		}
		else if(userID != 0 && roleID != 0){
			RoleService roleService = (RoleService)SpringContextUtil.getBean("roleServiceImpl");
			flag = roleService.queryRoleOperatorAuthority(roleID, this.getText(), controller, action);
		}
		
		if(flag){
			StringBuffer handler = new StringBuffer();
			if(!(getCondition() == null) && !(getCondition() == "")){
				handler.append("if(" + getCondition() + "){");
			}
			handler.append("html += '<a class=\\\'g-mlr-5\\\'");
			handler.append(" onclick=\\'");
			handler.append(getOnclick() + "(");
			if(!(getParam() == null) && !(getParam() == "")){
				String[] params  = null;
				params  = getParam().split(",");
				
				if(params != null && params.length > 0){
					for(Integer i = 0; i < params.length; i ++ ){
						params[i] = params[i].trim();
						
						handler.append("' + ");
						if(params[i].contains("'")){
							params[i] = params[i].replace("'", "");
							handler.append("'&quot;' + " + params[i] + " + '&quot;'");
						}
						else{
							handler.append(params[i]);
						}
						handler.append(" + '");
						
						if(i == params.length - 1){
						}
						else{
							handler.append(",");
						}
					}
				}
				else{
					handler.append("' + " + getParam() + " + '");
				}
			}
			handler.append(")\\'");
			handler.append(" >");
			handler.append(getText());
			handler.append("</a>';");
			if(!(getCondition() == null) && !(getCondition() == "")){
				handler.append("}");
			}
			Tag parent = getParent();
			if(parent instanceof com.mopon.helpers.ui.formatterColumn.Extenions){
				 	com.mopon.helpers.ui.formatterColumn.Extenions column = (com.mopon.helpers.ui.formatterColumn.Extenions) parent;
				 	column.getHandlers().add(handler.toString());
	        }
		}
    }

	@Override
    public void renderEnd(JspWriter out) {
    }
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}