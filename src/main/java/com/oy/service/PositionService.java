package com.oy.service;

import java.util.List;
import java.util.Map;

import com.oy.entity.Position;

public interface PositionService {
	public List<Position> findPositions(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addPosition(Position position);

	public Integer deletePosition(Integer id);

	public Integer updatePosition(Position position);

}
