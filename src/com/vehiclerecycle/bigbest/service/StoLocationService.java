package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.StoLocationDao;
import com.vehiclerecycle.bigbest.entities.StoLocation;

@Service
public class StoLocationService {

	@Autowired
	private StoLocationDao stoLocationDao;

	public List<StoLocation> getAll() {
		return stoLocationDao.getAll();
	}


	public void saveOrUpdate(HttpSession session, StoLocation t) {
		stoLocationDao.saveOrUpdate(session, t);
	}


	public void delete(HttpSession session,StoLocation t) {
		stoLocationDao.delete(session, t);
	}


	public List<StoLocation> getByInfo(StoLocation t) {
		return stoLocationDao.getByInfo(t);
	}


	public StoLocation getOneById(Integer id) {
		return stoLocationDao.getOneById(id);
	}

}
