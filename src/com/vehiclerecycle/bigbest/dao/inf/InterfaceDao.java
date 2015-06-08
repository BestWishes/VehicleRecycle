package com.vehiclerecycle.bigbest.dao.inf;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface InterfaceDao<T> {

	/**
	 * 新增或修改
	 * @param t
	 */
	public void saveOrUpdate(HttpSession session,T t);
	/**
	 * 删除
	 * @param t
	 */
	public void delete(HttpSession session,T t);
	/**
	 * ͨ通过ID获取
	 * @param id
	 * @return
	 */
	public T getOneById(Integer id);
	/**
	 * 获取所有
	 * @return
	 */
	public List<T> getAll();
	/**
	 * 通过信息获取
	 * @param t
	 * @return
	 */
	public List<T> getByInfo(T t);
	
}
