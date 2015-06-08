package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.VehicleBasicDao;
import com.vehiclerecycle.bigbest.entities.VehicleBasic;

@Service
public class VehicleBasicService {

	@Autowired
	private VehicleBasicDao vehicleBasicDao;
	
	public List<VehicleBasic> getAll() {
		return vehicleBasicDao.getAll();
	}

	
	public void saveOrUpdate(HttpSession session,VehicleBasic t) {
		vehicleBasicDao.saveOrUpdate(session,t);
	}

	
	public void delete(HttpSession session,VehicleBasic t) {
		vehicleBasicDao.delete(session,t);
	}

	
	public List<VehicleBasic> getByInfo(VehicleBasic t) {
		return vehicleBasicDao.getByInfo(t);
	}

	
	public VehicleBasic getOneById(Integer id) {
		return vehicleBasicDao.getOneById(id);
	}
	public List<VehicleBasic> getByClient(Integer id) {
		return vehicleBasicDao.getByClient(id);
	}
}
