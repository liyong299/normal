package org.topteam.ui.easyui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.util.UUID;

/**
 * Created by 枫 on 2014/8/25.
 */
public class PushTag extends TagRender {
    @Override
    public String getEasyuiTag() {
        return null;
    }

    @Override
    public int renderBody() {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public void renderStart(JspWriter out) {
        setId(UUID.randomUUID().toString());
    }

    @Override
    public void renderEnd(JspWriter out) throws JspException {

    }

    @Override
    public String doAfterTagRenderScript() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}
