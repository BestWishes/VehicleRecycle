package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.RevokeBillDao;
import com.vehiclerecycle.bigbest.entities.RevokeBill;

@Service
public class RevokeBillService{

	@Autowired
	private RevokeBillDao revokeDao;
	
	public List<RevokeBill> getAll() {
		return revokeDao.getAll();
	}

	
	public void saveOrUpdate(HttpSession session,RevokeBill t) {
		revokeDao.saveOrUpdate(session,t);
	}

	
	public void delete(HttpSession session,RevokeBill t) {
		revokeDao.delete(session,t);
	}

	
	public List<RevokeBill> getByInfo(RevokeBill t) {
		return revokeDao.getByInfo(t);
	}

	
	public RevokeBill getOneById(Integer id) {
		return revokeDao.getOneById(id);
	}

}
