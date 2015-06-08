package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Department;

@Repository
public class DepartmentDao extends BaseDao implements InterfaceDao<Department> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.DepartmentDao");

	@Override
	public void saveOrUpdate(HttpSession session,Department t) {
		if(t.getId()==null){
			   getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加部门:"+t.getDeptName()+",部门管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改部门:"+t.getDeptName()+",部门管理");
		}
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session,Department t) {
		this.getSession().delete(t);
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除部门:"+t.getDeptName()+",部门管理");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAll() {
		String hql="FROM Department";
		List<Department> departments=this.getSession().createQuery(hql).list();
		return departments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getByInfo(Department t) {
		List<Department> departments;
		if (t.getId()!=null) {
			String hql="FROM Department d WHERE d.id=:id";
			departments=this.getSession().createQuery(hql).setInteger("id",t.getId()).list();
		}
		else if (t.getDeptName()!=null) {
			String hql="FROM Department d WHERE d.deptName like :deptName";
			departments=this.getSession().createQuery(hql).setString("deptName", "%"+t.getDeptName()+"%").list();
		}
		else {
			departments=this.getAll();
		}
		return departments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Department getOneById(Integer id) {
		List<Department> departments;
		String hql="FROM Department d WHERE d.id=:id";	
		departments=this.getSession().createQuery(hql).setInteger("id", id).list();
		return departments.isEmpty()?(new Department()):departments.get(0);
	}

}
