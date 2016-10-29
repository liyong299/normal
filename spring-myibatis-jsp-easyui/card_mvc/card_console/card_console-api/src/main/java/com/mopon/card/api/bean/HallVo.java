package com.mopon.card.api.bean;

import java.util.ArrayList;
import java.util.List;

public class HallVo
{

	private String id;
	private List<SeatVo> seats = new ArrayList<>();
	
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	/**
	 * @return the seats
	 */
	public List<SeatVo> getSeats()
	{
		return seats;
	}
	/**
	 * @param seats the seats to set
	 */
	public void setSeats(List<SeatVo> seats)
	{
		this.seats = seats;
	}
	

}
