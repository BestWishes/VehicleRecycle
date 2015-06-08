package com.vehiclerecycle.bigbest.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehiclerecycle.bigbest.dao.EmployeeDao;
import com.vehiclerecycle.bigbest.entities.Employee;

@Service
public class EmployeeService {

	
	@Autowired
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
		
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}
	
	public Employee login(HttpSession session, Employee employee) {
		return employeeDao.login(session, employee);
	}
	
	public void saveOrUpdate(HttpSession session, Employee employee){
		employeeDao.saveOrUpdate(session,employee);
	}

	
	public void delete(HttpSession session,Employee t) {
		employeeDao.delete(session,t);
	}

	public Employee getOneById(Integer id){
		return employeeDao.getOneById(id);
	};
	
	public List<Employee> getByInfo(Employee t) {
		
		return employeeDao.getByInfo(t);
	}
	
}
