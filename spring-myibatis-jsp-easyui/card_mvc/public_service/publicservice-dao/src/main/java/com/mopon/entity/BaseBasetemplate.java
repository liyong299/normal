package com.mopon.entity;

import java.util.Date;

public class BaseBasetemplate {
    private Integer templateid;
    
    private String templatekey;

    private String templatename;

    private Integer templatetype;
    
    private String templatetypename;

    private String templatecontent;

    private String templatecontent1;

    private Integer status;

    private String createtime;

    private Integer templateclass;
    
    private String templateclassname;

    private Integer templatedefine;

    public Integer getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename == null ? null : templatename.trim();
    }

    public Integer getTemplatetype() {
        return templatetype;
    }

    public void setTemplatetype(Integer templatetype) {
        this.templatetype = templatetype;
    }

    public String getTemplatecontent() {
        return templatecontent;
    }

    public void setTemplatecontent(String templatecontent) {
        this.templatecontent = templatecontent == null ? null : templatecontent.trim();
    }

    public String getTemplatecontent1() {
        return templatecontent1;
    }

    public void setTemplatecontent1(String templatecontent1) {
        this.templatecontent1 = templatecontent1 == null ? null : templatecontent1.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getTemplateclass() {
        return templateclass;
    }

    public void setTemplateclass(Integer templateclass) {
        this.templateclass = templateclass;
    }

    public Integer getTemplatedefine() {
        return templatedefine;
    }

    public void setTemplatedefine(Integer templatedefine) {
        this.templatedefine = templatedefine;
    }

	public String getTemplatetypename() {
		return templatetypename;
	}

	public void setTemplatetypename(String templatetypename) {
		this.templatetypename = templatetypename;
	}

	public String getTemplateclassname() {
		return templateclassname;
	}

	public void setTemplateclassname(String templateclassname) {
		this.templateclassname = templateclassname;
	}

	public String getTemplatekey() {
		return templatekey;
	}

	public void setTemplatekey(String templatekey) {
		this.templatekey = templatekey;
	}
}