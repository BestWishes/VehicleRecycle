package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.DepartmentDao;
import com.vehiclerecycle.bigbest.entities.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	public List<Department> getAll() {
		return departmentDao.getAll();			
	}

	public void saveOrUpdate(HttpSession session,Department t) {
		departmentDao.saveOrUpdate(session,t);
	}

	public void delete(HttpSession session,Department t) {
		departmentDao.delete(session,t);
	}

	public List<Department> getByInfo(Department t) {
		return departmentDao.getByInfo(t);
	}

	public Department getOneById(Integer id) {
		return departmentDao.getOneById(id);
	}
}
