package org.topteam.ui.easyui;

import java.util.Map;

/**
 * TODO
 * Created by 枫 on 2014/8/8.
 */
public class TreeGridTag extends DataGridTag {

    public static final String TAG = "treegrid";

    private String idField;
    private String treeField;
    private Boolean animate;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options =  super.getOptions();
        options.put("idField",getIdField());
        options.put("treeField",getTreeField());
        options.put("animate",getAnimate());
        return options;
    }

    @Override
	public String getIdField() {
        return idField;
    }

    @Override
	public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getTreeField() {
        return treeField;
    }

    public void setTreeField(String treeField) {
        this.treeField = treeField;
    }

    public Boolean getAnimate() {
        return animate;
    }

    public void setAnimate(Boolean animate) {
        this.animate = animate;
    }
}
