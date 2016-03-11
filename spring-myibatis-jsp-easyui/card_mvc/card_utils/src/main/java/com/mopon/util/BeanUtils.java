package com.mopon.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BeanUtils {

	/**
	 * 获取对象中指定属性的值。
	 * 
	 * @param target
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return 返回对象中指定属性的值。
	 */
	public static Object getField(Object target, String fieldName) {
		return getField(target, findField(target.getClass(), fieldName));
	}
	/**
	 * 获取对象中指定属性的值。
	 * 
	 * @param target
	 *            对象
	 * @param field
	 *            属性
	 * @return 返回对象中指定属性的值。
	 */
	public static Object getField(Object target, Field field) {
		try {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			Object result = field.get(target);
			field.setAccessible(accessible);
			return result;
		} catch (Exception e) {
			throw new IllegalStateException("获取对象的属性[" + field.getName()
					+ "]值失败", e);
		}
	}
	
	/**
	 * 获取类中指定名称的属性。
	 * 
	 * @param targetClass
	 *            类
	 * @param fieldName
	 *            属性名
	 * @return 返回对应的属性，如果没找到返回null。
	 */
	public static Field findField(Class<?> targetClass, String fieldName) {
		Assert.notNull(targetClass);
		Assert.notBlank(fieldName);
		for (Field field : getAllDeclaredField(targetClass)) {
			if (fieldName.equals(field.getName())) {
				return field;
			}
		}
		return null;
	}
	
	/**
	 * 递归获取类的Field，直到其父类为Object类。子类的Field排列在父类之前。
	 * 
	 * @param targetClass
	 *            类
	 * @param excludeFieldNames
	 *            排除的属性名称
	 * @return 返回Field列表。
	 */
	public static List<Field> getAllDeclaredField(Class<?> targetClass,
			String... excludeFieldNames) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : targetClass.getDeclaredFields()) {
			if (CollectionUtils.contains(excludeFieldNames, field.getName())) {
				continue;
			}
			fields.add(field);
		}
		Class<?> parentClass = targetClass.getSuperclass();
		if (parentClass != Object.class) {
			fields.addAll(getAllDeclaredField(parentClass, excludeFieldNames));
		}
		return fields;
	}

	
}
