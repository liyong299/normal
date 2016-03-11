package org.topteam.ui.easyui;

import java.util.Map;

/**
 * Created by JiangFeng on 2014/9/4.
 */
public class SelectManyCheckBoxTag extends ComboBoxTag {

    public static final String TAG = "selectManyCheckbox";

    private int columns = -1;

    private Boolean checkAllable;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("columns", getColumns());
        options.put("checkAllable", getCheckAllable());
        return options;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Boolean getCheckAllable() {
        return checkAllable;
    }

    public void setCheckAllable(Boolean checkAllable) {
        this.checkAllable = checkAllable;
    }
}
