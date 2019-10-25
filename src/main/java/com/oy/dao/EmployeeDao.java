package com.oy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oy.entity.Employee;

@Repository
public interface EmployeeDao {
	public List<Employee> findEmployees(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addEmployee(Employee employee);

	public Integer updateEmployee(Employee employee);

	public Integer deleteEmployee(String id);
}
