package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.StorageDao;
import com.vehiclerecycle.bigbest.entities.Storage;
@Service
public class StorageService  {

	@Autowired
	private StorageDao storageDao;
	
	public List<Storage> getAll() {
		return storageDao.getAll();
	}


	public void saveOrUpdate(HttpSession session,Storage t) {
		storageDao.saveOrUpdate(session, t);
	}


	public void delete(HttpSession session,Storage t) {
		storageDao.delete(session, t);
	}


	public List<Storage> getByInfo(Storage t) {
		return storageDao.getByInfo(t);
	}


	public Storage getOneById(Integer id) {
		return storageDao.getOneById(id);
	}

}
