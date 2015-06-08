package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.RevokeBill;

@Repository
public class RevokeBillDao extends BaseDao implements InterfaceDao<RevokeBill>{	
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.RevokeBillDao");

	@SuppressWarnings("unchecked")
	public List<RevokeBill> getAll() {
		String hql="FROM RevokeBill";
		List<RevokeBill> revokeAssigns=this.getSession().createQuery(hql).list();
		return revokeAssigns;
	}
	

	public void saveOrUpdate(HttpSession session,RevokeBill t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加撤销单:"+t.getId()+",撤销单管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改撤销单:"+t.getId()+",撤销单管理");
		}
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session,RevokeBill t) {
		String hql="DELETE RevokeBill r WHERE r.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除撤销单:"+t.getId()+",撤销单管理");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RevokeBill> getByInfo(RevokeBill t) {
		List<RevokeBill> revokeBills;
		if (t.getId()!=null) {
			String hql="FROM RevokeBill r WHERE r.id=:id";
			revokeBills=this.getSession().createQuery(hql).setInteger("id", t.getId()).list();
		}
		else {
			revokeBills=this.getAll();
		}
		return revokeBills;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RevokeBill getOneById(Integer id) {
		String hql="FROM RevokeBill r WHERE r.id=:id";
		List<RevokeBill> revokeBills=this.getSession().createQuery(hql).setInteger("id", id).list();
		return revokeBills.isEmpty()?(new RevokeBill()):revokeBills.get(0);
	}
}
