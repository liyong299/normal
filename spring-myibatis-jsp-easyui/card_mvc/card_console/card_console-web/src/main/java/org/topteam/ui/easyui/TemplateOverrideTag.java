package org.topteam.ui.easyui;

import org.topteam.ui.model.RenderContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * Created by 枫 on 2014/8/17.
 */
public class TemplateOverrideTag extends TagRender {
    private String name;

    @Override
    public String getEasyuiTag() {
        return null;
    }

    @Override
    public int renderBody() {
        return isOverrided() ? SKIP_BODY : EVAL_BODY_BUFFERED;
    }

    @Override
    public void renderStart(JspWriter out) {

    }

    @Override
    public void renderEnd(JspWriter out) throws JspException {
        if (!isOverrided()) {
            BodyContent b = getBodyContent();
            String varName = RenderContext.JEASYUI_TAG_NAME + name;
            pageContext.setAttribute(varName, b.getString());
        }
    }

    private boolean isOverrided() {
        String varName = RenderContext.JEASYUI_TAG_NAME + name;
        return pageContext.getAttribute(varName) != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
