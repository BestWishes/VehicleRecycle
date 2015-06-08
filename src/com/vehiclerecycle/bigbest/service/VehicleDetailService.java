package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.VehicleDetailDao;
import com.vehiclerecycle.bigbest.entities.VehicleDetail;

@Service
public class VehicleDetailService {

	@Autowired
	private VehicleDetailDao vehicleDetailDao;
	public List<VehicleDetail> getAll() {
		return vehicleDetailDao.getAll();
	}

	public void saveOrUpdate(HttpSession session, VehicleDetail t) {
		vehicleDetailDao.saveOrUpdate(session, t);
	}

	public void delete(HttpSession session, VehicleDetail t) {
		vehicleDetailDao.delete(session, t);
		}

	public List<VehicleDetail> getByInfo(VehicleDetail t) {
		return vehicleDetailDao.getByInfo(t);
	}

	public VehicleDetail getOneById(Integer id) {
		return vehicleDetailDao.getOneById(id);
	}

}
