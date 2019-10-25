package com.oy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oy.dao.PositionDao;
import com.oy.entity.Position;
import com.oy.service.PositionService;

@Service("positionService")
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;

	public List<Position> findPositions(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return positionDao.findPositions(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return positionDao.getCount(map);
	}

	public Integer addPosition(Position position) {
		// TODO Auto-generated method stub
		return positionDao.addPosition(position);
	}

	public Integer deletePosition(Integer id) {
		Integer flag = null;
		try {
			flag = positionDao.deletePosition(id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return flag;
	}

	public Integer updatePosition(Position position) {
		// TODO Auto-generated method stub
		return positionDao.updatePosition(position);
	}

}
