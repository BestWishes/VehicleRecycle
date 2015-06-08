package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Transportation;

@Repository
public class TransportationDao extends BaseDao implements InterfaceDao<Transportation> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.TransportationDao");

	@Override
	public void saveOrUpdate(HttpSession session, Transportation t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加运输方式:"+t.getName()+",数据字典");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改运输方式:"+t.getName()+",数据字典");
		}		
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, Transportation t) {
		String hql="DELETE Transportation v WHERE v.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();	
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除运输方式:"+t.getId()+",数据字典");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Transportation getOneById(Integer id) {
		List<Transportation> storagelLocations;
		String hql="FROM Transportation s WHERE s.id=:id";
		storagelLocations=this.getSession().createQuery(hql).setInteger("id", id).list();
		return storagelLocations.isEmpty()?(new Transportation()):storagelLocations.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transportation> getAll() {
		return this.getSession().createQuery("FROM Transportation").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transportation> getByInfo(Transportation t) {
		List<Transportation> stoLocations;
		if(t.getId()!=null){
			stoLocations=this.getSession().createQuery("FROM Transportation s WHERE s.id=:id").setInteger("id", t.getId()).list();
		}
		else if (t.getName()!=null) {
			stoLocations=this.getSession().createQuery("FROM Transportation s WHERE s.name like:locName").setString("locName","%"+ t.getName()+"%").list();
		}
		else {
			stoLocations=this.getAll();
		}
		return stoLocations;
	}

}
