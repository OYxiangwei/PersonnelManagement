package com.oy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oy.entity.Position;
import com.oy.service.PositionService;
import com.oy.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("position")
public class PositionController {
	@Autowired
	private PositionService positionService;

	@RequestMapping("/list")
	public String list(Position position, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (position.getName() != null && !"".equals(position.getName().trim())) {
			map.put("name", "%" + position.getName() + "%");
		}
		List<Position> positionList = positionService.findPositions(map);
		Integer total = positionService.getCount(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(positionList);
		result.put("row", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/save")
	public String save(Position position, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalResult = 0;
		if (position.getId() == null) {
			totalResult = positionService.addPosition(position);
		} else {
			totalResult = positionService.updatePosition(position);
		}
		JSONObject result = new JSONObject();
		if (totalResult < 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				positionService.deletePosition(Integer.parseInt(idsStr[i]));
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/getcombobox")
	public JSONArray getPosition(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Position> positionList = positionService.findPositions(map);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Position position : positionList) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", position.getId());
			maps.put("name", position.getName());
			list.add(maps);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}

}
