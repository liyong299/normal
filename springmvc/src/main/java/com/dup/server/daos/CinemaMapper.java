package com.dup.server.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dup.base.model.Page;
import com.dup.core.dao.entities.BaseAccount;
import com.dup.server.daos.entities.Cinema;

@Repository
public interface CinemaMapper {
	public List<Cinema> queryList(Page<Cinema> page);
	public List<Cinema> queryCinemasTest();
	public BaseAccount query(BaseAccount account);
	public Integer exist(BaseAccount account);
	public Integer  insert(BaseAccount account);
	public Integer delete(Integer id);
	public Integer update(BaseAccount account);
}