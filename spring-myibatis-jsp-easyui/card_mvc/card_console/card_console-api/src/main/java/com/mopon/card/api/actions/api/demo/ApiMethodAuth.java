package com.mopon.card.api.actions.api.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mopon.card.api.actions.api.APIAuth;

/**
 * 通过注解进行接口权限校验
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ApiMethodAuth 
{
	/** 访问类型 */
	APIAuth value();
}