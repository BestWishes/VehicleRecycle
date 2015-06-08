package com.vehiclerecycle.bigbest.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Storage;

@Repository
public class StorageDao extends BaseDao implements InterfaceDao<Storage> {
	
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.StorageDao");

	@Override
	public void saveOrUpdate(HttpSession session, Storage t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加仓库:"+t.getStorageName()+",仓库管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改仓库:"+t.getStorageName()+",仓库管理");
		}
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, Storage t) {
		String hql="DELETE Storage s WHERE s.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除仓库:"+t.getStorageName()+",仓库管理");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Storage getOneById(Integer id) {
		List<Storage> storages;
		String hql="FROM Storage s WHERE s.id=:id";
		storages=this.getSession().createQuery(hql).setInteger("id", id).list();
		return storages.isEmpty()?(new Storage()):storages.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Storage> getAll() {
		return this.getSession().createQuery("FROM Storage").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Storage> getByInfo(Storage t) {
		List<Storage> storages;
		if(t.getId()!=null){
			storages=this.getSession().createQuery("FROM Storage s WHERE s.id=:id").setInteger("id", t.getId()).list();
		}
		else if (t.getStorageName()!=null) {
			storages=this.getSession().createQuery("FROM Storage s WHERE s.storageName like:storageName").setString("storageName","%"+ t.getStorageName()+"%").list();
		}
		else {
			storages=this.getAll();
		}
		return storages;
	}

}
