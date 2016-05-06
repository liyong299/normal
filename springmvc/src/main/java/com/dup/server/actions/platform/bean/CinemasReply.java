package com.dup.server.actions.platform.bean;

import java.util.ArrayList;
import java.util.List;

import com.dup.server.actions.platform.bean.vo.CinemaVo;
import com.dup.server.daos.entities.Cinema;

/**
 * 查询影院列表响应对象。
 */
public class CinemasReply extends ApiReply {
    
	/** 影院列表 */
	private List<CinemaVo> cinemas = new ArrayList<CinemaVo>();

	/**
	 * 构造方法。
	 * 
	 * @param cinemas
	 *            影院列表
	 * @param url
	 *            服务器图片路径
	 */
	public CinemasReply(List<Cinema> cinemas, String url) {
		for (Cinema cinema : cinemas) {
			this.cinemas.add(new CinemaVo(cinema, url));
		}
	}

	public List<CinemaVo> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<CinemaVo> cinemas) {
		this.cinemas = cinemas;
	}
}