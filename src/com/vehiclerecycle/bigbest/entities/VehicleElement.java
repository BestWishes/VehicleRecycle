package com.vehiclerecycle.bigbest.entities;
/**
 * 汽车元件表
 */
public class VehicleElement {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 元件名称
	 */
	private String eleName;
	/**
	 * 元件型号
	 */
	private String eleType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEleName() {
		return eleName;
	}
	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	public String getEleType() {
		return eleType;
	}
	public void setEleType(String eleType) {
		this.eleType = eleType;
	}
	@Override
	public String toString() {
		return "VehicleElement [id=" + id + ", eleName=" + eleName
				+ ", eleType=" + eleType + "]";
	}

	
}
