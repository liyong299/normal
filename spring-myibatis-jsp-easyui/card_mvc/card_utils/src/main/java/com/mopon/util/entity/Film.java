/**
 * 
 */
package com.mopon.util.entity;

import java.io.Serializable;

/**
 * <p>Description: 影片 </p>
 * @date 2015年4月13日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Film implements Serializable {

	public Film(){}
	/**
	 * 编码
	 */
	private String code;
	
	/**
	 *
	 */
	private String name;
	
	/**
	 * 分钟
	 */
	private String duration;
	
	/**
	 * 上映日期 yyyy-MM-dd
	 */
	private String publishDate;
	
	/**
	 * 发行商
	 */
	private String publisher;
	
	/**
	 * 导演
	 */
	private String director;
	
	/**
	 * 演员
	 */
	private String cast;
	
	/**
	 * 简介
	 */
	private String intro;
	
	/**
	 * 放映类型
	 */
	private String showTypes;
	/**
	 * 发行国家
	 */
	private String country;
	/**
	 * 影片类型 多个/分割
	 */
	private String type;
	/**
	 * 语言 多个/分割
	 */
	private String language;
	/**
	 * 海报 多个，分割
	 */
	private String poster;
	/**
	 * 剧照 多个，分割
	 */
	private String stills;
	
	/**
	 * 精彩看点
	 */
	private String highlight;
	/**
	 * 评分
	 */
	private String score;
	

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	private String md5;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getShowTypes() {
		return showTypes;
	}

	public void setShowTypes(String showTypes) {
		this.showTypes = showTypes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getStills() {
		return stills;
	}

	public void setStills(String stills) {
		this.stills = stills;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	
	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public String toString() {
		return "Film [code=" + code + ", name=" + name + ", duration="
				+ duration + ", publishDate=" + publishDate + ", publisher="
				+ publisher + ", director=" + director + ", cast=" + cast
				+ ", intro=" + intro + ", showTypes=" + showTypes
				+ ", country=" + country + ", type=" + type + ", language="
				+ language + ", poster=" + poster + ", stills=" + stills
				+ ", highlight=" + highlight + "]";
	}
	
}
