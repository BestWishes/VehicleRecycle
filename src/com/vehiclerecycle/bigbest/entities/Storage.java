package com.vehiclerecycle.bigbest.entities;

public class Storage {

	/**
	 * 仓库编号
	 */
	private Integer id;
	/**
	 * 仓库名称
	 */
	private String storageName;
	/**
	 * 仓库地址
	 */
	private String storageAddress;
	/**
	 * 仓库容量
	 */
	private Integer storageCapacity;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStorageName() {
		return storageName;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	public String getStorageAddress() {
		return storageAddress;
	}
	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}
	public Integer getStorageCapacity() {
		return storageCapacity;
	}
	public void setStorageCapacity(Integer storageCapacity) {
		this.storageCapacity = storageCapacity;
	}
	@Override
	public String toString() {
		return "Storage [id=" + id + ", storageName=" + storageName
				+ ", storageAddress=" + storageAddress + ", storageCapacity="
				+ storageCapacity + "]";
	}
	
}
