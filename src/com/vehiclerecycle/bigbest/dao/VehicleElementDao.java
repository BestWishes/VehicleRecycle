package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.VehicleElement;

@Repository
public class VehicleElementDao extends BaseDao implements InterfaceDao<VehicleElement> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.VehicleElementDao");

	@Override
	public void saveOrUpdate(HttpSession session, VehicleElement t) {
		if(t.getId()==null){
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加元件:"+t.getEleName()+",数据字典");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改元件:"+t.getEleName()+",数据字典");
		}	
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session, VehicleElement t) {
		String hql="DELETE VehicleElement v WHERE v.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();	
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除元件:"+t.getId()+",数据字典");
}

	@SuppressWarnings("unchecked")
	@Override
	public VehicleElement getOneById(Integer id) {
		List<VehicleElement> storagelLocations;
		String hql="FROM VehicleElement s WHERE s.id=:id";
		storagelLocations=this.getSession().createQuery(hql).setInteger("id", id).list();
		return storagelLocations.isEmpty()?(new VehicleElement()):storagelLocations.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleElement> getAll() {
		return this.getSession().createQuery("FROM VehicleElement").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleElement> getByInfo(VehicleElement t) {
		List<VehicleElement> stoLocations;
		if(t.getId()!=null){
			stoLocations=this.getSession().createQuery("FROM VehicleElement s WHERE s.id=:id").setInteger("id", t.getId()).list();
		}
		else if (t.getEleName()!=null) {
			stoLocations=this.getSession().createQuery("FROM VehicleElement s WHERE s.eleName like:locName").setString("locName", "%"+t.getEleName()+"%").list();
		}
		else {
			stoLocations=this.getAll();
		}
		return stoLocations;
	}

}
