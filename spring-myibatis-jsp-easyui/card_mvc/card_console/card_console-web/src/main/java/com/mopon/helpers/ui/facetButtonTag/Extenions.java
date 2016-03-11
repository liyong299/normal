package com.mopon.helpers.ui.facetButtonTag;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import org.topteam.ui.easyui.ButtonTag;

import com.mopon.interceptor.ApplicationContext;
import com.mopon.service.role.RoleService;
import com.mopon.util.SpringContextUtil;

/**
 * Created by 陈誉 on 2015-05-04.
 */
public class Extenions extends ButtonTag {
	//@Autowired
	//private RoleService roleService;
	
	private Boolean flag = false;
	
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
			 try {
		            out.write("<a");
		            if (getId() != null) {
		                out.write(" id=\"" + getId() + "\"");
		            }
		            if (getClassStyle() != null) {
		                out.write(" class=\"" + getClassStyle() + "\"");
		            }
		            if (getStyle() != null) {
		                out.write(" style=\"" + getStyle() + "\"");
		            }
		            if (getOnclick() != null) {
		                out.write(" onclick=\"" + getOnclick() + "\"");
		            }
		            if (getHref() == null) {
		                out.write(" href='#'");
		            } else {
		                out.write(" href='" + getHref() + "' target='" + (getTarget() == null ? "_blank" : getTarget()) + "'");
		            }
		            out.write(" data-options=\"" + optionsToString());
		            out.write("\"");
		            out.write(">");
		            if (getText() != null) {
		                out.write(getText());
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
    }
	
	@Override
    public void renderEnd(JspWriter out) {
		if(flag){
			try {
	            out.write("</a>");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }
}