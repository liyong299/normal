package com.mopon.dao.cinema;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.cinema.Cinema;

@Repository
public interface CinemaMapper {
	public List<Cinema> queryCinemaByAreaCode(Cinema cinema);
}
