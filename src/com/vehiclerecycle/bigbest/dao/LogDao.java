package com.vehiclerecycle.bigbest.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Log;

@Repository
public class LogDao extends BaseDao implements InterfaceDao<Log> {


	@Override
	public void saveOrUpdate(HttpSession session,Log t) {
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session,Log t) {
	
	}

	@Override
	public Log getOneById(Integer id) {
		Log log=new Log();
		String hql="FROM Log l where l.id=id";
		log=(Log) this.getSession().createQuery(hql).setInteger("id", id).list().get(0);
		return log;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getAll() {
		String hql="FROM Log";
		List<Log> logList=this.getSession().createQuery(hql).list();
		return logList;
	}

	@Override
	public List<Log> getByInfo(Log t) {
		return null;
	}

}
