package com.oy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oy.dao.DepartmentDao;
import com.oy.entity.Department;
import com.oy.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	public List<Department> finDepartments(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return departmentDao.finDepartments(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return departmentDao.getCount(map);
	}

	public Integer addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.addDepartment(department);
	}

	public Integer deleteDepartment(Integer id) {
		// TODO Auto-generated method stub
		return departmentDao.deleteDepartment(id);
	}

	public Integer updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.updateDepartment(department);
	}

}
