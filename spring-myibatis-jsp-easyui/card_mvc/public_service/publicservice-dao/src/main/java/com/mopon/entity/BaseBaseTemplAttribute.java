package com.mopon.entity;

public class BaseBaseTemplAttribute {
    private Integer templatetype;

    private String templatetypename;

    private Integer templateclass;

    private String templateclassname;

    private String tempselectattrs;

    private String tempseletedvalues;

    public Integer getTemplatetype() {
        return templatetype;
    }

    public void setTemplatetype(Integer templatetype) {
        this.templatetype = templatetype;
    }

    public String getTemplatetypename() {
        return templatetypename;
    }

    public void setTemplatetypename(String templatetypename) {
        this.templatetypename = templatetypename == null ? null : templatetypename.trim();
    }

    public Integer getTemplateclass() {
        return templateclass;
    }

    public void setTemplateclass(Integer templateclass) {
        this.templateclass = templateclass;
    }

    public String getTemplateclassname() {
        return templateclassname;
    }

    public void setTemplateclassname(String templateclassname) {
        this.templateclassname = templateclassname == null ? null : templateclassname.trim();
    }

    public String getTempselectattrs() {
        return tempselectattrs;
    }

    public void setTempselectattrs(String tempselectattrs) {
        this.tempselectattrs = tempselectattrs == null ? null : tempselectattrs.trim();
    }

    public String getTempseletedvalues() {
        return tempseletedvalues;
    }

    public void setTempseletedvalues(String tempseletedvalues) {
        this.tempseletedvalues = tempseletedvalues == null ? null : tempseletedvalues.trim();
    }
}