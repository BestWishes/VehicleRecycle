package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.VehicleDetail;

@Repository
public class VehicleDetailDao extends BaseDao implements InterfaceDao<VehicleDetail> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.VehicleDetailDao");

	@Override
	public void saveOrUpdate(HttpSession session, VehicleDetail t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加汽车详细信息:"+t.getId()+",业务管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改汽车详细信息:"+t.getId()+",业务管理");
		}	
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, VehicleDetail t) {
		String hql="DELETE VehicleDetail v WHERE v.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();	
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除车辆详细信息:"+t.getId()+",业务单管理");
}

	@SuppressWarnings("unchecked")
	@Override
	public VehicleDetail getOneById(Integer id) {
		List<VehicleDetail> storagelLocations;
		String hql="FROM VehicleDetail s WHERE s.id=:id";
		storagelLocations=this.getSession().createQuery(hql).setInteger("id", id).list();
		return storagelLocations.isEmpty()?(new VehicleDetail()):storagelLocations.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleDetail> getAll() {
		return this.getSession().createQuery("FROM VehicleDetail").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleDetail> getByInfo(VehicleDetail t) {
		List<VehicleDetail> stoLocations = null;
		if(t.getId()!=null){
			stoLocations=this.getSession().createQuery("FROM VehicleDetail s WHERE s.id=:id").setInteger("id", t.getId()).list();
		}
		else if (t.getVehicleBasic()!=null) {
			if (t.getVehicleBasic().getClient()!=null) {
				
				stoLocations=this.getSession().createQuery("FROM VehicleDetail s WHERE s.vehicleBasic.client.name=:locName").setString("locName", t.getVehicleBasic().getClient().getName()).list();
			}
			if (t.getVehicleBasic().getId()!=null) {
				stoLocations=this.getSession().createQuery("FROM VehicleDetail s WHERE s.vehicleBasic.id=:lName").setInteger("lName", t.getVehicleBasic().getId()).list();
			}
			if (t.getVehicleBasic().getState()!=null) {
				stoLocations=this.getSession().createQuery("FROM VehicleDetail s WHERE s.vehicleBasic.state=:sName").setString("sName", t.getVehicleBasic().getState()).list();
			}
		}
		else {
			stoLocations=this.getAll();
		}
		return stoLocations;
	}
}
