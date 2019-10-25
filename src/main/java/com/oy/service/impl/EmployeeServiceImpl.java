package com.oy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oy.dao.EmployeeDao;
import com.oy.entity.Employee;
import com.oy.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> findEmployees(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployees(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.getCount(map);
	}

	public Integer addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Integer flag = null;
		try {
			flag = employeeDao.addEmployee(employee);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return flag;
	}

	public Integer updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.updateEmployee(employee);
	}

	public Integer deleteEmployee(String id) {
		// TODO Auto-generated method stub
		return employeeDao.deleteEmployee(id);
	}

}
