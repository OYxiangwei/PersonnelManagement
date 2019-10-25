package com.oy.service;

import java.util.List;
import java.util.Map;

import com.oy.entity.Admin;

public interface AdminService {
	public Admin login(Admin admin);

	public List<Admin> findAdmins(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addAdmin(Admin admin);

	public Integer updateAdmin(Admin admin);

	public Integer deleteAdmin(Integer id);

}
