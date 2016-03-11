package org.topteam.ui.easyui;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/12.
 */
public class DateTimeBoxTag extends DateBoxTag {
    public static final String TAG ="datetimebox";

    private String formatter = "yyyy-MM-dd HH:mm:ss";
    private String spinnerWidth;
    private Boolean showSeconds;
    private String timeSeparator;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("spinnerWidth",getSpinnerWidth());
        options.put("showSeconds",getShowSeconds());
        options.put("timeSeparator", getTimeSeparator());
        return options;
    }

    public String getSpinnerWidth() {
        return spinnerWidth;
    }

    public void setSpinnerWidth(String spinnerWidth) {
        this.spinnerWidth = spinnerWidth;
    }

    public Boolean getShowSeconds() {
        return showSeconds;
    }

    public void setShowSeconds(Boolean showSeconds) {
        this.showSeconds = showSeconds;
    }

    public String getTimeSeparator() {
        return timeSeparator;
    }

    public void setTimeSeparator(String timeSeparator) {
        this.timeSeparator = timeSeparator;
    }

    @Override
	public String getFormatter() {
        return formatter;
    }

    @Override
	public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
