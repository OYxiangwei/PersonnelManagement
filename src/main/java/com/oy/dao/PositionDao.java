package com.oy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oy.entity.Position;

@Repository
public interface PositionDao {
	public List<Position> findPositions(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addPosition(Position position);

	public Integer deletePosition(Integer id);

	public Integer updatePosition(Position position);

}
