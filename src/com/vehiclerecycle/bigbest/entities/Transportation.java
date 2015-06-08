package com.vehiclerecycle.bigbest.entities;

/**
 * 运输方式
 * @author LJJ
 *
 */
public class Transportation {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 运输方式
	 */
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Transportation [id=" + id + ", name=" + name + "]";
	}
	
}
