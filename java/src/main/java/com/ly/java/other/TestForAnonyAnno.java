package com.ly.java.other;

import java.lang.annotation.Annotation;

/**
 * @功能描述：构造一个注解的匿名对象返回
 * @文件名称：TestForAnonyAnno.java
 * @author ly
 */
public class TestForAnonyAnno {

	public Override test() {
		return new Override() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Override.class;
			}
		};
	}
}
