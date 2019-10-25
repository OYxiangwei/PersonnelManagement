package com.oy.service;

import java.util.List;
import java.util.Map;

import com.oy.entity.Department;

public interface DepartmentService {
	public List<Department> finDepartments(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addDepartment(Department department);

	public Integer deleteDepartment(Integer id);

	public Integer updateDepartment(Department department);

}
