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
import org.springframework.web.bind.annotation.ResponseBody;

import com.oy.entity.Department;
import com.oy.service.DepartmentService;
import com.oy.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("dept")
public class DeptController {
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/list")
	public String list(Department department, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (department.getName() != null && !"".equals(department.getName().trim())) {
			map.put("name", "%" + department.getName() + "%");
		}
		List<Department> departmentslist = departmentService.finDepartments(map);
		Integer total = departmentService.getCount(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(departmentslist);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/save")
	public String save(Department department, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int resultTotal = 0;
		if (department.getId() == null) {
			resultTotal = departmentService.addDepartment(department);
		} else {
			resultTotal = departmentService.updateDepartment(department);
		}
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
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
				departmentService.deleteDepartment(Integer.parseInt(idsStr[i]));
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/getcombobox")
	@ResponseBody
	public JSONArray getDepartment(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Department> depatmentList = departmentService.finDepartments(map);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Department department : depatmentList) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", department.getId());
			result.put("name", department.getName());
			list.add(result);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}

}
