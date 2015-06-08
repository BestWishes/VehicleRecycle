package com.vehiclerecycle.bigbest.entities;

import java.util.Date;

/**
 *业务单
 * 
 * @author LJJ
 *
 */
public class VehicleBasic {
	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 客服
	 */
	private Employee server;
	/**
	 * 业务员
	 */
	private Employee clerk;
	/**
	 * 车辆名称
	 */
	private String vehicleName;
	/**
	 * 车俩型号
	 */
	private String vehicleType;
	/**
	 * 车牌号
	 */
	private String vehicleLicense;
	/**
	 * 车主名
	 */
	private Client client;
	/**
	 * 联系日期
	 */
	private Date contactDate;	
	/**
	 * 状态（已预约,进行中,已完成,已撤销）
	 */
	private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getClerk() {
		return clerk;
	}
	public void setClerk(Employee clerk) {
		this.clerk = clerk;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleLicense() {
		return vehicleLicense;
	}
	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Employee getServer() {
		return server;
	}
	public void setServer(Employee server) {
		this.server = server;
	}
	@Override
	public String toString() {
		String c=clerk==(null)?"null":clerk.getEmployeeName();
		String s=server==(null)?"null":server.getEmployeeName();
		String cli=client==(null)?"null":client.getName();
		return "VehicleBasic [id=" + id  +  ", server="
				+ s +", clerk="
				+ c + ", vehicleName=" + vehicleName
				+ ", vehicleType=" + vehicleType + ", VehicleLicense="
				+ vehicleLicense + ", vehicleOwner=" + cli
				+ ", contactDate=" + contactDate +
				", state=" + state +"]";
	}	
}
