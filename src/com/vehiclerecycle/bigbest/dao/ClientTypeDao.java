package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.ClientType;
@Repository
public class ClientTypeDao extends BaseDao implements InterfaceDao<ClientType> {

	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.ClientTypeDao");

	
	@Override
	public void saveOrUpdate(HttpSession session, ClientType t) {
		if(t.getId()==null){
			   getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加客户类型:"+t.getTypeName()+",数据字典");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改客户类型:"+t.getTypeName()+",数据字典");
		}
		this.getSession().saveOrUpdate(t);
		
	}

	@Override
	public void delete(HttpSession session, ClientType t) {
		String hql="DELETE ClientType c WHERE c.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除客户类型:"+t.getTypeName()+",数据字典");	
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClientType getOneById(Integer id) {
		String hql="FROM ClientType t WHERE t.id=:id";
		List<ClientType> clientTypes=this.getSession().createQuery(hql).setInteger("id", id).list();
		return clientTypes.isEmpty()?(new ClientType()):clientTypes.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientType> getAll() {
		String hql="FROM ClientType";
		List<ClientType> clientTypes=this.getSession().createQuery(hql).list();
		return clientTypes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientType> getByInfo(ClientType t) {
		List<ClientType> clientTypes;
		if (t.getId()!=null) {
			String hqlString="FROM ClientType t WHERE t.id =:id";
			clientTypes=this.getSession().createQuery(hqlString).setInteger("id", t.getId()).list();
		}else if (t.getTypeName()!=null) {
			String hqlString="FROM ClientType t WHERE t.typeName like:clientName";
			clientTypes=this.getSession().createQuery(hqlString).setString("clientName","%"+t.getTypeName()+"%").list();
		}else {
			clientTypes=this.getAll();
		}

		return clientTypes;
	}

}
