package com.dup.server.actions.platform.config;

import org.springframework.stereotype.Component;

import com.dup.core.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("com.dup.server.actions.platform.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
    /**
     * 构造方法。
     */
    public FreeMarkerSettings() {
	setOrder(200);
	addTemplatePath("classpath:/com/dup/server/actions/platform/");
    }
}