package com.mopon.card.api.util.freemarker;

import org.springframework.stereotype.Component;

/**
 * FreeMarker配置组件。
 */
@Component("com.dup.core.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		addTemplatePath("/views/");  // 没有它会找不到模板路径
//		addTemplatePath("classpath:/coo/mvc/templates/");
		addAutoImport("style", "comm/macros/mystyle.ftl");
		addAutoImport("html", "comm/macros/html_template.ftl");
//		addAutoImport("dwz", "dwz.ftl");
//		addAutoImport("ecs", "ecs.ftl");
//		addStaticClass(StringUtils.class);
//		addStaticClass(DateUtils.class);
//		addEnumPackage("coo.core.enums");
	}
}