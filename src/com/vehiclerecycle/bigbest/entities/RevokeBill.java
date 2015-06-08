package com.vehiclerecycle.bigbest.entities;

import java.util.Date;

/**
 *撤销单
 * 
 * @author LJJ
 *
 */
public class RevokeBill {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 业务单
	 */
	private VehicleBasic vehicleBasic;
	/**
	 * 撤单原因
	 */
	private String revokeReason;
	/**
	 * 撤单日期
	 */
	private Date revokeDate;
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
	public String getRevokeReason() {
		return revokeReason;
	}
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}
	public Date getRevokeDate() {
		return revokeDate;
	}
	public void setRevokeDate(Date revokeDate) {
		this.revokeDate = revokeDate;
	}
	@Override
	public String toString() {
		return "Revoke [id=" + id + ", vehicleBasic=" + vehicleBasic
				+ ", revokeReason=" + revokeReason + ", revokeDate="
				+ revokeDate + "]";
	}

	
}
