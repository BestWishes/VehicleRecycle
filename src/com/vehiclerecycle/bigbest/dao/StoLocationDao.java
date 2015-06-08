package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.StoLocation;

@Repository
public class StoLocationDao extends BaseDao implements InterfaceDao<StoLocation> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.StoLocationDao");

	@Override
	public void saveOrUpdate(HttpSession session, StoLocation t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加库位:"+t.getLocName()+",库位管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改库位:"+t.getLocName()+",库位管理");
		}
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, StoLocation t) {
		String hql="DELETE StoLocation s WHERE s.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除库位:"+t.getLocName()+",仓库管理");
		}

	@SuppressWarnings("unchecked")
	@Override
	public StoLocation getOneById(Integer id) {
		List<StoLocation> storagelLocations;
		String hql="FROM StoLocation s WHERE s.id=:id";
		storagelLocations=this.getSession().createQuery(hql).setInteger("id", id).list();
		return storagelLocations.isEmpty()?(new StoLocation()):storagelLocations.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoLocation> getAll() {
		return this.getSession().createQuery("FROM StoLocation").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoLocation> getByInfo(StoLocation t) {
		List<StoLocation> stoLocations;
		if(t.getId()!=null){
			stoLocations=this.getSession().createQuery("FROM StoLocation s WHERE s.id=:id").setInteger("id", t.getId()).list();
		}
		else if (t.getLocName()!=null) {
			stoLocations=this.getSession().createQuery("FROM StoLocation s WHERE s.locName like:locName").setString("locName", "%"+t.getLocName()+"%").list();
		}
		else if (t.getStorage()!=null) {
			stoLocations=this.getSession().createQuery("FROM StoLocation s WHERE s.storage.storageName like:storageName").setString("storageName", "%"+t.getStorage().getStorageName()+"%").list();
		}
		else {
			stoLocations=this.getAll();
		}
		return stoLocations;
	}

}
