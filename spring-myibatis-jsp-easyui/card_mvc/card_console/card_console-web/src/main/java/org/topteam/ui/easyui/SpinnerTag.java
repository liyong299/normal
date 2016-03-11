package org.topteam.ui.easyui;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/12.
 */
public class SpinnerTag extends TextBoxTag {
    public static final String TAG = "spinner";

    private Object min;
    private Object max;
    private int increment = -1;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("min", getMin());
        options.put("max", getMax());
        options.put("increment", getIncrement());
        return options;
    }

    public Object getMin() {
        return min;
    }

    public void setMin(Object min) {
        this.min = min;
    }

    public Object getMax() {
        return max;
    }

    public void setMax(Object max) {
        this.max = max;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
