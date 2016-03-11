package com.mopon.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * 
 * <p>Title: 自动增长ArrayList</p>
 * <p>Description: 页面多行数据提交到Controller时，用于封装多行数据</p>
 * <p>Copyright:Copyright(c)2014</p>
 * <p>Company:timeplatform</p>
 * @date 2015-2-4
 * @author tongbiao
 * @version 1.0
 */

@SuppressWarnings("rawtypes")
public class AutoArrayList extends ArrayList {
	private static Logger LOGGER = Logger.getLogger(AutoArrayList.class);
	private static final long serialVersionUID = 5332296994651370661L;
	private Class classItem;

	public AutoArrayList(Class classItem) {
		this.classItem = classItem;
	}

	@SuppressWarnings("unchecked")
	public Object get(int index) {
		try {
			while (index >= size()) {
				add(classItem.newInstance());
			}
		} catch (Exception e) {
			LOGGER.error("获取数据异常:", e);
		}
		return super.get(index);
	}

}
