package com.oy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oy.entity.Employee;
import com.oy.service.EmployeeService;
import com.oy.utils.IntegrateObject;
import com.oy.utils.JsonDateValueProcessor;
import com.oy.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("empl")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String list(Employee employee, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (employee.getId() != null && !"".equals(employee.getId())) {
			map.put("id", "%" + employee.getId() + "%");
		}
		if (employee.getName() != null && !"".equals(employee.getName())) {
			map.put("name", "%" + employee.getName() + "%");
		}
		if (employee.getSex() != null && !"".equals(employee.getSex())) {
			map.put("sex", "%" + employee.getSex() + "%");
		}
		if (employee.getDepartment() != null) {
			if (employee.getDepartment().getName() != null && !"".equals(employee.getDepartment().getName().trim())) {
				map.put("department_name", "%" + employee.getDepartment().getName() + "%");
			}
		}

		if (employee.getPosition() != null) {
			if (employee.getPosition().getName() != null && !"".equals(employee.getPosition().getName().trim())) {
				map.put("position_name", "%" + employee.getPosition().getName() + "%");
			}
		}
		List<Employee> employees = employeeService.findEmployees(map);
		Integer totalInteger = employeeService.getCount(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject resultJsonObject = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(employees, jsonConfig);
		resultJsonObject.put("rows", jsonArray);
		resultJsonObject.put("total", totalInteger);
		ResponseUtil.write(response, resultJsonObject);
		return null;
	}

	@RequestMapping("/save")
	public String save(@RequestParam("depart_id") Integer depart_id, @RequestParam("pos_id") Integer pos_id,
			@RequestParam("updateFlag") String updateFlag, Employee employee, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		int resultTotal = 0;
		// 完成 Department 和 Position 在 Employee 中的关联映射
		IntegrateObject.genericAssociation(depart_id, pos_id, employee);
		JSONObject result = new JSONObject();
		if (updateFlag.equals("no")) {
			try {
				resultTotal = employeeService.addEmployee(employee);
				if (resultTotal > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
				}
			} catch (Exception e) {
				result.put("success", false);
			}
		} else if (updateFlag.equals("yes")) {
			resultTotal = employeeService.updateEmployee(employee);
			if (resultTotal > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("ids") String ids, HttpServletResponse response, HttpSession session)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				employeeService.deleteEmployee(idsStr[i]);
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdfDateFormat = new SimpleDateFormat();
		CustomDateEditor editor = new CustomDateEditor(sdfDateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}
}
