package com.vehiclerecycle.bigbest.entities;

/**
 * 汽车详情单
 * @author LJJ
 *
 */
public class VehicleDetail {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 汽车基本信息单
	 */
	private VehicleBasic vehicleBasic;
	/**
	 * 运输方式
	 */
	private Transportation transportation; 
	/**
	 * 汽车重量
	 */
	private Double vehicleWeght;
	/**
	 * 存放位置
	 */
	private StoLocation stoLocation;
	/**
	 * 入库日期
	 */
	private java.util.Date stoDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public VehicleBasic getVehicleBasic() {
		return vehicleBasic;
	}
	public void setVehicleBasic(VehicleBasic vehicleBasic) {
		this.vehicleBasic = vehicleBasic;
	}
	public Transportation getTransportation() {
		return transportation;
	}
	public void setTransportation(Transportation transportation) {
		this.transportation = transportation;
	}
	public Double getVehicleWeght() {
		return vehicleWeght;
	}
	public void setVehicleWeght(Double vehicleWeght) {
		this.vehicleWeght = vehicleWeght;
	}
	public StoLocation getStoLocation() {
		return stoLocation;
	}
	public void setStoLocation(StoLocation stoLocation) {
		this.stoLocation = stoLocation;
	}
	public java.util.Date getStoDate() {
		return stoDate;
	}
	public void setStoDate(java.util.Date stoDate) {
		this.stoDate = stoDate;
	}
	@Override
	public String toString() {
		return "VehicleDetail [id=" + id + ", vehicleBasic=" + vehicleBasic
				+ ", transportation=" + transportation + ", vehicleWeght="
				+ vehicleWeght + ", stoLocation=" + stoLocation + ", stoDate="
				+ stoDate + "]";
	}
	
}
