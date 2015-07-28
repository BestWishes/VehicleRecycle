package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Employee;
import com.vehiclerecycle.bigbest.util.EncryptUtil;


@Repository
public class EmployeeDao extends BaseDao implements InterfaceDao<Employee>{
	
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.EmployeeDao");
	/**
	 * 获取所有的用户
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getAll() {
		String hqlString="FROM Employee";
		List<Employee> emList=this.getSession().createQuery(hqlString).list();
		return emList;
	}
	/**
	 * 登录验证
	 * @param session 
	 * @param employee
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Employee login(HttpSession session, Employee employee) {
		String  hqlString="FROM Employee e where e.employeeName=? and e.password=?";
		List<Employee> emList=this.getSession().createQuery(hqlString).setString(0, employee.getEmployeeName()).setString(1, EncryptUtil.encrypt(employee.getPassword())).list();
		if (emList.isEmpty()) 
		{
			employee=new Employee();
			employee.setId(-1);
			return employee;
		}else {
			getlogger.error(emList.get(0).getEmployeeName()+",登录"+",用户管理");

			return emList.get(0);
			
		}		
	}

	/**
	 * 修改或新增用户信息
	 * @param session 
	 */
	public void saveOrUpdate(HttpSession session, Employee employee) {
		if(employee.getId()==null){
			   getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加用户:"+employee.getEmployeeName()+",用户管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改用户:"+employee.getEmployeeName()+",用户管理");
		}
		employee.setPassword(EncryptUtil.encrypt(employee.getPassword()));
		this.getSession().saveOrUpdate(employee);
	}

	@Override
	public void delete(HttpSession session, Employee t) {
		String hql="DELETE Employee e WHERE e.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除用户:"+t.getEmployeeName()+",用户管理");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getByInfo(Employee e) {
		List<Employee> employees;
		if (e.getId()!=null) {
			String hqlString="FROM Employee e WHERE e.id like:id";
			employees=this.getSession().createQuery(hqlString).setInteger("id", e.getId()).list();
		}else if (e.getEmployeeName()!=null) {
			String hqlString="FROM Employee e WHERE e.employeeName like:employeeName";
			employees=this.getSession().createQuery(hqlString).setString("employeeName","%"+e.getEmployeeName()+"%").list();

		}else if (e.getPosition()!=null) {
			String hqlString="FROM Employee e WHERE e.position.positionName =:positionName";
			employees=this.getSession().createQuery(hqlString).setString("positionName",e.getPosition().getPositionName()).list();

		}else {
			employees=this.getAll();
		}

		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee getOneById(Integer id) {
		String hql="FROM Employee e WHERE e.id=:id";
		List<Employee> employee=this.getSession().createQuery(hql).setInteger("id", id).list();
		return employee.isEmpty()?(new Employee()):employee.get(0);
	}
}
