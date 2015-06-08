package com.vehiclerecycle.bigbest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.LogDao;
import com.vehiclerecycle.bigbest.entities.Log;

@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	
	public List<Log> getAll() {
		return logDao.getAll();
	}
}
