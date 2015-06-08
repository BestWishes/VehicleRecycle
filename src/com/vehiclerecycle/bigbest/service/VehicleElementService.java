package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.VehicleElementDao;
import com.vehiclerecycle.bigbest.entities.VehicleElement;

@Service
public class VehicleElementService {

	@Autowired
	private VehicleElementDao vehicleElementDao;
	public List<VehicleElement> getAll() {
		return vehicleElementDao.getAll();
	}

	public void saveOrUpdate(HttpSession session, VehicleElement t) {
		vehicleElementDao.saveOrUpdate(session, t);
	}

	public void delete(HttpSession session, VehicleElement t) {
		vehicleElementDao.delete(session, t);
	}

	public List<VehicleElement> getByInfo(VehicleElement t) {
		return vehicleElementDao.getByInfo(t);
	}

	public VehicleElement getOneById(Integer id) {
		return vehicleElementDao.getOneById(id);
	}

}
