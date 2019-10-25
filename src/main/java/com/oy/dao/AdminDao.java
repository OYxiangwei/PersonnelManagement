package com.oy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oy.entity.Admin;

@Repository
public interface AdminDao {
	public Admin login(Admin admin);

	public List<Admin> findAdmins(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addAdmin(Admin admin);

	public Integer updateAdmin(Admin admin);

	public Integer deleteAdmin(Integer id);
}
