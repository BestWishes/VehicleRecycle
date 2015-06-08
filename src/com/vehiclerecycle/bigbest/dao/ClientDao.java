package com.vehiclerecycle.bigbest.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Client;

@Repository
public class ClientDao extends BaseDao implements InterfaceDao<Client> {

	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.ClientDao");

	@Override
	public void saveOrUpdate(HttpSession session, Client t) {
		if(t.getId()==null){
			   getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加客户:"+t.getName()+",客户管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改客户:"+t.getName()+",客户管理");
		}
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, Client t) {
		String hql="DELETE Client c WHERE c.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除客户:"+t.getName()+",客户管理");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Client getOneById(Integer id) {
		String hql="FROM Client t WHERE t.id=:id";
		List<Client> clients=this.getSession().createQuery(hql).setInteger("id", id).list();
		return clients.isEmpty()?(new Client()):clients.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		String hql="FROM Client";
		List<Client> clients=this.getSession().createQuery(hql).list();
		return clients;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getByInfo(Client t) {
		List<Client> clients;
		if (t.getName()!=null) {
			String hqlString="FROM Client t WHERE t.name like:clientName";
			clients=this.getSession().createQuery(hqlString).setString("clientName","%"+t.getName()+"%").list();
		}else {
			clients=this.getAll();
		}

		return clients;
	}

}
