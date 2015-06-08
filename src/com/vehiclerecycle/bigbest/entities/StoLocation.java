package com.vehiclerecycle.bigbest.entities;

/**
 * 库位
 * @author LJJ
 *
 */
public class StoLocation {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所属仓库
	 */
	private Storage storage;
	/**
	 * 库位名称
	 */
	private String locName;
	/**
	 * 存放的汽车
	 */
	private String  isEmpty;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
	}
	@Override
	public String toString() {
		return "StoLocation [id=" + id + ", storage=" + storage + ", locName="
				+ locName + ", isEmpty=" + isEmpty + "]";
	}
	
}
