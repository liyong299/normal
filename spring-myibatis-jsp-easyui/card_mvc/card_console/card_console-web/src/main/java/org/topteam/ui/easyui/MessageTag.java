package org.topteam.ui.easyui;

import javax.servlet.jsp.JspWriter;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/9.
 */
public class MessageTag extends TagRender {

    public static final String TAG = "messager";
    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public int renderBody() {
        return 0;
    }

    @Override
    public Map<String, Object> getOptions() {
        return null;
    }

    @Override
    public void renderStart(JspWriter out) {

    }

    @Override
    public void renderEnd(JspWriter out) {

    }
}
