package com.oy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oy.entity.Department;

@Repository
public interface DepartmentDao {
	public List<Department> finDepartments(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addDepartment(Department department);

	public Integer deleteDepartment(Integer id);

	public Integer updateDepartment(Department department);

}
