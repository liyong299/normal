package com.mopon.dao;

import java.util.List;

/**
 * 基础dao 用于存放基础共有方法
 * @author liangweilun
 *
 */
public interface BaseDao {
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	<T> int insert(T t);
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	<T> int update(T t);
	/**
	 *  更加id删除
	 * @param id
	 * @return
	 */
	int deleteById(String id);
	
	/**
	 * 根据条件查询单个对象
	 * @param t
	 * @return
	 */
	<T> T selectSingle(T t);
	
	/**
	 * 根据条件查询返回列表
	 * @param t
	 * @return
	 */
	<T> List<T> selectList(T t);
}
