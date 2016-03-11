package com.mopon.entity.cinema;

public class Hall {
	
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 影听编码
	 */
	private String code;

	/**
	 * 影听名称
	 */
	private String name;

	/**
	 *座位数量
	 */
	private int seatCount;

	/**
	 * 标准影院编码
	 */
	private long normcinemacode;

	/**
	 * 外部影院编码
	 */
	private String extcinemacode;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

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

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public long getNormcinemacode() {
		return normcinemacode;
	}

	public void setNormcinemacode(long normcinemacode) {
		this.normcinemacode = normcinemacode;
	}

	public String getExtcinemacode() {
		return extcinemacode;
	}

	public void setExtcinemacode(String extcinemacode) {
		this.extcinemacode = extcinemacode;
	}
}
