package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.VehicleToElement;

@Repository
public class VehicleToElementDao extends BaseDao implements InterfaceDao<VehicleToElement> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.VehicleToElementDao");

	@Override
	public void saveOrUpdate(HttpSession session, VehicleToElement t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加汽车元件信息:"+t.getVehicleElement().getEleName()+",业务管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改汽车元件信息:"+t.getVehicleElement().getEleName()+",业务管理");
		}	
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, VehicleToElement t) {
		String hql="DELETE VehicleToElement v WHERE v.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();	
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除汽车元件信息:"+t.getId()+",业务管理");
	}

	@SuppressWarnings("unchecked")
	@Override
	public VehicleToElement getOneById(Integer id) {
		List<VehicleToElement> storagelLocations;
		String hql="FROM VehicleToElement s WHERE s.id=:id";
		storagelLocations=this.getSession().createQuery(hql).setInteger("id", id).list();
		return storagelLocations.isEmpty()?(new VehicleToElement()):storagelLocations.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleToElement> getAll() {
		return this.getSession().createQuery("FROM VehicleToElement").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleToElement> getByInfo(VehicleToElement t) {
		List<VehicleToElement> stoLocations;
		if(t.getId()!=null){
			stoLocations=this.getSession().createQuery("FROM VehicleToElement s WHERE s.id=:id").setInteger("id", t.getId()).list();
		}
		else if (t.getVehicleElement()!=null) {
			stoLocations=this.getSession().createQuery("FROM VehicleToElement s WHERE s.vehicleElement.eleName=:locName").setString("locName", t.getVehicleElement().getEleName()).list();
		}
		else if (t.getVehicleDetail()!=null) {
			stoLocations=this.getSession().createQuery("FROM VehicleToElement s WHERE s.vehicleDetail.id=:detailId").setInteger("detailId", t.getVehicleDetail().getId()).list();
		}
		else {
			stoLocations=this.getAll();
		}
		return stoLocations;
	}

	
}
