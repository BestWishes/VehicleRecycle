package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.ClientDao;
import com.vehiclerecycle.bigbest.entities.Client;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	public List<Client> getAll() {
		return clientDao.getAll();
	}

	public void saveOrUpdate(HttpSession session,Client t) {
		clientDao.saveOrUpdate(session, t);
	}

	public void delete(HttpSession session,Client t) {
		clientDao.delete(session, t);		
	}

	public List<Client> getByInfo(Client t) {
		return clientDao.getByInfo(t);
	}

	public Client getOneById(Integer id) {
		return clientDao.getOneById(id);
	}

}
