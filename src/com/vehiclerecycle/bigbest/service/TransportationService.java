package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.TransportationDao;
import com.vehiclerecycle.bigbest.entities.Transportation;

@Service
public class TransportationService{

	@Autowired
	private TransportationDao transportationDao;
	public List<Transportation> getAll() {
		return transportationDao.getAll();
	}

	public void saveOrUpdate(HttpSession session, Transportation t) {
		transportationDao.saveOrUpdate(session, t);
	}

	public void delete(HttpSession session, Transportation t) {
		transportationDao.delete(session, t);
	}

	public List<Transportation> getByInfo(Transportation t) {
		return transportationDao.getByInfo(t);
	}

	public Transportation getOneById(Integer id) {
		return transportationDao.getOneById(id);
	}

}
