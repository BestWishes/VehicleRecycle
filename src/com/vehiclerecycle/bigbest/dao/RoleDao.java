package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Role;

@Repository
public class RoleDao extends BaseDao implements InterfaceDao<Role> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.RoleDao");

	@Override
	public void saveOrUpdate(HttpSession session, Role t) {
		this.getSession().saveOrUpdate(t);
		if(t.getId()==null){
			   getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加角色:"+t.getRoleName()+",角色管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改角色:"+t.getRoleName()+",角色管理");
		}
	}

	@Override
	public void delete(HttpSession session, Role t) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Role getOneById(Integer id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Role> getAll() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Role> getByInfo(Role t) {
		// TODO 自动生成的方法存根
		return null;
	}

}
