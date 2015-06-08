package com.vehiclerecycle.bigbest.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehiclerecycle.bigbest.dao.ClientTypeDao;
import com.vehiclerecycle.bigbest.entities.ClientType;
@Service
public class ClientTypeService {
	@Autowired
	private ClientTypeDao clientTypeDao;
	
	public List<ClientType> getAll() {
		return clientTypeDao.getAll();
	}

	public void saveOrUpdate(HttpSession session,ClientType t) {
		clientTypeDao.saveOrUpdate(session, t);
	}

	public void delete(HttpSession session,ClientType t) {
		clientTypeDao.delete(session, t);		
	}

	public List<ClientType> getByInfo(ClientType t) {
		return clientTypeDao.getByInfo(t);
	}

	public ClientType getOneById(Integer id) {
		return clientTypeDao.getOneById(id);
	}

}
