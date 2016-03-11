package com.mopon.dao.show;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mopon.util.entity.Show;

@Repository("syncShowMapper")
public interface SyncShowMapper {

	/**
	 *
	  * purpose：批量增加<br>
	  * step: <br>
	  * @param record
	  * @return
	  * int
	 */
	public int insertShowBatch(List<Show> records);
	/**
	 *
	 * purpose:<br>
	 * step: <br>
	 * @param code
	 * @return
	 * Cinema
	 */
	public List<Show> queryShowAll();

	public List<Show> queryShowAllBySync(Show show);

	/**
	 *
	 * purpose:单个修改<br>
	 * step: <br>
	 * @param recordsUpdate
	 * void
	 */
	public int updateShowStatus(@Param("item")Show record);
}