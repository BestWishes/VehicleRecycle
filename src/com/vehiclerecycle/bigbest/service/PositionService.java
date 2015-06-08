package com.vehiclerecycle.bigbest.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclerecycle.bigbest.dao.PositionDao;
import com.vehiclerecycle.bigbest.entities.Position;

@Service
public class PositionService  {

	@Autowired
	private PositionDao positionDao;
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public List<Position> getAll() {
		
		return positionDao.getAll();
	}

	public void saveOrUpdate(HttpSession session,Position t) {
		positionDao.saveOrUpdate(session,t);
	}

	public void delete(HttpSession session,Position t) {
		positionDao.delete(session,t);
	}

	public List<Position> getByInfo(Position t) {
		return positionDao.getByInfo(t);
	}

	public Position getOneById(Integer id)
	{
		return positionDao.getOneById(id);
	}
}
