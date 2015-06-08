package com.vehiclerecycle.bigbest.entities;

import java.util.Date;

/**
 * 汽车元件对应表
 * @author LJJ
 *
 */
public class VehicleToElement {

	/**
	 * 标号
	 */
	private Integer id;
	/**
	 * 汽车详情表
	 */
	private VehicleDetail vehicleDetail;
	/**
	 * 汽车元件表
	 */
	private VehicleElement vehicleElement;
	/**
	 * 元件品质
	 */
	private String eleQuality;
	/**
	 * 元件生产商
	 */
	private String eleProducer;
	/**
	 * 元件生产日期
	 */
	private Date eleProDate;
	/**
	 * 元件价格
	 */
	private String elePrice;
	/**
	 * 元件数量
	 */
	private int elenumber;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public VehicleDetail getVehicleDetail() {
		return vehicleDetail;
	}
	public void setVehicleDetail(VehicleDetail vehicleDetail) {
		this.vehicleDetail = vehicleDetail;
	}
	public VehicleElement getVehicleElement() {
		return vehicleElement;
	}
	public void setVehicleElement(VehicleElement vehicleElement) {
		this.vehicleElement = vehicleElement;
	}
	public String getEleQuality() {
		return eleQuality;
	}
	public void setEleQuality(String eleQuality) {
		this.eleQuality = eleQuality;
	}
	public String getEleProducer() {
		return eleProducer;
	}
	public void setEleProducer(String eleProducer) {
		this.eleProducer = eleProducer;
	}
	public Date getEleProDate() {
		return eleProDate;
	}
	public void setEleProDate(Date eleProDate) {
		this.eleProDate = eleProDate;
	}
	public String getElePrice() {
		return elePrice;
	}
	public void setElePrice(String elePrice) {
		this.elePrice = elePrice;
	}
	public int getElenumber() {
		return elenumber;
	}
	public void setElenumber(int elenumber) {
		this.elenumber = elenumber;
	}
	@Override
	public String toString() {
		return "VehicleToElement [id=" + id + ", vehicleDetail="
				+ vehicleDetail + ", vehicleElement=" + vehicleElement
				+ ", eleQuality=" + eleQuality + ", eleProducer=" + eleProducer
				+ ", eleProDate=" + eleProDate + ", elePrice=" + elePrice
				+ ", elenumber=" + elenumber + "]";
	}
	
	
}
