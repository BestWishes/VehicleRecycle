package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.VehicleBasic;

@Repository
public class VehicleBasicDao extends BaseDao implements InterfaceDao<VehicleBasic>{
	
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.VehicleBasicDao");

	/**
	 * 获取所有业务单信息
	 */
	@SuppressWarnings("unchecked")
	public List<VehicleBasic> getAll() {		
		String hqlString="FROM VehicleBasic";
		List<VehicleBasic> vehicles=this.getSession().createQuery(hqlString).list();
		return vehicles;
	}

	/**
	 * 修改或新增业务单
	 */
	public void saveOrUpdate(HttpSession session,VehicleBasic vehicleBasic) {
		if(vehicleBasic.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加业务单:"+vehicleBasic.getId()+",业务单管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改业务单:"+vehicleBasic.getId()+",业务单管理");
		}
		this.getSession().saveOrUpdate(vehicleBasic);
	}

	@Override
	public void delete(HttpSession session,VehicleBasic t) {
		String hql="DELETE VehicleBasic v WHERE v.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();	
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除业务单:"+t.getId()+",业务单管理");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleBasic> getByInfo(VehicleBasic t) {
		List<VehicleBasic> vehicles;
		StringBuilder hql=new StringBuilder();
		hql.append(" FROM VehicleBasic v WHERE v.id!=:ids");
		
		/**
		 * 管理员 按“业务单ID”查询业务单
		 */
		if (t.getId()!=null) {
			hql.append(" AND v.id =:id ");
			//query=query.setInteger("id", t.getId());
		}
		/**
		 * 业务员 按“业务员”和“业务单状态”查询业务单
		 */
		if (t.getState()!=null) {
			hql.append(" AND v.state =:state ");
			//query=query.setString("state", t.getState());
		}
		/**
		 * 管理员 按“业务员”查询业务单
		 */
		if (t.getClerk()!=null ) {
			hql.append(" AND v.clerk.employeeName like:clerkName ");
			//query=query.setString("clerkName", t.getClerk().getEmployeeName());
			}
		/**
		 * 管理员 按“业务单状态”查询业务单
		 */
		if (t.getServer()!=null) {
			hql.append(" AND v.server.employeeName like:serverName ");
			//query=query.setString("serverName", t.getServer().getEmployeeName());
		}
		Query query=this.getSession().createQuery(hql.toString());
		query=query.setInteger("ids", -1);
		if (t.getId()!=null) {
			query=query.setInteger("id", t.getId());
		}
		if (t.getState()!=null) {
			query=query.setString("state", t.getState());
		}
		if (t.getClerk()!=null ) {
			query=query.setString("clerkName", "%"+t.getClerk().getEmployeeName()+"%");
			}
		if (t.getServer()!=null) {
			query=query.setString("serverName", "%"+t.getServer().getEmployeeName()+"%");
		}
		vehicles=query.list();
		return vehicles;
	}

	@Override
	public VehicleBasic getOneById(Integer id) {
		String hql="FROM VehicleBasic v WHERE v.id=:id";
		@SuppressWarnings("unchecked")
		List<VehicleBasic> vehicles=this.getSession().createQuery(hql).setInteger("id", id).list();
		return vehicles.isEmpty()?(new VehicleBasic()):vehicles.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<VehicleBasic> getByClient(Integer id) {
		String hql="FROM VehicleBasic v WHERE v.client.id=:id";
		return this.getSession().createQuery(hql).setInteger("id", id).list();
	}
}
