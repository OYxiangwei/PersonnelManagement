package com.oy.utils;

import com.oy.entity.Department;
import com.oy.entity.Employee;
import com.oy.entity.Position;

public class IntegrateObject {
	public static void genericAssociation(Integer depart_id, Integer pos_id, Employee employee) {
		Department department = new Department();
		department.setId(depart_id);
		Position position = new Position();
		position.setId(pos_id);
		employee.setDepartment(department);
		employee.setPosition(position);
	}

}
