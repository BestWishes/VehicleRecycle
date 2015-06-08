package com.vehiclerecycle.bigbest.service.inf;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface InterfaceService<T> {

	public List<T> getAll();
	public void saveOrUpdate(HttpSession session, T t);
	public void delete(HttpSession session,T t);
	public List<T> getByInfo(T t);
	public T getOneById(Integer id);
}
