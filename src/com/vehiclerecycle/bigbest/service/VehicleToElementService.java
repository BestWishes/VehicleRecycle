package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.VehicleToElementDao;
import com.vehiclerecycle.bigbest.entities.VehicleToElement;

@Service
public class VehicleToElementService {

	@Autowired
	private VehicleToElementDao vehicleToElementDao;
	public List<VehicleToElement> getAll() {
		return vehicleToElementDao.getAll();
	}

	public void saveOrUpdate(HttpSession session, VehicleToElement t) {
		vehicleToElementDao.saveOrUpdate(session, t);
	}

	public void delete(HttpSession session, VehicleToElement t) {
		vehicleToElementDao.delete(session, t);
	}

	public List<VehicleToElement> getByInfo(VehicleToElement t) {
		return vehicleToElementDao.getByInfo(t);
	}

	public VehicleToElement getOneById(Integer id) {
		return vehicleToElementDao.getOneById(id);
	}

}
